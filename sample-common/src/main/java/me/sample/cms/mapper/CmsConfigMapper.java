package me.sample.cms.mapper;

import java.util.List;
import me.sample.cms.domain.CmsConfig;

public interface CmsConfigMapper {
    int deleteByPrimaryKey(String configCode);

    int insert(CmsConfig record);

    CmsConfig selectByPrimaryKey(String configCode);

    List<CmsConfig> selectAll();

    int updateByPrimaryKey(CmsConfig record);
    
    List<CmsConfig> query(CmsConfig record);

}