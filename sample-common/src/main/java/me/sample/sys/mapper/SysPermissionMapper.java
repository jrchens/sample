package me.sample.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.sys.domain.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> query(@Param("pid") Integer pid);
    
    SysPermission getRoot();

}