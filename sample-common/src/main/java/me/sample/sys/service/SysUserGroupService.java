package me.sample.sys.service;

import java.util.List;

import me.sample.sys.domain.SysGroup;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserGroup;

public interface SysUserGroupService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserGroup record);

//    SysUserGroup selectByPrimaryKey(Integer id);

//    List<SysUserGroup> selectAll();

//    int updateByPrimaryKey(SysUserGroup record);

    List<SysGroup> queryByUsername(String username);
    List<SysUser> queryByGroupname(String groupname);

    int deleteByUsername(String username);
    int deleteByGroupname(String groupname);
    int deleteByUserGroupname(String username,String groupname);
}