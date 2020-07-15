package com.chenxt.bootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenxt.bootdemo.SpringBootDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * demo
 *
 * @author chenxt
 * @date 2020/07/15
 */
@AutoConfigureMockMvc
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class UserControllerTest {

    @Resource
    private WebApplicationContext mac;

    @Resource
    private MockMvc mockMvc;

    @Test
    public void loginTest() throws Exception {
        //请求的json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", "18251816111");
        jsonObject.put("verifyCode", "111111");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/login")
                //json类型
                .contentType(MediaType.APPLICATION_JSON)
                //使用writeValueAsString()方法来获取对象的JSON字符串表示
                .content(jsonObject.toJSONString()))
                //andExpect，添加ResultMathcers验证规则，验证控制器执行完成后结果是否正确，【这是一个断言】
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //使用jsonPaht验证返回的json中code字段的返回值
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                //body属性不为空
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty())
                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
