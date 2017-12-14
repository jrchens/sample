package me.sample.base.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TreeObject implements Serializable {

//    id: An identity value bind to the node.
//    text: Text to be showed.
//    iconCls: The css class to display icon.
//    checked: Whether the node is checked.
//    state: The node state, 'open' or 'closed'.
//    attributes: Custom attributes bind to the node.
//    target: Target DOM object.
    
//    id: node id, which is important to load remote data
//    text: node text to show
//    state: node state, 'open' or 'closed', default is 'open'. When set to 'closed', the node have children nodes and will load them from remote site
//    checked: Indicate whether the node is checked selected.
//    attributes: custom attributes can be added to a node
//    children: an array nodes defines some children nodes

    /**
     * 
     */
    private static final long serialVersionUID = -731698236750838550L;
    private String id;
    private String text;
    private String state;
    private Boolean checked;
    private Map<String,Object> attributes = Maps.newHashMap();
    private List<TreeObject> children = Lists.newArrayList();
    private String iconCls;
    private String target;
    private Boolean disabled;

    public TreeObject() {
        super();
    }
//    public TreeObject(String id, String text) {
//        super();
//        this.id = id;
//        this.text = text;
//    }
    public TreeObject(String id, String text,Boolean disabled) {
        super();
        this.id = id;
        this.text = text;
        this.disabled = disabled;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public List<TreeObject> getChildren() {
        return children;
    }
    public void setChildren(List<TreeObject> children) {
        this.children = children;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public Boolean getDisabled() {
        return disabled;
    }
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + ((checked == null) ? 0 : checked.hashCode());
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((disabled == null) ? 0 : disabled.hashCode());
        result = prime * result + ((iconCls == null) ? 0 : iconCls.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        TreeObject other = (TreeObject) obj;
        if (attributes == null) {
            if (other.attributes != null)
                return false;
        } else if (!attributes.equals(other.attributes))
            return false;
        if (checked == null) {
            if (other.checked != null)
                return false;
        } else if (!checked.equals(other.checked))
            return false;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (disabled == null) {
            if (other.disabled != null)
                return false;
        } else if (!disabled.equals(other.disabled))
            return false;
        if (iconCls == null) {
            if (other.iconCls != null)
                return false;
        } else if (!iconCls.equals(other.iconCls))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TreeObject [id=").append(id).append(", text=").append(text).append(", state=").append(state)
                .append(", checked=").append(checked).append(", attributes=").append(attributes).append(", children=")
                .append(children).append(", iconCls=").append(iconCls).append(", target=").append(target)
                .append(", disabled=").append(disabled).append("]");
        return builder.toString();
    }
    
    
    
}
