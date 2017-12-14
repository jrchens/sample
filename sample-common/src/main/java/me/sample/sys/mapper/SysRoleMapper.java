package me.sample.sys.mapper;

import java.util.List;
import me.sample.sys.domain.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> query(SysRole record);
}