package me.sample.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.base.domain.TreeObject;
import me.sample.sys.domain.SysPermission;

public interface SysPermissionService {


//    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);
    
    int removeByPrimaryKey(SysPermission record);
    
    List<TreeObject> load(Integer pid);
    TreeObject getRoot();
    
    List<SysPermission> query(Integer pid);
    PageInfo<SysPermission> query(SysPermission record,int pageNum, int pageSize);
}
