package com.ssmshiro.common.exception;


import com.ssmshiro.common.utils.PropertiesLoader;

/**
 * 自定义异常类，规范异常返回
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(InfoCode infoCode) {
        super(PropertiesLoader.getProperty(infoCode.toString()));
        this.errorCode = infoCode.toString();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(InfoCode infoCode, Throwable cause) {
        super(PropertiesLoader.getProperty(infoCode.toString()), cause);
        this.errorCode = infoCode.toString();
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
