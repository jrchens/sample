package me.sample.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.sys.domain.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(@Param("username") String username) ;
    List<SysUser> query(SysUser record);
}