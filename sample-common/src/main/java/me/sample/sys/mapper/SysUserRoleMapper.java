package me.sample.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserRole;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
    
    List<SysRole> queryByUsername(@Param("username") String username);
    List<SysUser> queryByRolename(@Param("rolename") String rolename);
    int deleteByUsername(@Param("username") String username);
    int deleteByRolename(@Param("rolename") String rolename);
    int deleteByUserRolename(@Param("username") String username,@Param("rolename") String rolename);

}