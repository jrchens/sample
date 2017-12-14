package me.sample.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.sys.domain.SysPermission;
import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysRolePermission;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    List<SysRolePermission> selectAll();

    int updateByPrimaryKey(SysRolePermission record);

    List<SysPermission> queryByRolename(@Param("rolename") String rolename);

    List<SysRole> queryByPermissionCode(@Param("permissionCode") String permissionCode);

    int deleteByRolename(@Param("rolename") String rolename);

    int deleteByPermissionCode(@Param("permissionCode") String permissionCode);

    int deleteByRolenamePermissionCode(@Param("rolename") String rolename,
            @Param("permissionCode") String permissionCode);
}