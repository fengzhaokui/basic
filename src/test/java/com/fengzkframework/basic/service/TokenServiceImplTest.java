package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.vo.TOKEN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TokenServiceImplTest {
    @Autowired
    private TokenServiceImpl tokenService;

    @Test
    public void testin() {
       // TOKEN token = new TOKEN();
        TOKEN token = new TOKEN();
        token.setSktno("123123");
        token.setPersonId(123);
        token.setCreatetime(new Date());
        token.setTokenguid(java.util.UUID.randomUUID().toString().replaceAll("-", "") + "123123");

        //oinfo.setToken(token.getTokenguid());
        tokenService.insert(token);
    }
}