package me.sample.cms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import me.sample.cms.domain.CmsFile;

public interface CmsFileService {
    // int deleteByPrimaryKey(Integer id);

    int insert(CmsFile record);

    CmsFile selectByPrimaryKey(Integer id);

    List<CmsFile> selectAll();

    int updateByPrimaryKey(CmsFile record);
    
    int save(MultipartFile[] files,CmsFile record);
    int remove(CmsFile record);
    PageInfo<CmsFile> query(CmsFile record,int pageNum, int pageSize);
    
    List<CmsFile> queryByArticleId(Integer articleId);
    List<CmsFile> queryByFileSha1(String fileSha1);

}