package cn.maxlu.demo.springmvc.controller.rest;

import cn.maxlu.demo.springmvc.config.SpringConfig;
import cn.maxlu.demo.springmvc.config.SpringMvcConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class, SpringMvcConfig.class})
public class UserControllerTest {
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testQueryUserInfoById() throws Exception {
        String expectResponseStr = "{\"code\":0,\"msg\":\"query user info success\",\"data\":{\"id\":\"1\",\"name\":\"max1\"},\"extra\":null}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/user?id=1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expectResponseStr))
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testModifyUserInfo() throws Exception {
        String requestBody = "{\"id\":\"1\", \"name\":\"max2\"}";
        String expectResponseStr = "{\"code\":0,\"msg\":\"modify user info success\",\"data\":{\"id\":\"1\",\"name\":\"max2\"},\"extra\":null}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expectResponseStr))
                .andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testCreateNewUser() throws Exception {
        String requestBody = "{\"id\":\"2\", \"name\":\"max2\"}";
        String expectResponseStr = "{\"code\":0,\"msg\":\"create new user success\",\"data\":{\"id\":\"2\",\"name\":\"max2\"},\"extra\":null}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expectResponseStr))
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String expectResponseStr = "{\"code\":0,\"msg\":\"delete user success\",\"data\":{\"id\":\"1\",\"name\":\"max1\"},\"extra\":null}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/user?id=1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expectResponseStr))
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }
}
