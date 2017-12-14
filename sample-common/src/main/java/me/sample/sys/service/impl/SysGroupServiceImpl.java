package me.sample.sys.service.impl;

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

import me.sample.base.service.SequenceService;
import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysGroup;
import me.sample.sys.mapper.SysGroupMapper;
import me.sample.sys.service.SysGroupService;
import me.sample.sys.service.SysUserGroupService;

@Service
public class SysGroupServiceImpl implements SysGroupService {
    private static final Logger logger = LoggerFactory.getLogger(SysGroupService.class);

    @Autowired
    private SysGroupMapper sysGroupMapper;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private SysUserGroupService sysUserGroupService;

    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Override
//    public int deleteByPrimaryKey(Integer id) {
//        int aff = 0;
//        try {
//            aff = sysGroupMapper.deleteByPrimaryKey(id);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("delete one sys grouperror",e);
//        }
//        return aff;
//    }

    @Override
    public int insert(SysGroup record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();

            Integer id = sequenceService.next().intValue();
            record.setId(id);
            record.setDisabled(Boolean.FALSE);
            record.setDeleted(Boolean.FALSE);
            record.setCrtime(now);

            aff = sysGroupMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys grouperror",e);
        }
        return aff;
        
    }

    @Override
    public SysGroup selectByPrimaryKey(Integer id) {
        SysGroup record = null;
        try {
            record = sysGroupMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one sys grouperror",e);
        }
        return record;
    }

    @Override
    public List<SysGroup> selectAll() {
        List<SysGroup> records = null;
        try {
            records = sysGroupMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all sys grouperror",e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(SysGroup record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();
            record.setMdtime(now);
            aff = sysGroupMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one sys grouperror",e);
        }
        return aff;
    }
    
    @Override
    public int removeByPrimaryKey(SysGroup record) {
        int aff = 0;
        try {
            SysGroup clone = selectByPrimaryKey(record.getId());
            clone.setDeleted(Boolean.TRUE);
            clone.setMduser(record.getMduser());
            aff = updateByPrimaryKey(clone);
            
            String groupname = clone.getGroupname();
//            List<SysUser> users = sysUserGroupService.queryByGroupname(groupname);
//            if(users.isEmpty()){
            
            Long count = jdbcTemplate.queryForObject("select count(1) from sys_user where deleted = 0 and groupname = ?", Long.class, groupname);
            if (count.longValue() == 0l) {
                aff += sysUserGroupService.deleteByGroupname(clone.getGroupname());
            }else{
                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one sys grouperror",e);
        }
        return aff;
    }
    

    @Override
    public PageInfo<SysGroup> query(SysGroup record,int pageNum, int pageSize) {
        PageInfo<SysGroup> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<SysGroup> list = sysGroupMapper.query(record);
            pi = new PageInfo<SysGroup>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query sys grouperror",e);
        }
        return pi;
    }

    
}
