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

import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;
import me.sample.sys.domain.SysUser;
import me.sample.sys.service.SysUserGroupService;

@Controller
@RequestMapping(value = "sys/sys_group_user")
public class SysGroupUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysGroupUserController.class);

    @Autowired
    private SysUserGroupService sysUserGroupService;

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, @RequestHeader String user,
            @RequestParam String groupname) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, groupname);
        Map<String, Object> res = Maps.newHashMap();
        try {
            List<SysUser> users = sysUserGroupService.queryByGroupname(groupname);
            Map<String, Object> data = Maps.newHashMap();
            data.put("total", users.size());
            data.put("rows", users);

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

}
