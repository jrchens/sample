package me.sample.cms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CmsArticle implements Serializable {
    private Integer id;

    private Integer parentId;

    private String categoryNodename;

    private String title;

    private String summary;

    private Date pubDate;

    private String articleFrom;

    private String articleEditor;

    private Integer state;

    private Boolean disabled;

    private Boolean deleted;

    private String cruser;

    private Timestamp crtime;

    private String mduser;

    private Timestamp mdtime;

    private String richContent;

    private static final long serialVersionUID = 1L;

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

    public String getCategoryNodename() {
        return categoryNodename;
    }

    public void setCategoryNodename(String categoryNodename) {
        this.categoryNodename = categoryNodename == null ? null : categoryNodename.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getArticleFrom() {
        return articleFrom;
    }

    public void setArticleFrom(String articleFrom) {
        this.articleFrom = articleFrom == null ? null : articleFrom.trim();
    }

    public String getArticleEditor() {
        return articleEditor;
    }

    public void setArticleEditor(String articleEditor) {
        this.articleEditor = articleEditor == null ? null : articleEditor.trim();
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

    public Timestamp getCrtime() {
        return crtime;
    }

    public void setCrtime(Timestamp crtime) {
        this.crtime = crtime;
    }

    public String getMduser() {
        return mduser;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser == null ? null : mduser.trim();
    }

    public Timestamp getMdtime() {
        return mdtime;
    }

    public void setMdtime(Timestamp mdtime) {
        this.mdtime = mdtime;
    }

    public String getRichContent() {
        return richContent;
    }

    public void setRichContent(String richContent) {
        this.richContent = richContent == null ? null : richContent.trim();
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
        CmsArticle other = (CmsArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getCategoryNodename() == null ? other.getCategoryNodename() == null : this.getCategoryNodename().equals(other.getCategoryNodename()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getPubDate() == null ? other.getPubDate() == null : this.getPubDate().equals(other.getPubDate()))
            && (this.getArticleFrom() == null ? other.getArticleFrom() == null : this.getArticleFrom().equals(other.getArticleFrom()))
            && (this.getArticleEditor() == null ? other.getArticleEditor() == null : this.getArticleEditor().equals(other.getArticleEditor()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCruser() == null ? other.getCruser() == null : this.getCruser().equals(other.getCruser()))
            && (this.getCrtime() == null ? other.getCrtime() == null : this.getCrtime().equals(other.getCrtime()))
            && (this.getMduser() == null ? other.getMduser() == null : this.getMduser().equals(other.getMduser()))
            && (this.getMdtime() == null ? other.getMdtime() == null : this.getMdtime().equals(other.getMdtime()))
            && (this.getRichContent() == null ? other.getRichContent() == null : this.getRichContent().equals(other.getRichContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getCategoryNodename() == null) ? 0 : getCategoryNodename().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getPubDate() == null) ? 0 : getPubDate().hashCode());
        result = prime * result + ((getArticleFrom() == null) ? 0 : getArticleFrom().hashCode());
        result = prime * result + ((getArticleEditor() == null) ? 0 : getArticleEditor().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCruser() == null) ? 0 : getCruser().hashCode());
        result = prime * result + ((getCrtime() == null) ? 0 : getCrtime().hashCode());
        result = prime * result + ((getMduser() == null) ? 0 : getMduser().hashCode());
        result = prime * result + ((getMdtime() == null) ? 0 : getMdtime().hashCode());
        result = prime * result + ((getRichContent() == null) ? 0 : getRichContent().hashCode());
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
        sb.append(", categoryNodename=").append(categoryNodename);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append(", pubDate=").append(pubDate);
        sb.append(", articleFrom=").append(articleFrom);
        sb.append(", articleEditor=").append(articleEditor);
        sb.append(", state=").append(state);
        sb.append(", disabled=").append(disabled);
        sb.append(", deleted=").append(deleted);
        sb.append(", cruser=").append(cruser);
        sb.append(", crtime=").append(crtime);
        sb.append(", mduser=").append(mduser);
        sb.append(", mdtime=").append(mdtime);
        sb.append(", richContent=").append(richContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}