package org.apache.shiro.realm;

import java.util.List;

import javax.sql.DataSource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.StatelessToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.io.BaseEncoding;

import io.jsonwebtoken.Jwts;

public class StatelessRealm extends AuthorizingRealm {

    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        logger.info("StatelessRealm.doGetAuthorizationInfo.username:{}", username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roles = jdbcTemplate.queryForList("select rolename from sys_user_role where username = ?",
                String.class, username);
        authorizationInfo.addRoles(roles);

        StringBuffer sqlBuffer = new StringBuffer();
        for (String role:roles
             ) {
            sqlBuffer.append("rolename = ? or ");
        }
        int length = sqlBuffer.length();
        if(length > 0){
            sqlBuffer.delete(length-4,length);
            sqlBuffer.insert(0,"SELECT permission_code FROM sys_role_permission where ");
            List<String> permissions = jdbcTemplate.queryForList(sqlBuffer.toString(),
                    String.class, roles.toArray());
            authorizationInfo.addStringPermissions(permissions);
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
        logger.info("StatelessRealm.doGetAuthenticationInfo.statelessToken:{}", statelessToken);
        String user = statelessToken.getUsername();
        String jwtoken = statelessToken.getJwtoken();
        logger.info("StatelessRealm.doGetAuthenticationInfo.user:{},jwtoken:{}", user, jwtoken);

        String passwordSalt = jdbcTemplate.queryForObject(
                "select password_salt from sys_user where deleted = 0 and username = ?", String.class, user);

        String subject = Jwts.parser().setSigningKey(BaseEncoding.base64().encode(passwordSalt.getBytes()))
                .parseClaimsJws(jwtoken).getBody().getSubject();

        String credentials = subject.equals(user) ? jwtoken : null;
        // String key = getKey(user);//根据用户名获取密钥（和客户端的一样）
        // //在服务器端生成客户端参数消息摘要
        // String serverDigest = HmacSHA256Utils.digest(key,
        // statelessToken.getParams());
        // 然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(user, credentials, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

}
