package me.sample.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import me.sample.cms.domain.CmsArticle;

public interface CmsArticleService {
    // int deleteByPrimaryKey(Integer id);

    int insert(CmsArticle record);

    CmsArticle selectByPrimaryKey(Integer id);

    List<CmsArticle> selectAll();

    int updateByPrimaryKey(CmsArticle record);
    
    
    int remove(CmsArticle record);
    PageInfo<CmsArticle> query(CmsArticle record,int pageNum, int pageSize);

}