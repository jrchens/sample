package me.sample.sys.domain;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1040681594176005043L;

    private Integer id;

    private String email;

    private String username;

    private String viewname;

    private String password;

    private String passwordSalt;

    private String groupname;
    private String rolename;

    private Integer state;

    private Boolean disabled;

    private Boolean deleted;

    private String cruser;

    private Date crtime;

    private String mduser;

    private Date mdtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname == null ? null : viewname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser == null ? null : cruser.trim();
    }

    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public String getMduser() {
        return mduser;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser == null ? null : mduser.trim();
    }

    public Date getMdtime() {
        return mdtime;
    }

    public void setMdtime(Date mdtime) {
        this.mdtime = mdtime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crtime == null) ? 0 : crtime.hashCode());
        result = prime * result + ((cruser == null) ? 0 : cruser.hashCode());
        result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
        result = prime * result + ((disabled == null) ? 0 : disabled.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((groupname == null) ? 0 : groupname.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mdtime == null) ? 0 : mdtime.hashCode());
        result = prime * result + ((mduser == null) ? 0 : mduser.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((passwordSalt == null) ? 0 : passwordSalt.hashCode());
        result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        SysUser other = (SysUser) obj;
        if (crtime == null) {
            if (other.crtime != null)
                return false;
        } else if (!crtime.equals(other.crtime))
            return false;
        if (cruser == null) {
            if (other.cruser != null)
                return false;
        } else if (!cruser.equals(other.cruser))
            return false;
        if (deleted == null) {
            if (other.deleted != null)
                return false;
        } else if (!deleted.equals(other.deleted))
            return false;
        if (disabled == null) {
            if (other.disabled != null)
                return false;
        } else if (!disabled.equals(other.disabled))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (groupname == null) {
            if (other.groupname != null)
                return false;
        } else if (!groupname.equals(other.groupname))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (mdtime == null) {
            if (other.mdtime != null)
                return false;
        } else if (!mdtime.equals(other.mdtime))
            return false;
        if (mduser == null) {
            if (other.mduser != null)
                return false;
        } else if (!mduser.equals(other.mduser))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (passwordSalt == null) {
            if (other.passwordSalt != null)
                return false;
        } else if (!passwordSalt.equals(other.passwordSalt))
            return false;
        if (rolename == null) {
            if (other.rolename != null)
                return false;
        } else if (!rolename.equals(other.rolename))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
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
        builder.append("SysUser [id=").append(id).append(", email=").append(email).append(", username=")
                .append(username).append(", viewname=").append(viewname).append(", password=").append(password)
                .append(", passwordSalt=").append(passwordSalt).append(", groupname=").append(groupname)
                .append(", rolename=").append(rolename).append(", state=").append(state).append(", disabled=")
                .append(disabled).append(", deleted=").append(deleted).append(", cruser=").append(cruser)
                .append(", crtime=").append(crtime).append(", mduser=").append(mduser).append(", mdtime=")
                .append(mdtime).append("]");
        return builder.toString();
    }

}