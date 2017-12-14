package me.sample.sys.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.sample.sys.domain.SysRole;
import me.sample.sys.service.SysRoleService;

/**
 * 
 * @author ChenSheng
 *
 */

@Controller
@RequestMapping(value = "sys_role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index(WebRequest webRequest, Model model) {
        logger.info("{}",webRequest.getDescription(true));
        return "sys/role/index";
    }

    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(
            WebRequest webRequest, SysRole record, 
            @RequestParam(defaultValue="1",required=false) int page, 
            @RequestParam(defaultValue="10",required=false) int limit) {
        logger.info("{}",webRequest.getDescription(true));
        logger.info("{},{},{}",record,page,limit);
        Map<String, Object> res = Maps.newHashMap();
        try {
            PageInfo<SysRole> pi = sysRoleService.query(record, page, limit);
            res.put("code", 0);
            res.put("msg", "");
            res.put("count", pi.getTotal());
            res.put("data", pi.getList());
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", e.getMessage());
            res.put("count", 0);
            res.put("data", Lists.newArrayList());
        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }
    
    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> remove(
            WebRequest webRequest,
            SysRole record) {
        logger.info("{}",webRequest.getDescription(true));
        logger.info("{}",record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            //
            int aff = sysRoleService.removeByPrimaryKey(record);
            res.put("code", 0);
            res.put("msg", "");
            res.put("data", aff);
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", e.getMessage());
            res.put("data", 0);
        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(
            WebRequest webRequest, 
            @RequestParam String viewname,
            @RequestParam Integer id) {
        logger.info("{}",webRequest.getDescription(true));
        logger.info("{},{}",viewname,id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            SysRole record = sysRoleService.selectByPrimaryKey(id);
            record.setViewname(viewname);
            int aff = sysRoleService.updateByPrimaryKey(record);
            res.put("code", 0);
            res.put("msg", "");
            res.put("data", aff);
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", e.getMessage());
            res.put("data", 0);
        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }
    

    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save (
            WebRequest webRequest, SysRole record) {
        logger.info("{}",webRequest.getDescription(true));
        logger.info("{}",record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            int aff = sysRoleService.insert(record);
            res.put("code", 0);
            res.put("msg", "");
            res.put("data", aff);
        } catch (Exception e) {
            res.put("code", 500);
            res.put("msg", e.getMessage());
            res.put("data", 0);
        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

}
