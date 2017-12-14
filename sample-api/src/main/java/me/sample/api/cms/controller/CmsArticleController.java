package me.sample.api.cms.controller;

import java.sql.Date;
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
import me.sample.cms.domain.CmsArticle;
import me.sample.cms.domain.CmsCategory;
import me.sample.cms.service.CmsArticleService;
import me.sample.cms.service.CmsCategoryService;

@Controller
@RequestMapping(value = "cms/article")
public class CmsArticleController {

    private static final Logger logger = LoggerFactory.getLogger(CmsArticleController.class);

    @Autowired
    private CmsArticleService cmsArticleService;
    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;
    
    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, 
            @RequestHeader String user, 
            CmsArticle record,
            @RequestParam(defaultValue = "1", required = false) int categoryId,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int rows) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{}", user, record, page, rows);
        Map<String, Object> res = Maps.newHashMap();
        try {
            
            CmsCategory cmsCategory = cmsCategoryService.selectByPrimaryKey(categoryId);
            record.setCategoryNodename(cmsCategory.getNodename());
            
            PageInfo<CmsArticle> pi = cmsArticleService.query(record, page, rows);
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
    public ResponseEntity<Map<String, Object>> get(WebRequest webRequest, @RequestHeader String user, CmsArticle record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record = cmsArticleService.selectByPrimaryKey(record.getId());

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
    public ResponseEntity<Map<String, Object>> save(WebRequest webRequest, 
            @RequestHeader String user, 
            @RequestParam(defaultValue = "1", required = false) int categoryId,
            CmsArticle record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            CmsCategory cmsCategory = cmsCategoryService.selectByPrimaryKey(categoryId);
            record.setCategoryNodename(cmsCategory.getNodename());
            
            record.setCruser(user);
            int aff = cmsArticleService.insert(record);
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

    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> remove(WebRequest webRequest, 
            @RequestHeader String user,
            CmsArticle record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{}:{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setMduser(user);
            int aff = cmsArticleService.remove(record);

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

    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(WebRequest webRequest, 
            @RequestHeader String user,
//            @RequestParam int categoryId,
            @RequestParam String categoryNodename,
            @RequestParam String title,
            @RequestParam(required=false) String summary,
            @RequestParam Date pubDate,            
            @RequestParam(required=false) String articleFrom,
            @RequestParam(required=false) String articleEditor,
            @RequestParam Integer state,
            @RequestParam String richContent,
            @RequestParam Integer id) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{},{},{},{},{},{},{}", user, categoryNodename,title,summary,pubDate,articleFrom,articleEditor,state,richContent,id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            // categoryNodename,title,summary,pubDate,articleFrom,articleEditor,state,richContent
//            CmsCategory cmsCategory = cmsCategoryService.selectByPrimaryKey(categoryId);
//            String categoryNodename = cmsCategory.getNodename();
            
            CmsArticle record = cmsArticleService.selectByPrimaryKey(id);
            record.setCategoryNodename(categoryNodename);
            record.setTitle(title);
            record.setSummary(summary);
            record.setPubDate(pubDate);
            record.setArticleFrom(articleFrom);
            record.setArticleEditor(articleEditor);
            record.setState(state);
            record.setRichContent(richContent);

            record.setMduser(user);
            int aff = cmsArticleService.updateByPrimaryKey(record);
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

    @RequestMapping(value = "disable.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> disable(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam Integer id) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            CmsArticle record = cmsArticleService.selectByPrimaryKey(id);
            record.setDisabled(!record.getDisabled());

            record.setMduser(user);
            int aff = cmsArticleService.updateByPrimaryKey(record);
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
