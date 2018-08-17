package cn.maxlu.demo.springmvc.controller.echo;

import cn.maxlu.demo.springmvc.controller.BaseController;
import cn.maxlu.demo.springmvc.controller.common.RestResponse;
import cn.maxlu.demo.springmvc.controller.common.exceptionhandler.SourceNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/echo")
public class EchoController extends BaseController {

    @RequestMapping(value = "/1", method = {RequestMethod.GET})
    public Object echo1(){
        return "hello world";
    }

    @RequestMapping(value = "/2", method = {RequestMethod.GET})
    public RestResponse echo2(){
        return RestResponse.success("hello world");
    }

    @RequestMapping(value = "/3", method = {RequestMethod.GET})
    public RestResponse echo3(){
        throw new SourceNotFoundException();
    }
}
