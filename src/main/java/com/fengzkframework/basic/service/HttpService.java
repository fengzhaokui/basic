package com.fengzkframework.basic.service;

import com.fengzkframework.basic.controller.JHWXController;
import com.fengzkframework.basic.domain.MSPayData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.utils.JsonUtils;
import com.fengzkframework.basic.utils.ResultUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class HttpService {
    Gson gson=new Gson();

    Logger logger = LoggerFactory.getLogger(HttpService.class);
    @Value("${msserviceurl}")
    private String msbaseurl;

    @Value("${wxserviceurl}")
    private String baseurl;
  private  final  static String wxtoken="?user=999999&&pwd=cb1d9732bd6823b85fd607815726efc6";

    /**
     * get请求马上服务
     * @param map
     * @return
     */
    public ResultData GetMSService(String argurl,Map<String, String> map)
    {
        //请求地址
        String url = msbaseurl+argurl;
        //入参
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            url+="&&"+entry.getKey()+"="+entry.getValue();
        }
        logger.info("马上请求："+url);
        RestTemplate restTemplate = new RestTemplate();
        MSPayData aa=  restTemplate.getForObject(url, MSPayData.class);
        logger.info("马上返回："+gson.toJson(aa));
        return ResultUtil.GetResultData(aa);

    }
    public ResultData GetMSService(String argurl,String json)
    {
        //请求地址
        String url = msbaseurl+argurl;
        url+="?data="+json;
        logger.info("马上请求："+url);
        RestTemplate restTemplate = new RestTemplate();
        MSPayData aa=  restTemplate.getForObject(url, MSPayData.class);
        logger.info("马上返回："+gson.toJson(aa));
        return ResultUtil.GetResultData(aa);

    }

    /**
     * get请求服务
     * @param map
     * @return
     */
    public ResultData GetService(Map<String, String> map)
    {
        //请求地址
        String url = baseurl+"sendsms"+wxtoken;
        //入参
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            url+="&&"+entry.getKey()+"="+entry.getValue();
        }
        logger.info("c#请求："+url);

        RestTemplate restTemplate = new RestTemplate();
        ResultData aa=  restTemplate.getForObject(url, ResultData.class);
        logger.info("c#返回："+gson.toJson(aa));
        return aa;
      //  ResponseBean responseBean = restTemplate.postForObject(url, requestBean, ResponseBean.class);
    }

    /**
     * boby传递参数的接口调用
     * @param argurl
     * @param map
     * @return
     */
    public  ResultData PostService(String argurl,Map<String, String> map)
    {
        //请求地址
        String url = baseurl+argurl+wxtoken;

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String jsonstr= JsonUtils.hashMap2Json(map);
        logger.info("c#请求："+url+"[boby:]"+jsonstr);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
        logger.info("c#返回："+gson.toJson(aa));
        return aa;
    }

    public  ResultData PostService(String argurl,String json)
    {
        //请求地址
        String url = baseurl+argurl+wxtoken;
        logger.info("c#请求："+url);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
       // String jsonstr= JsonUtils.hashMap2Json(map);
        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResultData aa=  restTemplate.postForObject(url,formEntity, ResultData.class);
        logger.info("c#返回："+gson.toJson(aa));
        return aa;
    }
}
