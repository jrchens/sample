package me.sample.cms.mapper;

import java.util.List;
import me.sample.cms.domain.CmsArticle;

public interface CmsArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsArticle record);

    CmsArticle selectByPrimaryKey(Integer id);

    List<CmsArticle> selectAll();

    int updateByPrimaryKey(CmsArticle record);
    
    List<CmsArticle> query(CmsArticle record);

}