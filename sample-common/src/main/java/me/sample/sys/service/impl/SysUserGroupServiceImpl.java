package me.sample.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysGroup;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserGroup;
import me.sample.sys.mapper.SysUserGroupMapper;
import me.sample.sys.service.SysUserGroupService;

@Service
public class SysUserGroupServiceImpl implements SysUserGroupService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserGroupService.class);

    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int aff = 0;
        try {
            aff = sysUserGroupMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("delete one sys user group relationerror",e);
        }
        return aff;
    }

    @Override
    public int insert(SysUserGroup record) {
        int aff = 0;
        try {
            aff = sysUserGroupMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys user group relationerror",e);
        }
        return aff;
    }

//    @Override
//    public SysUserGroup selectByPrimaryKey(Integer id) {
//        SysUserGroup record = null;
//        try {
//            record = sysUserGroupMapper.selectByPrimaryKey(id);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select one sys user group relationerror",e);
//        }
//        return record;
//    }

//    @Override
//    public List<SysUserGroup> selectAll() {
//        List<SysUserGroup> records = null;
//        try {
//            records = sysUserGroupMapper.selectAll();
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("select all sys user group relationerror",e);
//        }
//        return records;
//    }

//    @Override
//    public int updateByPrimaryKey(SysUserGroup record) {
//        int aff = 0;
//        try {
//            aff = sysUserGroupMapper.updateByPrimaryKey(record);
//        } catch (Exception e) {
//            logger.error("{}", e);
//            throw new ServiceException("update one sys user group relationerror",e);
//        }
//        return aff;
//    }

    @Override
    public List<SysGroup> queryByUsername(String username) {
        List<SysGroup> records = null;
        try {
            records = sysUserGroupMapper.queryByUsername(username);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys user group relation by usernameerror",e);
        }
        return records;
    }

    @Override
    public List<SysUser> queryByGroupname(String groupname) {
        List<SysUser> records = null;
        try {
            records = sysUserGroupMapper.queryByGroupname(groupname);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select sys user group relation by groupnameerror",e);
        }
        return records;
    }

    @Override
    public int deleteByUsername(String username) {
        int aff = 0;
        try {
            aff = sysUserGroupMapper.deleteByUsername(username);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user group relation by usernameerror",e);
        }
        return aff;
    }

    @Override
    public int deleteByGroupname(String groupname) {
        int aff = 0;
        try {
            aff = sysUserGroupMapper.deleteByGroupname(groupname);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user group relation by groupnameerror",e);
        }
        return aff;
    }
    
    @Override
    public int deleteByUserGroupname(String username,String groupname) {
        int aff = 0;
        try {
            aff = sysUserGroupMapper.deleteByUserGroupname(username,groupname);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("delete sys user group relation by username & groupnameerror",e);
        }
        return aff;
    }
    
}
