package com.ssmshiro.common.response;

import com.ssmshiro.common.exception.InfoCode;
import com.ssmshiro.common.exception.ServiceException;
import com.ssmshiro.common.utils.PropertiesLoader;
import com.ssmshiro.user.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * 封装请求的结果，规范接口返回
 *
 * @param <T>
 */
public class ResultEntity<T> {

    private static final String SUCCESS = "success";

    private static final String ERROR = "error";

    private String result;

    private String errorcode;

    private String message;

    private String token;

    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 正常业务返回
     * @param code
     * @return
     */
    public ResultEntity<T> OK(InfoCode code) {
        return OK(null, code);
    }

    /**
     * 正常业务返回
     * @param data
     * @return
     */
    public ResultEntity<T> OK(T data) {
        return OK(data, null);
    }

    /**
     * 正常业务返回
     * @param data
     * @param code
     * @return
     */
    public ResultEntity<T> OK(T data, InfoCode code) {
        this.setResult(ResultEntity.SUCCESS);
        if (data != null) {
            this.setData(data);
        }
        if (code != null) {
            this.setMessage(PropertiesLoader.getProperty(code.toString()));
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            this.setToken(user.getToken());
        }
        this.setErrorcode(code.toString());

        return this;
    }

    /**
     * 系统异常构造方法
     * @param exception
     * @return
     */
    public ResultEntity<T> ERROR(Exception exception){
        this.setResult(ResultEntity.ERROR);
        this.setMessage(PropertiesLoader.getProperty(InfoCode.ESYS9999.toString()));
        this.setErrorcode(InfoCode.ESYS9999.toString());
        return this;
    }

    /**
     * 异常构造方法
     * @param serviceException
     * @return
     */
    public ResultEntity<T> ERROR(ServiceException serviceException){
        this.setResult(ResultEntity.ERROR);
        this.setMessage(serviceException.getMessage());
        this.setErrorcode(serviceException.getErrorCode());
        return this;
    }

    /**
     * 异常构造方法
     * @param serviceException
     * @return
     */
    public ResultEntity<T> ERROR(T data, ServiceException serviceException){
        this.setResult(ResultEntity.ERROR);
        this.setData(data);
        this.setMessage(serviceException.getMessage());
        this.setErrorcode(serviceException.getErrorCode());
        return this;
    }
}
