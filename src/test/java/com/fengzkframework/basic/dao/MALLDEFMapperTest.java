package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.MALLDEF;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MALLDEFMapperTest {
    @Autowired
    MALLDEFMapper malldefMapper;

    @Test
    public void selectByytandcity2() {
        Gson gson=new Gson();
        MALLDEF malldef=new MALLDEF();
        malldef.setCityid(2);
        List<MALLDEF> list=malldefMapper.selectByytandcity2(malldef);
        System.out.print("返回："+gson.toJson(list));
        return;
    }
}