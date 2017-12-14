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
import me.sample.sys.domain.SysRole;
import me.sample.sys.domain.SysUser;
import me.sample.sys.domain.SysUserRole;
import me.sample.sys.service.SysRoleService;
import me.sample.sys.service.SysUserRoleService;
import me.sample.sys.service.SysUserService;

@Controller
@RequestMapping(value = "sys/sys_user_role")
public class SysUserRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleController.class);

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
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
            List<SysRole> roles = sysUserRoleService.queryByUsername(username);
            for (SysRole sysRole : roles) {
                if(sysRole.getRolename().equals(sysUser.getRolename())){
                    sysRole.setId(0);
                }
            }
            Map<String, Object> data = Maps.newHashMap();
            data.put("total", roles.size());
            data.put("rows", roles);

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
    public ResponseEntity<Map<String, Object>> queryExt(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam String username) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, username);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<SysRole> roles = sysUserRoleService.queryByUsername(username);

            List<SysRole> all = sysRoleService.selectAll();

            List<SysRole> roleList = Lists.newArrayList();
            
            for (SysRole sysRole : all) {
                boolean bool = true;
                String rolename = sysRole.getRolename();
                for (SysRole g : roles) {
                    if(rolename.equals(g.getRolename())){
                        bool = false;
                        break;
                    }
                }
                if(bool){
                    roleList.add(sysRole);
                }
            }

            Map<String, Object> data = Maps.newHashMap();
            data.put("total", roleList.size());
            data.put("rows", roleList);

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
            @RequestParam String rolename, 
            SysUser record) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, record);
        Map<String, Object> res = Maps.newHashMap();
        try {
            record.setCruser(user);
            int aff = sysUserRoleService.insert(new SysUserRole(username, rolename));
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
            @RequestParam String rolename
            ) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{}", user, username,rolename);
        Map<String, Object> res = Maps.newHashMap();
        try {
            int aff = sysUserRoleService.deleteByUserRolename(username, rolename);
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
