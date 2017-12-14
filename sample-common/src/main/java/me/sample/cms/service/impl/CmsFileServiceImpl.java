package me.sample.cms.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import me.sample.cms.domain.CmsFile;
import me.sample.cms.mapper.CmsFileMapper;
import me.sample.cms.service.CmsFileService;
import me.sample.exception.ServiceException;

@Service
public class CmsFileServiceImpl implements CmsFileService {
    private static final Logger logger = LoggerFactory.getLogger(CmsFileService.class);

    @Autowired
    private CmsFileMapper cmsFileMapper;

    private static final String SAVE_PATH = "/usr/local/var/upload";
    private static final Map<String,String> dataType = Maps.newHashMap();
    
    static {
        dataType.put("jpeg","image/jpeg");
        dataType.put("jpg","image/jpeg");
        dataType.put("png","image/png");
        dataType.put("pdf","application/pdf");
        dataType.put("doc","application/msword");
        dataType.put("docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        dataType.put("ppt","application/vnd.ms-powerpoint");
        dataType.put("pptx","application/vnd.openxmlformats-officedocument.presentationml.presentation");
        dataType.put("xls","application/vnd.ms-excel");
        dataType.put("xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        dataType.put("rar","application/octet-stream");
        dataType.put("zip","application/zip");
        // dataType.put("*","application/octet-stream");
    }

    // @Autowired
    // private SequenceService sequenceService;

    // private JdbcTemplate jdbcTemplate;
    // @Autowired
    // public void setDataSource(DataSource dataSource){
    // this.jdbcTemplate = new JdbcTemplate(dataSource);
    // }

    // @Override
    // public int deleteByPrimaryKey(Integer id) {
    // int aff = 0;
    // try {
    // aff = cmsFileMapper.deleteByPrimaryKey(id);
    // } catch (Exception e) {
    // logger.error("", e);
    // throw new ServiceException("delete one cms file error",e);
    // }
    // return aff;
    // }

    @Override
    public int insert(CmsFile record) {
        int aff = 0;
        try {
            // Date now = DateTime.now().toDate();
            Timestamp now = new Timestamp(DateTime.now().getMillis());

            // Integer id = sequenceService.next("cms").intValue();
            // record.setId(id);
            record.setDeleted(Boolean.FALSE);
            record.setCrtime(now);

            aff = cmsFileMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one cms file error", e);
        }
        return aff;
    }

    @Override
    public CmsFile selectByPrimaryKey(Integer id) {
        CmsFile record = null;
        try {
            record = cmsFileMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one cms file error", e);
        }
        return record;
    }

    @Override
    public List<CmsFile> selectAll() {
        List<CmsFile> records = null;
        try {
            records = cmsFileMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all cms file error", e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(CmsFile record) {
        int aff = 0;
        try {
            // Date now = DateTime.now().toDate();
            Timestamp now = new Timestamp(DateTime.now().getMillis());

            record.setMdtime(now);
            aff = cmsFileMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("{}", e);
            throw new ServiceException("update one cms file error", e);
        }
        return aff;
    }

    @Override
    public int save(MultipartFile[] files, CmsFile record) {
        int aff = 0;
        try {
            DateTime now = DateTime.now();
            String cruser = record.getCruser();
            String owner = record.getOwner();
            String path = now.toString("yyyy/MM/dd");
            File dir = new File(SAVE_PATH.concat("/").concat(path));
            if (!dir.exists()) {
                dir.mkdirs();
            }
//            dir.getAbsolutePath();
//            dir.getCanonicalPath(); relative_path
            
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {

                    String originalFilename = file.getOriginalFilename();
                    Long size = Long.valueOf(file.getSize());
                    String ext = Files.getFileExtension(originalFilename.toLowerCase());
                    // file.getContentType(); // jpg,jpeg,png | doc,docx,xls,xlsx,ppt,pptx,pdf | zip,rar
                    String type = dataType.get(ext);
                    if(!StringUtils.hasText(type)){
                        type = "application/octet-stream";
                    }
                    String name = UUID.randomUUID().toString().concat(".").concat(ext);
                    File dest = new File(dir,name);
                    file.transferTo(dest);

                    logger.info("originalFilename:{},size:{},type:{}",originalFilename,size,type);

                    CmsFile r = new CmsFile();
                    r.setOriginalFileName(originalFilename);
                    r.setFileName(name);
                    r.setFileType(type);
                    r.setFileSize(size);
                    r.setRelativePath("/".concat(path));
                    r.setSavePath(dir.getAbsolutePath());
                    r.setFileSha1(Files.hash(dest, Hashing.sha1()).toString());

                    r.setOwner(owner);
                    r.setDeleted(Boolean.FALSE);
                    r.setCrtime(new Timestamp(now.getMillis()));
                    r.setCruser(cruser);
                    
                    aff += insert(r);

                }
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("upload file(s) and save cms file(s) error", e);
        }
        return aff;
    }

    @Override
    public int remove(CmsFile record) {
        int aff = 0;
        try {
            CmsFile clone = selectByPrimaryKey(record.getId());

            clone.setDeleted(Boolean.TRUE);
            clone.setMduser(record.getMduser());
            aff = updateByPrimaryKey(clone);

            // Long count = jdbcTemplate.queryForObject("select count(1) from
            // cms_article where deleted = 0 and parent_id = ?", Long.class,
            // record.getId());
            // if (count.longValue() == 0l) {
            // } else {
            // throw new DataIntegrityViolationException("Cannot delete a row
            // because of foreign key constraint");
            // }

        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("remove one cms file error", e);
        }
        return aff;
    }

    @Override
    public PageInfo<CmsFile> query(CmsFile record, int pageNum, int pageSize) {
        PageInfo<CmsFile> pi = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<CmsFile> list = cmsFileMapper.query(record);
            pi = new PageInfo<CmsFile>(list);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("query cms file error", e);
        }
        return pi;
    }

    @Override
    public List<CmsFile> queryByArticleId(Integer articleId) {
        List<CmsFile> records = null;
        try {
            records = cmsFileMapper.queryByArticleId(articleId);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select cms file by article id error", e);
        }
        return records;
    }

    @Override
    public List<CmsFile> queryByFileSha1(String fileSha1) {
        List<CmsFile> records = null;
        try {
            records = cmsFileMapper.queryByFileSha1(fileSha1);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select cms file by file sha1 error", e);
        }
        return records;
    }
}
