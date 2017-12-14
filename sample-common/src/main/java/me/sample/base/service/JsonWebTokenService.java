package me.sample.base.service;

public interface JsonWebTokenService {
    public String generateJWToken(String sub, String jti, String passwordSalt);
    public String getJWToken(String sub);
    public void delJWToken(String sub);
}
