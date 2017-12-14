package me.sample.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserRole;
import me.sample.sys.mapper.SysUserRoleMapper;
import me.sample.sys.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleService.class);

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int aff = 0;
        try {
            aff = sysUserRoleMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("delete one sys user role relationerror",e);
        }
        return aff;
    }

    @Override
    public int insert(SysUserRole record) {
        int aff = 0;
        try {
            aff = sysUserRoleMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys user role relationerror",e);
        }
        return aff;
    }

//    @Override
//    public SysUserRole selectByPrimaryKey(Integer id) {
//        SysUserRole record = null;
//        try {
//            record = sysUserRoleMapper.selectByPrimaryKey(id);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select one sys user role relationerror",e);
//        }
//        return record;
//    }

//    @Override
//    public List<SysUserRole> selectAll() {
//        List<SysUserRole> records = null;
//        try {
//            records = sysUserRoleMapper.selectAll();
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select all sys user role relationerror",e);
//        }
//        return records;
//    }

//    @Override
//    public int updateByPrimaryKey(SysUserRole record) {
//        int aff = 0;
//        try {
//            aff = sysUserRoleMapper.updateByPrimaryKey(record);
//        } catch (Exception e) {
//            logger.error("{}", e);
//            throw new ServiceException("update one sys user role relationerror",e);
//        }
//        return aff;
//    }

    @Override
    public List<SysRole> queryByUsername(String username) {
        List<SysRole> records = null;
        try {
            records = sysUserRoleMapper.queryByUsername(username);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys user role relation by usernameerror",e);
        }
        return records;
    }

    @Override
    public List<SysUser> queryByRolename(String groupname) {
        List<SysUser> records = null;
        try {
            records = sysUserRoleMapper.queryByRolename(groupname);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys user role relation by groupnameerror",e);
        }
        return records;
    }

    @Override
    public int deleteByUsername(String username) {
        int aff = 0;
        try {
            aff = sysUserRoleMapper.deleteByUsername(username);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user role relation by usernameerror",e);
        }
        return aff;
    }

    @Override
    public int deleteByRolename(String groupname) {
        int aff = 0;
        try {
            aff = sysUserRoleMapper.deleteByRolename(groupname);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user role relation by groupnameerror",e);
        }
        return aff;
    }
    
    @Override
    public int deleteByUserRolename(String username,String groupname) {
        int aff = 0;
        try {
            aff = sysUserRoleMapper.deleteByUserRolename(username,groupname);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user role relation by username & groupnameerror",e);
        }
        return aff;
    }
    
}
