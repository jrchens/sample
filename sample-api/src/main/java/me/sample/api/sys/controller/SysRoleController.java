package me.sample.api.sys.controller;

import java.util.List;
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
import me.sample.sys.domain.SysRole;
import me.sample.sys.service.SysRoleService;

@Controller
@RequestMapping(value = "sys/sys_role")
public class SysRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "query_all.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> queryAll(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam(defaultValue="1",required=false) String procDisable
            ) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{}", user);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<SysRole> pi = sysRoleService.selectAll();
            List<Map<String,Object>> data = Lists.newArrayList();
            for (SysRole sysRole : pi) {
                Map<String, Object> entry = Maps.newHashMap();
                entry.put("id", sysRole.getId());
                entry.put("rolename", sysRole.getRolename());
                entry.put("viewname", sysRole.getViewname());
                if("1".equals(procDisable)){
                    entry.put("disabled", sysRole.getDisabled());
                }
                data.add(entry);
            }
            
            res.put("success", true);
            res.put("message", "");
            res.put("data", data);
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
            @RequestHeader String user, SysRole record,
            @RequestParam(defaultValue = "false", required = false) boolean skipDisabled,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int rows) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{}", user, record, page, rows);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<SysRole> list = Lists.newArrayList();
            PageInfo<SysRole> pi = sysRoleService.query(record, page, rows);
            List<SysRole> dataList = pi.getList();
            if(skipDisabled){ 
                for (SysRole sysRole : dataList) {
                    if(!sysRole.getDisabled()){
                        list.add(sysRole);
                    }
                }
            }else{
                list.addAll(dataList);
            }
            
            Map<String, Object> data = Maps.newHashMap();
            data.put("total", pi.getTotal());
            data.put("rows", list);
            
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
    public ResponseEntity<Map<String, Object>> get(WebRequest webRequest, @RequestHeader String user, SysRole record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record = sysRoleService.selectByPrimaryKey(record.getId());

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
    public ResponseEntity<Map<String, Object>> save(WebRequest webRequest, @RequestHeader String user, SysRole record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setCruser(user);
            int aff = sysRoleService.insert(record);
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
            SysRole record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{}:{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setMduser(user);
            int aff = sysRoleService.removeByPrimaryKey(record);

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
            @RequestParam Integer id) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{}", user, viewname, id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            SysRole record = sysRoleService.selectByPrimaryKey(id);
            record.setViewname(viewname);

            record.setMduser(user);
            int aff = sysRoleService.updateByPrimaryKey(record);
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
            SysRole record = sysRoleService.selectByPrimaryKey(id);
            record.setDisabled(!record.getDisabled());

            record.setMduser(user);
            int aff = sysRoleService.updateByPrimaryKey(record);
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
