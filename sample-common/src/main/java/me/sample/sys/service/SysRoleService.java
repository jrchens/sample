package me.sample.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.sys.domain.SysRole;

public interface SysRoleService {

//    int deleteByPrimaryKey(Integer id);
    
    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
    
    int removeByPrimaryKey(SysRole record);
    PageInfo<SysRole> query(SysRole record,int pageNum, int pageSize);
}
