package me.sample.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class CmsCategory implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3672209461689660572L;

    private Integer id;

    private Integer parentId;

    private String nodename;

    private String viewname;

    private String nodeLink;

    private Integer nodeLevel;

    private String fullPath;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname == null ? null : viewname.trim();
    }

    public String getNodeLink() {
        return nodeLink;
    }

    public void setNodeLink(String nodeLink) {
        this.nodeLink = nodeLink == null ? null : nodeLink.trim();
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
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
        CmsCategory other = (CmsCategory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getNodename() == null ? other.getNodename() == null : this.getNodename().equals(other.getNodename()))
            && (this.getViewname() == null ? other.getViewname() == null : this.getViewname().equals(other.getViewname()))
            && (this.getNodeLink() == null ? other.getNodeLink() == null : this.getNodeLink().equals(other.getNodeLink()))
            && (this.getNodeLevel() == null ? other.getNodeLevel() == null : this.getNodeLevel().equals(other.getNodeLevel()))
            && (this.getFullPath() == null ? other.getFullPath() == null : this.getFullPath().equals(other.getFullPath()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
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
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getNodename() == null) ? 0 : getNodename().hashCode());
        result = prime * result + ((getViewname() == null) ? 0 : getViewname().hashCode());
        result = prime * result + ((getNodeLink() == null) ? 0 : getNodeLink().hashCode());
        result = prime * result + ((getNodeLevel() == null) ? 0 : getNodeLevel().hashCode());
        result = prime * result + ((getFullPath() == null) ? 0 : getFullPath().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
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
        sb.append(", parentId=").append(parentId);
        sb.append(", nodename=").append(nodename);
        sb.append(", viewname=").append(viewname);
        sb.append(", nodeLink=").append(nodeLink);
        sb.append(", nodeLevel=").append(nodeLevel);
        sb.append(", fullPath=").append(fullPath);
        sb.append(", state=").append(state);
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