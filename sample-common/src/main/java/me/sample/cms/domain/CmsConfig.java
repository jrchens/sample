package me.sample.cms.domain;

import java.io.Serializable;

public class CmsConfig implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -9212742999634788739L;

    private String configCode;

    private String configType;

    private String configLabel;

    private String configContent;

    private String configValue;

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    public String getConfigLabel() {
        return configLabel;
    }

    public void setConfigLabel(String configLabel) {
        this.configLabel = configLabel == null ? null : configLabel.trim();
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent == null ? null : configContent.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
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
        CmsConfig other = (CmsConfig) that;
        return (this.getConfigCode() == null ? other.getConfigCode() == null : this.getConfigCode().equals(other.getConfigCode()))
            && (this.getConfigType() == null ? other.getConfigType() == null : this.getConfigType().equals(other.getConfigType()))
            && (this.getConfigLabel() == null ? other.getConfigLabel() == null : this.getConfigLabel().equals(other.getConfigLabel()))
            && (this.getConfigContent() == null ? other.getConfigContent() == null : this.getConfigContent().equals(other.getConfigContent()))
            && (this.getConfigValue() == null ? other.getConfigValue() == null : this.getConfigValue().equals(other.getConfigValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConfigCode() == null) ? 0 : getConfigCode().hashCode());
        result = prime * result + ((getConfigType() == null) ? 0 : getConfigType().hashCode());
        result = prime * result + ((getConfigLabel() == null) ? 0 : getConfigLabel().hashCode());
        result = prime * result + ((getConfigContent() == null) ? 0 : getConfigContent().hashCode());
        result = prime * result + ((getConfigValue() == null) ? 0 : getConfigValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", configCode=").append(configCode);
        sb.append(", configType=").append(configType);
        sb.append(", configLabel=").append(configLabel);
        sb.append(", configContent=").append(configContent);
        sb.append(", configValue=").append(configValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}