package me.sample.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.hash.Hashing;

import me.sample.base.service.SequenceService;
import me.sample.exception.ServiceException;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserGroup;
import me.sample.sys.domain.SysUserRole;
import me.sample.sys.mapper.SysUserMapper;
import me.sample.sys.service.SysUserGroupService;
import me.sample.sys.service.SysUserRoleService;
import me.sample.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserGroupService sysUserGroupService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

//    @Override
//    public int deleteByPrimaryKey(Integer id) {
//        int aff = 0;
//        try {
//            aff = sysUserMapper.deleteByPrimaryKey(id);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("delete one sys user error",e);
//        }
//        return aff;
//    }

    @Override
    public int insert(SysUser record) {
        int aff = 0;
        try {
            Date now = DateTime.now().toDate();
            record.setId(sequenceService.next().intValue());
            record.setState(1);
            record.setDisabled(Boolean.FALSE);
            record.setDeleted(Boolean.FALSE);
            String passwordSalt = getPasswordSalt();
            record.setPasswordSalt(passwordSalt);
            String password = record.getPassword(); // md5(USERNAME@PASSWORD)
            password = setPassword(passwordSalt, password);
            if (password != null) {
                record.setPassword(password); // hmacsha256(salt,md5hash)
            }
            record.setPassword(password);
            record.setCrtime(now);

            aff = sysUserMapper.insert(record);
            
            aff += sysUserGroupService.insert(new SysUserGroup(record.getUsername(),record.getGroupname()));
            aff += sysUserRoleService.insert(new SysUserRole(record.getUsername(),record.getRolename()));
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one sys user error",e);
        }
        return aff;
    }
    
    private String getPasswordSalt() {
        Random rd = new Random();
        String src = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+-={}[]:;<>,./?";
        int len = src.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            buffer.append(src.charAt(rd.nextInt(len)));
        }
        return buffer.toString();
    }

    private String setPassword(String salt, String pass) {
        if (StringUtils.hasText(salt) && StringUtils.hasText(pass)) {
            return Hashing.hmacSha256(salt.getBytes()).hashBytes(pass.getBytes()).toString();
        }
        return null;
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        SysUser record = null;
        try {
            record = sysUserMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one sys user error",e);
        }
        return record;
    }

    @Override
    public List<SysUser> selectAll() {
        List<SysUser> records = null;
        try {
            records = sysUserMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all sys user error",e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        int aff = 0;
        try {

            Date now = DateTime.now().toDate();
            String newPassword = record.getPassword();
            SysUser original = selectByPrimaryKey(record.getId());
            String origianlPassword = original.getPassword();
            String origianlPasswordSalt = original.getPasswordSalt();
            String origianlGroupname = original.getGroupname();
            String origianlRolename = original.getRolename();
            if (!StringUtils.hasText(newPassword) || origianlPassword.equals(newPassword)) {
                record.setPassword(origianlPassword);
            } else {
                newPassword = setPassword(origianlPasswordSalt, newPassword);
                record.setPassword(newPassword);
            }
            record.setPasswordSalt(origianlPasswordSalt);

            record.setMdtime(now);
            aff = sysUserMapper.updateByPrimaryKey(record);
            
            if(!origianlGroupname.equals(record.getGroupname())){
                aff += sysUserGroupService.deleteByUserGroupname(original.getUsername(),origianlGroupname);
                aff += sysUserGroupService.insert(new SysUserGroup(record.getUsername(),record.getGroupname()));
            }
            if(!origianlRolename.equals(record.getRolename())){
                aff += sysUserRoleService.deleteByUserRolename(original.getUsername(),origianlRolename);
                aff += sysUserRoleService.insert(new SysUserRole(record.getUsername(),record.getRolename()));
            }
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one sys user error",e);
        }
        return aff;
    }

    @Override
    public int removeByPrimaryKey(SysUser record) {
        int aff = 0;
        try {
            SysUser clone = selectByPrimaryKey(record.getId());
            clone.setDeleted(Boolean.TRUE);
            clone.setMduser(record.getMduser());
            aff = updateByPrimaryKey(clone);
            
            String username = clone.getUsername();
//            List<SysGroup> sysGroups = sysUserGroupService.queryByUsername(username);
//            if(sysGroups.isEmpty()){
                aff += sysUserGroupService.deleteByUsername(username);
//            }else{
//                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
//            }
            
//            List<SysRole> sysRoles = sysUserRoleService.queryByUsername(username);
//            if(sysRoles.isEmpty()){
                aff += sysUserRoleService.deleteByUsername(username);
//            }else{
//                throw new DataIntegrityViolationException("Cannot delete a row because of foreign key constraint");
//            }
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one sys user error",e);
        }
        return aff;
    }
    
    @Override
    public SysUser selectByUsername(String username) {
        SysUser record = null;
        try {
            record = sysUserMapper.selectByUsername(username);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one sys user by username error",e);
        }
        return record;
    }

    @Override
    public PageInfo<SysUser> query(SysUser record, int pageNum, int pageSize) {
        PageInfo<SysUser> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<SysUser> list = sysUserMapper.query(record);
            pi = new PageInfo<SysUser>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query sys user error",e);
        }
        return pi;
    }
}
