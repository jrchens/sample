package me.sample.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.base.domain.TreeObject;
import me.sample.cms.domain.CmsCategory;

public interface CmsCategoryService {
    // int deleteByPrimaryKey(Integer id);

    int insert(CmsCategory record);

    CmsCategory selectByPrimaryKey(Integer id);

    List<CmsCategory> selectAll();

    int updateByPrimaryKey(CmsCategory record);
    
    int removeByPrimaryKey(CmsCategory record);
    
    List<TreeObject> load(Integer parentId,boolean skipDisabled);
    List<CmsCategory> queryByParentId(Integer parentId);
    PageInfo<CmsCategory> query(CmsCategory record,int pageNum, int pageSize);
}