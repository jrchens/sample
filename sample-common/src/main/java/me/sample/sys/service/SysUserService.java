package me.sample.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.sys.domain.SysUser;

public interface SysUserService {

//    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    int removeByPrimaryKey(SysUser record);

    SysUser selectByUsername(String username);

    PageInfo<SysUser> query(SysUser record, int pageNum, int pageSize);
}
