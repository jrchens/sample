package me.sample.cms.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsFile implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8363842167965122932L;

    private Integer id;
    
    private Integer articleId;

    private String originalFileName;

    private String fileName;

    private Long fileSize;

    private String fileType;

    private String savePath;
    
    private String relativePath;//relative_path, 

    private String fileSha1;

    private String owner;

    private Boolean deleted;

    private String cruser;

    private Timestamp crtime;

    private String mduser;

    private Timestamp mdtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName == null ? null : originalFileName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath == null ? null : savePath.trim();
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getFileSha1() {
        return fileSha1;
    }

    public void setFileSha1(String fileSha1) {
        this.fileSha1 = fileSha1 == null ? null : fileSha1.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
        result = prime * result + ((crtime == null) ? 0 : crtime.hashCode());
        result = prime * result + ((cruser == null) ? 0 : cruser.hashCode());
        result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((fileSha1 == null) ? 0 : fileSha1.hashCode());
        result = prime * result + ((fileSize == null) ? 0 : fileSize.hashCode());
        result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mdtime == null) ? 0 : mdtime.hashCode());
        result = prime * result + ((mduser == null) ? 0 : mduser.hashCode());
        result = prime * result + ((originalFileName == null) ? 0 : originalFileName.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((relativePath == null) ? 0 : relativePath.hashCode());
        result = prime * result + ((savePath == null) ? 0 : savePath.hashCode());
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
        CmsFile other = (CmsFile) obj;
        if (articleId == null) {
            if (other.articleId != null)
                return false;
        } else if (!articleId.equals(other.articleId))
            return false;
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
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (fileSha1 == null) {
            if (other.fileSha1 != null)
                return false;
        } else if (!fileSha1.equals(other.fileSha1))
            return false;
        if (fileSize == null) {
            if (other.fileSize != null)
                return false;
        } else if (!fileSize.equals(other.fileSize))
            return false;
        if (fileType == null) {
            if (other.fileType != null)
                return false;
        } else if (!fileType.equals(other.fileType))
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
        if (originalFileName == null) {
            if (other.originalFileName != null)
                return false;
        } else if (!originalFileName.equals(other.originalFileName))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (relativePath == null) {
            if (other.relativePath != null)
                return false;
        } else if (!relativePath.equals(other.relativePath))
            return false;
        if (savePath == null) {
            if (other.savePath != null)
                return false;
        } else if (!savePath.equals(other.savePath))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CmsFile [id=").append(id).append(", articleId=").append(articleId).append(", originalFileName=")
                .append(originalFileName).append(", fileName=").append(fileName).append(", fileSize=").append(fileSize)
                .append(", fileType=").append(fileType).append(", savePath=").append(savePath).append(", relativePath=")
                .append(relativePath).append(", fileSha1=").append(fileSha1).append(", owner=").append(owner)
                .append(", deleted=").append(deleted).append(", cruser=").append(cruser).append(", crtime=")
                .append(crtime).append(", mduser=").append(mduser).append(", mdtime=").append(mdtime).append("]");
        return builder.toString();
    }

}