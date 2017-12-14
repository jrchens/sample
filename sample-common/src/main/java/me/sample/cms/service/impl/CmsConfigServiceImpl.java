package me.sample.cms.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import me.sample.cms.domain.CmsConfig;
import me.sample.cms.mapper.CmsConfigMapper;
import me.sample.cms.service.CmsConfigService;
import me.sample.exception.ServiceException;

@Service
public class CmsConfigServiceImpl implements CmsConfigService {
    private static final Logger logger = LoggerFactory.getLogger(CmsConfigService.class);

    @Autowired
    private CmsConfigMapper cmsConfigMapper;
//    @Autowired
//    private SequenceService sequenceService;

//    private JdbcTemplate jdbcTemplate;
//    
//    @Autowired
//    public void setDataSource(DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

//    @Override
//    public int deleteByPrimaryKey(String configCode) {
//        int aff = 0;
//        try {
//            aff = cmsConfigMapper.deleteByPrimaryKey(configCode);
//        } catch (Exception e) {
//            logger.error("", e);
//            throw new ServiceException("delete one cms config error",e);
//        }
//        return aff;
//    }

    @Override
    public int insert(CmsConfig record) {
        int aff = 0;
        try {
            record.setConfigCode(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            aff = cmsConfigMapper.insert(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("insert one cms config error",e);
        }
        return aff;
    }

    @Override
    public CmsConfig selectByPrimaryKey(String configCode) {
        CmsConfig record = null;
        try {
            record = cmsConfigMapper.selectByPrimaryKey(configCode);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select one cms config error",e);
        }
        return record;
    }

    @Override
    public List<CmsConfig> selectAll() {
        List<CmsConfig> records = null;
        try {
            records = cmsConfigMapper.selectAll();
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select all cms config error",e);
        }
        return records;
    }

    @Override
    public int updateByPrimaryKey(CmsConfig record) {
        int aff = 0;
        try {
            aff = cmsConfigMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("update one cms config error",e);
        }
        return aff;
    }
    
    @Override
    public PageInfo<CmsConfig> query(CmsConfig record) {
        PageInfo<CmsConfig> pi = new PageInfo<CmsConfig>();
        try {
            List<CmsConfig> records = cmsConfigMapper.query(record);
            pi.setTotal(records.size());
            pi.setList(records);
        } catch (Exception e) {
            logger.error("", e);
            throw new ServiceException("select cms config error",e);
        }
        return pi;
    }
}