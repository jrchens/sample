package me.sample.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.sample.cms.domain.CmsFile;

public interface CmsFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsFile record);

    CmsFile selectByPrimaryKey(Integer id);

    List<CmsFile> selectAll();

    int updateByPrimaryKey(CmsFile record);

    List<CmsFile> query(CmsFile record);
    
    List<CmsFile> queryByArticleId(@Param("articleId") Integer articleId);

    List<CmsFile> queryByFileSha1(@Param("fileSha1") String fileSha1);
}