package cn.maxlu.demo.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BaseController {

    @RequestMapping(value = "/parent", method = {RequestMethod.GET})
    public Object parentEcho(){
        return "hello world, o~ yeah";
    }
}
