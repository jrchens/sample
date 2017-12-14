package me.sample.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.sys.domain.SysGroup;

public interface SysGroupService {

//    int deleteByPrimaryKey(Integer id);

    int insert(SysGroup record);

    SysGroup selectByPrimaryKey(Integer id);

    List<SysGroup> selectAll();

    int updateByPrimaryKey(SysGroup record);

    int removeByPrimaryKey(SysGroup record);
    PageInfo<SysGroup> query(SysGroup record,int pageNum, int pageSize);
}
