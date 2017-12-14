package me.sample.util;

public class JsonWebTokenUtil {
    // private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenUtil.class);

    private static class JsonWebTokenUtilHolder {
        private static final JsonWebTokenUtil INSTANCE = new JsonWebTokenUtil();
    }

    private JsonWebTokenUtil() {
    }

    public static final JsonWebTokenUtil getInstance() {
        return JsonWebTokenUtilHolder.INSTANCE;
    }

    // private static final DateTime now = DateTime.now();
//    private static final String iss = "http://local.com/api";
    // private static final String sub = "admin";
//    private static final String aud = "http://static.local.com/jeasyui";
    // private static final Date iat = now.toDate();
    // private static final Date exp = now.plusHours(2).toDate();
    // private static final Date nbf = null;
    // private static final String jti = UUID.randomUUID().toString();
    // private static final String passwordSalt = "CLN41KXp";

//    public String generateJWToken(String sub, String jti, String passwordSalt) {
//
//        logger.info("generate jwtoken");
//
//        DateTime now = DateTime.now();
//        Date iat = now.toDate();
//        Date exp = now.plusHours(2).toDate();
//        Date nbf = null;
//
//        String jwtoken = Jwts.builder().setIssuer(iss).setSubject(sub).setAudience(aud).setIssuedAt(iat)
//                .setExpiration(exp).setNotBefore(nbf).setId(jti)
//                .signWith(SignatureAlgorithm.HS256, BaseEncoding.base64().encode(passwordSalt.getBytes())).compact();
//
//        return jwtoken;
//    }

//    public String getJWToken(String user) {
//        logger.info("get jwtoken");
//        Cache cache = JsonWebTokenUtil.getInstance().getCacheManager().getCache("json_web_token");
//        ValueWrapper vw = cache.get(user);
//        return (String) vw.get();
//    }
}
