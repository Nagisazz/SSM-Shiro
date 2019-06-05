package com.ssmshiro.common.exception;

import com.ssmshiro.common.response.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController{

    @RequestMapping("/login")
    public @ResponseBody
    ResultEntity<String> error(){
        return new ResultEntity<String>().ERROR(new ServiceException(InfoCode.ESYS0001));
    }
}
