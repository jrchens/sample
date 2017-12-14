package me.sample.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysPermission;
import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysRolePermission;
import me.sample.sys.mapper.SysRolePermissionMapper;
import me.sample.sys.service.SysRolePermissionService;

@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    private static final Logger logger = LoggerFactory.getLogger(SysRolePermissionService.class);

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int aff = 0;
        try {
            aff = sysRolePermissionMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("delete one sys role permission relation error",e);
        }
        return aff;
    }

    @Override
    public int insert(SysRolePermission record) {
        int aff = 0;
        try {
            aff = sysRolePermissionMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys role permission relation error",e);
        }
        return aff;
    }

//    @Override
//    public SysRolePermission selectByPrimaryKey(Integer id) {
//        SysRolePermission record = null;
//        try {
//            record = sysUserRoleMapper.selectByPrimaryKey(id);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select one sys role permission relation error",e);
//        }
//        return record;
//    }

//    @Override
//    public List<SysRolePermission> selectAll() {
//        List<SysRolePermission> records = null;
//        try {
//            records = sysUserRoleMapper.selectAll();
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select all sys role permission relation error",e);
//        }
//        return records;
//    }

//    @Override
//    public int updateByPrimaryKey(SysRolePermission record) {
//        int aff = 0;
//        try {
//            aff = sysUserRoleMapper.updateByPrimaryKey(record);
//        } catch (Exception e) {
//            logger.error("{}", e);
//            throw new ServiceException("update one sys role permission relation error",e);
//        }
//        return aff;
//    }

    @Override
    public List<SysPermission> queryByRolename(String rolename) {
        List<SysPermission> records = null;
        try {
            records = sysRolePermissionMapper.queryByRolename(rolename);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys role permission relation by rolename error",e);
        }
        return records;
    }

    @Override
    public List<SysRole> queryByPermissionCode(String permissionCode) {
        List<SysRole> records = null;
        try {
            records = sysRolePermissionMapper.queryByPermissionCode(permissionCode);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys role permission relation by permission code error",e);
        }
        return records;
    }

    @Override
    public int deleteByRolename(String rolename) {
        int aff = 0;
        try {
            aff = sysRolePermissionMapper.deleteByRolename(rolename);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys role permission relation by rolename error",e);
        }
        return aff;
    }

    @Override
    public int deleteByPermissionCode(String permissionCode) {
        int aff = 0;
        try {
            aff = sysRolePermissionMapper.deleteByRolename(permissionCode);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys role permission relation by permissionCode error",e);
        }
        return aff;
    }
    
    @Override
    public int deleteByRolenamePermissionCode(String rolename,String permissionCode) {
        int aff = 0;
        try {
            aff = sysRolePermissionMapper.deleteByRolenamePermissionCode(rolename,permissionCode);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys role permission relation by rolename & permissionCode error",e);
        }
        return aff;
    }
    
}
