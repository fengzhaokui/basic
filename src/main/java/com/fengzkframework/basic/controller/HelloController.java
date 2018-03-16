package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.dao.MALLDEFMapper;
import com.fengzkframework.basic.dao.vo.MALLDEF;
import com.fengzkframework.basic.dao.vo.SKT;
import com.fengzkframework.basic.dao.vo.TOKEN;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.service.SktServiceImpl;
import com.fengzkframework.basic.service.TokenServiceImpl;
import com.fengzkframework.basic.utils.RedisHelper;
import com.fengzkframework.basic.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/hello")
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private SktServiceImpl skt;
    @Autowired
    private TokenServiceImpl tokenService;
@Autowired
    MALLDEFMapper malldefMapper;
    @GetMapping(value = "/say")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
//        RedisHelper redis=new RedisHelper();
//       List<String> keys= redis.GetAllRedisKeys();
//        MALLDEF malldef=new MALLDEF();
//        malldef.setYttype(0);
//       // malldef.setCityid(2);
//        List<MALLDEF> list=malldefMapper.selectByytandcity2(malldef);
        return "ok";// + myId +keys.toString();
//        return girlProperties.getCupSize();
    }


    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        //testintoken();

        return "ok";
    }
    @GetMapping(value = "/gettest")
    public ResultData<TOKEN> gettest(@RequestParam(value = "token", required = false, defaultValue = "0") String token) {
       // tokenService.selectByPrimaryKey(token);
        TOKEN tc=  tokenService.selectByPrimaryKey(token);
        return ResultUtil.success(tc);
    }


    public void testintoken() {
        // TOKEN token = new TOKEN();
        TOKEN token = new TOKEN();
        token.setSktno("123123");
       // token.setPersonId(123);
        token.setCreatetime(new Date());
        token.setTokenguid(java.util.UUID.randomUUID().toString().replaceAll("-", "") + "123123");

        //oinfo.setToken(token.getTokenguid());
        tokenService.insert(token);

    }

    @GetMapping(value = "/skt")
    public ResultData<SKT> getskt()
    {
        return ResultUtil.success(skt.selectByPrimaryKey("004"));

    }
    @PostMapping(value="/skt",produces = "application/json")
//    public ResultData<SKT> postskt(@RequestBody SKT sktno) throws Exception
    public ResultData<SKT> postskt(@Valid SKT sktno) throws Exception
    {

        return ResultUtil.success(skt.selectByPrimaryKey(sktno.getSktno()));
    }
    @PostMapping(value="/skt1",produces = "application/json")
//    public ResultData<SKT> postskt(@RequestBody SKT sktno) throws Exception
    public ResultData<SKT> postskt1(@RequestBody SKT sktno) throws Exception
    {

        return ResultUtil.success(skt.selectByPrimaryKey(sktno.getSktno()));
    }
}
