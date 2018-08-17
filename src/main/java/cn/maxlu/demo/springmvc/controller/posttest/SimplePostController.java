package cn.maxlu.demo.springmvc.controller.posttest;

import cn.maxlu.demo.springmvc.controller.BaseController;
import cn.maxlu.demo.springmvc.controller.common.RestResponse;
import cn.maxlu.demo.springmvc.controller.common.exceptionhandler.SourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post/simple")
public class SimplePostController extends BaseController {

    @RequestMapping(value = "/0", method = {RequestMethod.POST})
    public RestResponse test0(String code , String name){
        return RestResponse.success("success", new PostInfo(code, name));
    }

    @RequestMapping(value = "/1", method = {RequestMethod.POST})
    public RestResponse test1(PostInfo postInfo){
        return RestResponse.success("success", postInfo);
    }

    @RequestMapping(value = "/2", method = {RequestMethod.POST})
    public RestResponse test2(@RequestBody PostInfo postInfo){
        return RestResponse.success("success", postInfo);
    }

    @RequestMapping(value = "/3", method = {RequestMethod.POST})
    public RestResponse test3(@RequestBody PostInfo postInfo){
        return RestResponse.success("success", postInfo);
    }

    @RequestMapping(value = "/4", method = {RequestMethod.POST})
    public RestResponse test4(HttpServletRequest request){
        try {
            ServletInputStream inputStream = request.getInputStream();
            PostInfo postInfo = new ObjectMapper().readValue(inputStream, PostInfo.class);
            return RestResponse.success("success", postInfo);
        } catch (Exception e) {
            return RestResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    private static class PostInfo {

        public PostInfo() {
        }

        public PostInfo(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;

        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
