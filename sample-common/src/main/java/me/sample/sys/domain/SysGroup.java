package me.sample.sys.domain;

import java.io.Serializable;
import java.util.Date;

public class SysGroup implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6931206706945703784L;

    private Integer id;

    private String groupname;

    private String viewname;

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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname == null ? null : viewname.trim();
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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysGroup other = (SysGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupname() == null ? other.getGroupname() == null : this.getGroupname().equals(other.getGroupname()))
            && (this.getViewname() == null ? other.getViewname() == null : this.getViewname().equals(other.getViewname()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCruser() == null ? other.getCruser() == null : this.getCruser().equals(other.getCruser()))
            && (this.getCrtime() == null ? other.getCrtime() == null : this.getCrtime().equals(other.getCrtime()))
            && (this.getMduser() == null ? other.getMduser() == null : this.getMduser().equals(other.getMduser()))
            && (this.getMdtime() == null ? other.getMdtime() == null : this.getMdtime().equals(other.getMdtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupname() == null) ? 0 : getGroupname().hashCode());
        result = prime * result + ((getViewname() == null) ? 0 : getViewname().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCruser() == null) ? 0 : getCruser().hashCode());
        result = prime * result + ((getCrtime() == null) ? 0 : getCrtime().hashCode());
        result = prime * result + ((getMduser() == null) ? 0 : getMduser().hashCode());
        result = prime * result + ((getMdtime() == null) ? 0 : getMdtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupname=").append(groupname);
        sb.append(", viewname=").append(viewname);
        sb.append(", disabled=").append(disabled);
        sb.append(", deleted=").append(deleted);
        sb.append(", cruser=").append(cruser);
        sb.append(", crtime=").append(crtime);
        sb.append(", mduser=").append(mduser);
        sb.append(", mdtime=").append(mdtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}