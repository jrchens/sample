package me.sample.api.cms.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;
import me.sample.cms.domain.CmsConfig;
import me.sample.cms.service.CmsConfigService;

@Controller
@RequestMapping(value = "cms/config")
public class CmsConfigController {

    private static final Logger logger = LoggerFactory.getLogger(CmsConfigController.class);

    @Autowired
    private CmsConfigService cmsConfigService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;
    
    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(
            WebRequest webRequest, 
            @RequestHeader String user, 
            CmsConfig record/*,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int rows*/) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            
            PageInfo<CmsConfig> pi = cmsConfigService.query(record);
            Map<String, Object> data = Maps.newHashMap();
            data.put("total", pi.getTotal());
            data.put("rows", pi.getList());
            
            res.put("success", true);
            res.put("message", "");
            res.put("data", data);
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
            res.put("data", "");
        } finally {
            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "get.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> get(WebRequest webRequest, @RequestHeader String user, CmsConfig record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record = cmsConfigService.selectByPrimaryKey(record.getConfigCode());

            res.put("success", true);
            res.put("message", "");
            res.put("data", record);
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
            res.put("data", e.getMessage());
        } finally {
            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(
            WebRequest webRequest, 
            @RequestHeader String user, 
            CmsConfig record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            int aff = cmsConfigService.insert(record);
            res.put("success", true);
            res.put("message", "");
            res.put("data", aff);
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
            res.put("data", 0);
        } finally {
            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

//    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> remove(WebRequest webRequest, 
//            @RequestHeader String user,
//            CmsConfig record) {
//        logger.info("{}", webRequest.getDescription(true));
//        logger.info("{}:{}", user, record);
//        Map<String, Object> res = Maps.newHashMap();
//        try {
//            int aff = cmsConfigService.deleteByPrimaryKey(record.getConfigCode());
//            res.put("success", true);
//            res.put("message", "");
//            res.put("data", aff);
//        } catch (Exception e) {
//            res.put("success", false);
//            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
//            res.put("data", 0);
//        } finally {
//            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
//        }
//        return ResponseEntity.ok(res);
//    }

    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam(required=false) String configType,
            @RequestParam(required=false) String configLabel,
            @RequestParam(required=false) String configContent,
            @RequestParam(required=false) String configValue,
            @RequestParam(required=false) String configCode
            ) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{},{},{}", user, configType,configLabel,configContent,configValue,configCode);
        Map<String, Object> res = Maps.newHashMap();
        try {
            // categoryNodename,title,summary,pubDate,articleFrom,articleEditor,state,richContent
//            CmsCategory cmsCategory = cmsCategoryService.selectByPrimaryKey(categoryId);
//            String categoryNodename = cmsCategory.getNodename();
            
            CmsConfig record = cmsConfigService.selectByPrimaryKey(configCode);
            record.setConfigType(configType);
            record.setConfigLabel(configLabel);
            record.setConfigContent(configContent);
            record.setConfigValue(configValue);

            int aff = cmsConfigService.updateByPrimaryKey(record);
            res.put("success", true);
            res.put("message", "");
            res.put("data", aff);
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
            res.put("data", 0);
        } finally {
            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

}
