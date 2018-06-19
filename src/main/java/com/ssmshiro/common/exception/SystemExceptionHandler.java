package com.ssmshiro.common.exception;

import com.ssmshiro.common.response.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class SystemExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 未捕获的系统异常处理
     * @param exception
     * @return
     * @throws
     * @see
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResultEntity<String> handleException(Exception exception) {
        logger.error("system exception handler:", exception);
        return new ResultEntity<String>().ERROR(exception);
    }

    /**
     * 未捕获的业务异常处理
     * @param exception
     * @return
     * @throws
     * @see
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ResultEntity<String> handleServiceException(ServiceException exception) {
        logger.error(exception.getMessage());
        logger.error("system exception handler:", exception);
        return new ResultEntity<String>().ERROR(exception);
    }
}
