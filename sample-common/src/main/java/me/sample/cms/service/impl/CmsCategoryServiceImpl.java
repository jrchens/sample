package me.sample.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import me.sample.base.domain.TreeObject;
import me.sample.base.service.SequenceService;
import me.sample.cms.domain.CmsCategory;
import me.sample.cms.mapper.CmsCategoryMapper;
import me.sample.cms.service.CmsCategoryService;
import me.sample.exception.ServiceException;

@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CmsCategoryService.class);
    
    @Autowired
    private CmsCategoryMapper cmsCategoryMapper;
    @Autowired
    private SequenceService sequenceService;

    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
//  @Override
//  public int deleteByPrimaryKey(Integer id) {
//      int aff = 0;
//      try {
//          aff = cmsCategoryMapper.deleteByPrimaryKey(id);
//      } catch (Exception e) {
//          logger.error("", e);
//          throw new ServiceException("delete one cms category error",e);
//      }
//      return aff;
//  }
    
    @Override
    public int insert(CmsCategory record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();
            
            Integer parentId = record.getParentId();
            CmsCategory parentNode = selectByPrimaryKey(parentId);
            String parentFullPath = parentNode.getFullPath();
            Integer parentNodeLevel = parentNode.getNodeLevel();

            Integer id = sequenceService.next("cms").intValue();
            record.setFullPath(parentFullPath.concat("/").concat(String.valueOf(id)));
            record.setNodeLevel(parentNodeLevel+1);
            
            record.setId(id);
            record.setDisabled(Boolean.FALSE);
            record.setDeleted(Boolean.FALSE);
            record.setCrtime(now);

            aff = cmsCategoryMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one cms category error",e);
        }
        return aff;
    }

    @Override
    public CmsCategory selectByPrimaryKey(Integer id) {
        CmsCategory record = null;
        try {
            record = cmsCategoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one cms category error",e);
        }
        return record;
    }

    @Override
    public List<CmsCategory> selectAll() {
        List<CmsCategory> records = null;
        try {
            records = cmsCategoryMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all cms category error",e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(CmsCategory record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();
            
            record.setMdtime(now);
            aff = cmsCategoryMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one cms category error",e);
        }
        return aff;
    }

    @Override
    public int removeByPrimaryKey(CmsCategory record) {
        int aff = 0;
        try {
            CmsCategory clone = selectByPrimaryKey(record.getId());
            String nodename = clone.getNodename();

            Long count = jdbcTemplate.queryForObject("select count(1) from cms_article where deleted = 0 and category_nodename = ?", Long.class, nodename);
            if (count.longValue() == 1l) {
                clone.setDeleted(Boolean.TRUE);
                clone.setMduser(record.getMduser());
                aff = updateByPrimaryKey(clone);
            } else {
                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
            }
            
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one cms category error",e);
        }
        return aff;
    }
    

    private void load(TreeObject item, final List<TreeObject> children,boolean skipDisabled) {
        List<CmsCategory> list = queryByParentId(Integer.parseInt(item.getId()));
        if(!list.isEmpty()){
            for (CmsCategory record : list) {
                if(skipDisabled && record.getDisabled()){
                    continue;
                }else{
                    children.add(new TreeObject(String.valueOf(record.getId()), record.getViewname(),record.getDisabled()));
                }
            }
            item.setChildren(children);
            
            for (TreeObject itemNode : children) {
                load(itemNode,itemNode.getChildren(), skipDisabled);
            }
        }
    }
    
    @Override
    public List<TreeObject> load(Integer parentId,boolean skipDisabled) {

        List<TreeObject> tree = Lists.newArrayList();
        List<CmsCategory> list = queryByParentId(parentId);
        for (CmsCategory record : list) {
            if(skipDisabled && record.getDisabled()){
                continue;
            }else{
                tree.add(new TreeObject(String.valueOf(record.getId()), record.getViewname(),record.getDisabled()));
            }
        }
        
        for (TreeObject item : tree) {
            load(item,item.getChildren(),skipDisabled);
        }
        
        return tree;
    }

    @Override
    public List<CmsCategory> queryByParentId(Integer parentId) {
        List<CmsCategory> list = null;
        try {
            list = cmsCategoryMapper.queryByParentId(parentId);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query cms category by parent id error",e);
        }
        return list;
    }

    @Override
    public PageInfo<CmsCategory> query(CmsCategory record, int pageNum, int pageSize) {
        PageInfo<CmsCategory> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<CmsCategory> list = cmsCategoryMapper.queryByParentId(record.getId());
            pi = new PageInfo<CmsCategory>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query cms category error",e);
        }
        return pi;
    }

}
