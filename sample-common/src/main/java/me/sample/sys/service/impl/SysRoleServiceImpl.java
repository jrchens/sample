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
import me.sample.sys.domain.SysRole;
import me.sample.sys.mapper.SysRoleMapper;
import me.sample.sys.service.SysRolePermissionService;
import me.sample.sys.service.SysRoleService;
import me.sample.sys.service.SysUserRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleService.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // @Override
    // public int deleteByPrimaryKey(Integer id) {
    // int aff = 0;
    // try {
    // aff = sysRoleMapper.deleteByPrimaryKey(id);
    // } catch (Exception e) {
    // logger.error("", e);
    // throw new ServiceException("delete one sys roleerror",e);
    // }
    // return aff;
    // }

    @Override
    public int insert(SysRole record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();

            Integer id = sequenceService.next().intValue();
            record.setId(id);
            record.setDisabled(Boolean.FALSE);
            record.setDeleted(Boolean.FALSE);
            record.setCrtime(now);

            aff = sysRoleMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys roleerror", e);
        }
        return aff;
    }

    @Override
    public SysRole selectByPrimaryKey(Integer id) {
        SysRole record = null;
        try {
            record = sysRoleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one sys roleerror", e);
        }
        return record;
    }

    @Override
    public List<SysRole> selectAll() {
        List<SysRole> records = null;
        try {
            records = sysRoleMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all sys roleerror", e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();
            record.setMdtime(now);
            aff = sysRoleMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one sys roleerror", e);
        }
        return aff;
    }

    @Override
    public int removeByPrimaryKey(SysRole record) {
        int aff = 0;
        try {
            SysRole clone = selectByPrimaryKey(record.getId());
            clone.setDeleted(Boolean.TRUE);
            clone.setMduser(record.getMduser());
            aff = updateByPrimaryKey(clone);

            String rolename = clone.getRolename();

//            List<SysUser> users = sysUserRoleService.queryByRolename(rolename);
//            if (users.isEmpty()) {
            Long count = jdbcTemplate.queryForObject("select count(1) from sys_user where deleted = 0 and rolename = ?", Long.class, rolename);
            if (count.longValue() == 0l) {
                aff += sysUserRoleService.deleteByRolename(rolename);
            } else {
                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
            }

//            List<SysPermission> sysPermissions = sysRolePermissionService.queryByRolename(rolename);
//            if (sysPermissions.isEmpty()) {
                aff += sysRolePermissionService.deleteByRolename(rolename);
//            } else {
//                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
//            }
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one sys roleerror", e);
        }
        return aff;

    }

    @Override
    public PageInfo<SysRole> query(SysRole record, int pageNum, int pageSize) {
        PageInfo<SysRole> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<SysRole> list = sysRoleMapper.query(record);
            pi = new PageInfo<SysRole>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query sys roleerror", e);
        }
        return pi;
    }

}
