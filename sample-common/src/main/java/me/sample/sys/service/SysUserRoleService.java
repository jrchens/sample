package me.sample.sys.service;

import java.util.List;

import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserRole;

public interface SysUserRoleService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

//    SysUserRole selectByPrimaryKey(Integer id);

//    List<SysUserRole> selectAll();

//    int updateByPrimaryKey(SysUserRole record);

    List<SysRole> queryByUsername(String username);
    List<SysUser> queryByRolename(String rolename);

    int deleteByUsername(String username);
    int deleteByRolename(String rolename);
    int deleteByUserRolename(String username,String rolename);
}
