package me.sample.sys.service;

import java.util.List;

import me.sample.sys.domain.SysPermission;
import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysRolePermission;

public interface SysRolePermissionService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    // SysUserRole selectByPrimaryKey(Integer id);

    // List<SysUserRole> selectAll();

    // int updateByPrimaryKey(SysRolePermission record);

    List<SysPermission> queryByRolename(String rolename);
    List<SysRole> queryByPermissionCode(String permissionCode);
    
    int deleteByRolename(String rolename);
    int deleteByPermissionCode(String permissionCode);
    int deleteByRolenamePermissionCode(String rolename, String permissionCode);
}
