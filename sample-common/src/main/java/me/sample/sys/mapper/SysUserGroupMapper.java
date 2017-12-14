package me.sample.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.sys.domain.SysGroup;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserGroup;

public interface SysUserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserGroup record);

    SysUserGroup selectByPrimaryKey(Integer id);

    List<SysUserGroup> selectAll();

    int updateByPrimaryKey(SysUserGroup record);
    
    List<SysGroup> queryByUsername(@Param("username") String username);
    List<SysUser> queryByGroupname(@Param("groupname") String groupname);
    
    int deleteByUsername(@Param("username") String username);
    int deleteByGroupname(@Param("groupname") String groupname);
    int deleteByUserGroupname(@Param("username") String username,@Param("groupname") String groupname);
}