package me.sample.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

import me.sample.base.domain.StatelessUser;
import me.sample.base.service.JsonWebTokenService;
import me.sample.base.service.StatelessUserLoginService;

@Service
public class StatelessUserLoginServiceImpl implements StatelessUserLoginService {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public StatelessUser login(String username, String password) {

        List<Map<String, Object>> users = jdbcTemplate
                .queryForList("select * from sys_user where deleted = 0 and username = ?", username);
        if (users.isEmpty()) {
            // username not exists
        }
        String _password = ObjectUtils.getDisplayString(users.get(0).get("password"));
        String passwordSalt = ObjectUtils.getDisplayString(users.get(0).get("password_salt"));
        String viewname = ObjectUtils.getDisplayString(users.get(0).get("viewname"));

        String ep = Hashing.hmacSha256(passwordSalt.getBytes()).hashBytes(password.getBytes()).toString();

        if (!ep.equals(_password)) {
            // password error
        }

        // SELECT groupname FROM `sample_dev`.`sys_user_group`

        List<String> groups = jdbcTemplate.queryForList("select groupname from sys_user_group where username = ?",
                String.class, username);
        List<String> roles = jdbcTemplate.queryForList("select rolename from sys_user_role where username = ?",
                String.class, username);

        List<String> permissions = Lists.newArrayList();
        for (String rolename : roles) {
            permissions.addAll(jdbcTemplate.queryForList(
                    "select permission_code from sys_role_permission where rolename = ?", String.class, rolename));
        }

        StatelessUser su = new StatelessUser();
        su.setUsername(username);
        su.setViewname(viewname);
        su.setGroups(groups);
        su.setRoles(roles);
        su.setPermissions(permissions);

        // jwtoken
        String jti = UUID.randomUUID().toString();
        String jwtoken = jsonWebTokenService.generateJWToken(username, jti, passwordSalt);
        su.setJwtoken(jwtoken);
        
        // private String username;
        // private String viewname;
        // private List<String> groups;
        // private List<String> roles;
        // private List<String> permissions;

        return su;
    }

}
