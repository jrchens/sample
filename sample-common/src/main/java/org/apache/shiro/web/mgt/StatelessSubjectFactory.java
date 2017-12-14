package org.apache.shiro.web.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }

}
