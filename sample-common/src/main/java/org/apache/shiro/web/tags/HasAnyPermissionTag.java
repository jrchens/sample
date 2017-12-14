package org.apache.shiro.web.tags;

import org.apache.shiro.subject.Subject;

public class HasAnyPermissionTag extends PermissionTag {

    /**
     * 
     */
    private static final long serialVersionUID = 4139937870852372961L;
    
    private static final String PERMISSION_NAMES_DELIMETER = ",";

    public HasAnyPermissionTag() {
    }

    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();
        if (subject != null) {
            for (String permission : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {
                if (isPermitted(permission.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }

        }
        return hasAnyRole;
        // return isPermitted(p);
    }

}
