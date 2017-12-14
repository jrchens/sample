package me.sample.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.cms.domain.CmsCategory;

public interface CmsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCategory record);

    CmsCategory selectByPrimaryKey(Integer id);

    List<CmsCategory> selectAll();

    int updateByPrimaryKey(CmsCategory record);

    List<CmsCategory> queryByParentId(@Param("parentId") Integer parentId);
}