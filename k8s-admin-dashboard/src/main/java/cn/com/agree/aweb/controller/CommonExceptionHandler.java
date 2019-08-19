package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.vo.RestResultMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResultMessage<Object> errorHandler(Exception ex) {
        return new RestResultMessage<Object>(() -> ex);
    }
}