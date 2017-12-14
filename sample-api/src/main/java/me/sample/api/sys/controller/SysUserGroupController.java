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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;
import me.sample.sys.domain.SysGroup;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserGroup;
import me.sample.sys.service.SysGroupService;
import me.sample.sys.service.SysUserGroupService;
import me.sample.sys.service.SysUserService;

@Controller
@RequestMapping(value = "sys/sys_user_group")
public class SysUserGroupController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserGroupController.class);

    @Autowired
    private SysUserGroupService sysUserGroupService;
    @Autowired
    private SysGroupService sysGroupService;
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, @RequestHeader String user,
            @RequestParam String username) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, username);
        Map<String, Object> res = Maps.newHashMap();
        try {
            SysUser sysUser = sysUserService.selectByUsername(username);
            List<SysGroup> groups = sysUserGroupService.queryByUsername(username);
            for (SysGroup sysGroup : groups) {
                if(sysGroup.getGroupname().equals(sysUser.getGroupname())){
                    sysGroup.setId(0);
                }
            }
            Map<String, Object> data = Maps.newHashMap();
            data.put("total", groups.size());
            data.put("rows", groups);

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

    @RequestMapping(value = "query_ext.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> queryExt(WebRequest webRequest, @RequestHeader String user,
            @RequestParam String username) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, username);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<SysGroup> groups = sysUserGroupService.queryByUsername(username);

            List<SysGroup> all = sysGroupService.selectAll();

            List<SysGroup> groupList = Lists.newArrayList();
            
            for (SysGroup sysGroup : all) {
                boolean bool = true;
                String groupname = sysGroup.getGroupname();
                for (SysGroup g : groups) {
                    if(groupname.equals(g.getGroupname())){
                        bool = false;
                        break;
                    }
                }
                if(bool){
                    groupList.add(sysGroup);
                }
            }

            Map<String, Object> data = Maps.newHashMap();
            data.put("total", groupList.size());
            data.put("rows", groupList);

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

    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(WebRequest webRequest, 
            @RequestHeader String user, 
            @RequestParam String username, 
            @RequestParam String groupname, 
            SysUser record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setCruser(user);
            int aff = sysUserGroupService.insert(new SysUserGroup(username, groupname));
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
            @RequestParam String username,
            @RequestParam String groupname
            ) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{}", user, username,groupname);
        Map<String, Object> res = Maps.newHashMap();
        try {
            int aff = sysUserGroupService.deleteByUserGroupname(username, groupname);
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
