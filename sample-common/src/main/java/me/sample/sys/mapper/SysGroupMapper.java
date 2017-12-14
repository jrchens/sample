package me.sample.sys.mapper;

import java.util.List;
import me.sample.sys.domain.SysGroup;

public interface SysGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroup record);

    SysGroup selectByPrimaryKey(Integer id);

    List<SysGroup> selectAll();

    int updateByPrimaryKey(SysGroup record);
    
    List<SysGroup> query(SysGroup record);
}