package me.sample.base.domain;

import java.io.Serializable;
import java.util.List;

public class StatelessUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 637481229500823186L;
    private String username;
    private String viewname;
    private String jwtoken;
    
    private List<String> groups;
    private List<String> roles;
    private List<String> permissions;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getViewname() {
        return viewname;
    }
    public void setViewname(String viewname) {
        this.viewname = viewname;
    }
    public String getJwtoken() {
        return jwtoken;
    }
    public void setJwtoken(String jwtoken) {
        this.jwtoken = jwtoken;
    }
    public List<String> getGroups() {
        return groups;
    }
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((viewname == null) ? 0 : viewname.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatelessUser other = (StatelessUser) obj;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (permissions == null) {
            if (other.permissions != null)
                return false;
        } else if (!permissions.equals(other.permissions))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (viewname == null) {
            if (other.viewname != null)
                return false;
        } else if (!viewname.equals(other.viewname))
            return false;
        return true;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StatelessUser [username=").append(username).append(", viewname=").append(viewname)
                .append(", groups=").append(groups).append(", roles=").append(roles).append(", permissions=")
                .append(permissions).append("]");
        return builder.toString();
    }
    
    
}
