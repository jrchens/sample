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
import me.sample.sys.domain.SysPermission;
import me.sample.sys.domain.SysRolePermission;
import me.sample.sys.service.SysPermissionService;
import me.sample.sys.service.SysRolePermissionService;

@Controller
@RequestMapping(value = "sys/sys_role_permission")
public class SysRolePermissionController {

    private static final Logger logger = LoggerFactory.getLogger(SysRolePermissionController.class);

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam String rolename) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, rolename);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<Integer> data = Lists.newArrayList();
            List<SysPermission> sysPermissions = sysRolePermissionService.queryByRolename(rolename);
            for (SysPermission sysPermission : sysPermissions) {
                data.add(sysPermission.getId());
            }
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
            @RequestParam boolean checked,  
            @RequestParam String rolename,
            @RequestParam Integer id) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{}", user, rolename, id);
        Map<String, Object> res = Maps.newHashMap();
        try {
            SysPermission sysPermission = sysPermissionService.selectByPrimaryKey(id);
            int aff = 0;
            if(checked) {
                aff = sysRolePermissionService.insert(new SysRolePermission(rolename, sysPermission.getPermissionCode())); 
            } else {
                aff = sysRolePermissionService.deleteByRolenamePermissionCode(rolename, sysPermission.getPermissionCode());  
            }
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
