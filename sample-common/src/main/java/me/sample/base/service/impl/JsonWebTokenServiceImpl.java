package me.sample.base.service.impl;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.BaseEncoding;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.sample.base.service.JsonWebTokenService;

@Service
@Transactional
public class JsonWebTokenServiceImpl implements JsonWebTokenService {

    // private static final DateTime now = DateTime.now();
    private static final String iss = "http://local.com/api";
    // private static final String sub = "admin";
    private static final String aud = "http://static.local.com/jeasyui";
    // private static final Date iat = now.toDate();
    // private static final Date exp = now.plusHours(2).toDate();
    // private static final Date nbf = null;
    // private static final String jti = UUID.randomUUID().toString();
    // private static final String passwordSalt = "CLN41KXp";
    
    @Override
    @Cacheable(value="json_web_token",key="#sub")
    public String generateJWToken(String sub, String jti, String passwordSalt) {

        DateTime now = DateTime.now();
        Date iat = now.toDate();
        Date exp = now.plusHours(2).toDate();
        Date nbf = null;

        String jwtoken = Jwts.builder()
                .setIssuer(iss)
                .setSubject(sub)
                .setAudience(aud)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .setNotBefore(nbf)
                .setId(jti)
                .signWith(SignatureAlgorithm.HS256, BaseEncoding.base64().encode(passwordSalt.getBytes()))
                .compact();

        return jwtoken;
    }

    @Override
    @Cacheable(value="json_web_token",key="#sub")
    public String getJWToken(String sub) {
        return null;
    }

    @Override
    @CacheEvict(value="json_web_token",key="#sub")
    public void delJWToken(String sub) {
    }

}
