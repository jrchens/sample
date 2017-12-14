package org.apache.shiro.authc;

public class StatelessToken implements AuthenticationToken {

    /**
     * 
     */
    private static final long serialVersionUID = 7326400571702582018L;
    private String username;
    private String jwtoken;

    public StatelessToken() {
        super();
    }

    public StatelessToken(String username, String jwtoken) {
        super();
        this.username = username;
        this.jwtoken = jwtoken;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return jwtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtoken() {
        return jwtoken;
    }

    public void setJwtoken(String jwtoken) {
        this.jwtoken = jwtoken;
    }

}
