package me.sample.exception;

import org.springframework.core.NestedRuntimeException;

public class ServiceException extends NestedRuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -1737374376387764030L;
    private String state;
    private int code;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String msg, String state, int code) {
        super(msg);
        this.state = state;
        this.code = code;
    }
}
