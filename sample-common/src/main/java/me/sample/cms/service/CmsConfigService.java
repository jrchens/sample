package me.sample.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.cms.domain.CmsConfig;

public interface CmsConfigService {
    // int deleteByPrimaryKey(String configCode);

    int insert(CmsConfig record);

    CmsConfig selectByPrimaryKey(String configCode);

    List<CmsConfig> selectAll();

    int updateByPrimaryKey(CmsConfig record);
    
    PageInfo<CmsConfig> query(CmsConfig record);
}