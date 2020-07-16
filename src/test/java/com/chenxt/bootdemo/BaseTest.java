package com.chenxt.bootdemo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * 测试基类
 *
 * @author chenxt
 * @date 2020/07/16
 */
@AutoConfigureMockMvc
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class BaseTest {
    public static final String JUNIT_TEST_DATA_PREFIX = "JUNIT_TEST_DATA_";
    @Resource
    protected WebApplicationContext mac;
    @Resource
    protected MockMvc mockMvc;
}
