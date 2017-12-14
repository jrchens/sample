package me.sample.api.sys.controller;

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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;
import me.sample.sys.domain.SysPermission;
import me.sample.sys.service.SysPermissionService;

@Controller
@RequestMapping(value = "sys/sys_permission")
public class SysPermissionController {

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "query_tree.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> queryAll(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam(defaultValue="0",required=false) Integer id
            ) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{}", user);
        Map<String, Object> res = Maps.newHashMap();
        try {

//            List<TreeObject> data = Lists.newArrayList();
//            
//            List<SysPermission> list = sysPermissionService.query(id);
//            for (SysPermission sysPermission : list) {
//                TreeObject node = new TreeObject();
//                node.setId(String.valueOf(sysPermission.getId()));
//                node.setText(sysPermission.getViewname());
//                node.setState("open");
//                
//                List<SysPermission> subList = sysPermissionService.query(sysPermission.getId());
//                List<TreeObject> children = Lists.newArrayList();
//                for (SysPermission perm : subList) {
//                    TreeObject subNode = new TreeObject();
//                    subNode.setId(String.valueOf(perm.getId()));
//                    subNode.setText(perm.getViewname());
//                    
//                    List<SysPermission> _list = sysPermissionService.query(perm.getId());
//                    if(_list.size() > 0){
//                        subNode.setState("closed");
//                    }
//                    
//                    children.add(subNode);
//                }
//                node.setChildren(children);
//                
//                data.add(node);
//            }
            
            res.put("success", true);
            res.put("message", "");
            res.put("data", sysPermissionService.load(id));
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
            res.put("data", Lists.newArrayList());
        } finally {
            res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }
    
    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, 
            @RequestHeader String user, 
            SysPermission record,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int rows) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{}", user, record, page, rows);
        Map<String, Object> res = Maps.newHashMap();
        try {
            PageInfo<SysPermission> pi = sysPermissionService.query(record, page, rows);
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
    public ResponseEntity<Map<String, Object>> get(WebRequest webRequest, @RequestHeader String user, SysPermission record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record = sysPermissionService.selectByPrimaryKey(record.getId());

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
            SysPermission record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setCruser(user);
            int aff = sysPermissionService.insert(record);
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
    public ResponseEntity<Map<String, Object>> remove(WebRequest webRequest, @RequestHeader String user,
            SysPermission record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{}:{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setMduser(user);
            int aff = sysPermissionService.removeByPrimaryKey(record);

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
            @RequestParam String viewname,
            @RequestParam String permissionContent,
            @RequestParam Integer id) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{}", user, viewname, id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            SysPermission record = sysPermissionService.selectByPrimaryKey(id);
            record.setViewname(viewname);
            record.setPermissionContent(permissionContent);

            record.setMduser(user);
            int aff = sysPermissionService.updateByPrimaryKey(record);
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
            SysPermission record = sysPermissionService.selectByPrimaryKey(id);
            record.setDisabled(!record.getDisabled());

            record.setMduser(user);
            int aff = sysPermissionService.updateByPrimaryKey(record);
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
