package me.sample.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import me.sample.base.service.SequenceService;
import me.sample.cms.domain.CmsArticle;
import me.sample.cms.mapper.CmsArticleMapper;
import me.sample.cms.service.CmsArticleService;
import me.sample.exception.ServiceException;
import org.apache.velocity.app.VelocityEngine;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class CmsArticleServiceImpl implements CmsArticleService {
   private static final Logger logger = LoggerFactory.getLogger(CmsArticleService.class);
    
    @Autowired
    private CmsArticleMapper cmsArticleMapper;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private VelocityEngine velocityEngine;

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
//  @Override
//  public int deleteByPrimaryKey(Integer id) {
//      int aff = 0;
//      try {
//          aff = cmsArticleMapper.deleteByPrimaryKey(id);
//      } catch (Exception e) {
//          logger.error("", e);
//          throw new ServiceException("delete one cms article error",e);
//      }
//      return aff;
//  }
    
    @Override
    public int insert(CmsArticle record) {
        int aff = 0;
        try {
            // Date now = DateTime.now().toDate();
            Timestamp now = new Timestamp(DateTime.now().getMillis());
            
            Integer id = sequenceService.next("cms").intValue();
            record.setId(id);
            record.setDisabled(Boolean.FALSE);
            record.setDeleted(Boolean.FALSE);
            record.setCrtime(now);

            aff = cmsArticleMapper.insert(record);

        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one cms article error",e);
        }
        return aff;
    }

    public void generateHtml(CmsArticle record){
        try {
            File dir = new File("/usr/local/var/cms/"+record.getCategoryNodename());
            if(!dir.exists()){
                dir.mkdirs();
            }

            File dest = new File(dir,  String.valueOf(record.getId()).concat(".html"));

            int state = record.getState();
            if ((state & 1) != 1) {
                dest.delete();
                return;
            }
//            VelocityContext context = new VelocityContext();
//            context.put("article",record);
            Map<String,Object> model = Maps.newHashMap();
            model.put("article",record);

            // Template template = Velocity.getTemplate("me/sample/cms/vm/detail.vm","utf-8");


            FileWriter fw = new FileWriter(dest);

            // template.merge(context, fw);
//            VelocityEngine velocityEngine, String templateLocation, String encoding,
//                    Map<String, Object> model, Writer writer
            VelocityEngineUtils.mergeTemplate(velocityEngine,"me/sample/cms/vm/detail.vm","utf-8",model,fw);

            fw.flush();
            fw.close();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("generate cms article html file error",e);
        }
    }

    @Override
    public CmsArticle selectByPrimaryKey(Integer id) {
        CmsArticle record = null;
        try {
            record = cmsArticleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one cms article error",e);
        }
        return record;
    }

    @Override
    public List<CmsArticle> selectAll() {
        List<CmsArticle> records = null;
        try {
            records = cmsArticleMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all cms article error",e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(CmsArticle record) {
        int aff = 0;
        try {
            // Date now = DateTime.now().toDate();
            Timestamp now = new Timestamp(DateTime.now().getMillis());
            
            record.setMdtime(now);
            aff = cmsArticleMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one cms article error",e);
        }
        return aff;
    }

    @Override
    public int remove(CmsArticle record) {
        int aff = 0;
        try {
            CmsArticle clone = selectByPrimaryKey(record.getId());

            Long count = jdbcTemplate.queryForObject("select count(1) from cms_article where deleted = 0 and parent_id = ?", Long.class, record.getId());
            if (count.longValue() == 0l) {
                clone.setState(0);
                clone.setDeleted(Boolean.TRUE);
                clone.setMduser(record.getMduser());

                aff = updateByPrimaryKey(clone);
            } else {
                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
            }
            
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one cms article error",e);
        }
        return aff;
    }

    @Override
    public PageInfo<CmsArticle> query(CmsArticle record, int pageNum, int pageSize) {
        PageInfo<CmsArticle> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<CmsArticle> list = cmsArticleMapper.query(record);
            pi = new PageInfo<CmsArticle>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query cms article error",e);
        }
        return pi;
    }
}
