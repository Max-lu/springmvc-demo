package cn.maxlu.demo.springmvc.controller.common.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException() {
        super();
    }
}
