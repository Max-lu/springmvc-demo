package cn.maxlu.demo.springmvc.controller.rest;

import cn.maxlu.demo.springmvc.controller.common.RestResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public RestResponse queryUserInfoById(@RequestParam("id") String id) {
        return RestResponse.success("query user info success", new UserInfo(id, "max1"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResponse modifyUserInfo(@RequestBody UserInfo userInfo) {
        return RestResponse.success("modify user info success", userInfo);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RestResponse createNewUser(@RequestBody UserInfo userInfo) {
        return RestResponse.success("create new user success", userInfo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public RestResponse deleteUserById(@RequestParam("id") String id) {
        return RestResponse.success("delete user success", new UserInfo(id, "max1"));
    }

    private static class UserInfo {

        public UserInfo() {
        }

        public UserInfo(String id, String name) {
            this.id = id;
            this.name = name;
        }

        private String id;

        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
