package me.sample.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.sample.sys.domain.SysUser;
import me.sample.sys.service.SysUserService;

/**
 * 
 * @author ChenSheng
 *
 */

@Controller
@RequestMapping(value = "sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index(Model model) {
        return "sys/user/index";
    }

    @RequestMapping(value = "query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(
            SysUser record, 
            @RequestParam(defaultValue="1",required=false) int pageNum, 
            @RequestParam(defaultValue="10",required=false) int pageSize) {
        Map<String, Object> res = Maps.newHashMap();
        try {
//             String user =
//             ObjectUtils.getDisplayString(SecurityUtils.getSubject().getPrincipal());
            PageInfo<SysUser> pi = sysUserService.query(record, pageNum, pageSize);
            res.put("code", 0);
            res.put("msg", "");
            res.put("count", pi.getTotal());
            res.put("data", pi.getList());
        } catch (Exception e) {
            res.put("code", 0);
            res.put("msg", e.getMessage());
            res.put("count", 0);
            res.put("data", Lists.newArrayList());
        }
        return ResponseEntity.ok(res);
    }

}
