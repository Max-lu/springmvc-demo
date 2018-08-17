package cn.maxlu.demo.springmvc.controller.posttest;

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
public class SimplePostControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void test4() throws Exception {
        //language=JSON
        String requestBody = "{\"code\":\"123\", \"name\":\"max\"}";
        String expectResponseStr = "{\"code\":0,\"msg\":\"success\",\"data\":{\"code\":\"123\",\"name\":\"max\"},\"extra\":null}";
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/post/simple/4").content(requestBody))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expectResponseStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
    }
}
