package com.fengzkframework.basic.aspect;

import com.fengzkframework.basic.utils.HttpRequestUtil;
import com.fengzkframework.basic.utils.RedisHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 接口访问限制类；
 */
@Component
@Aspect
public class RequestLimitAspect {
    @Value("${apilimitmaxcount}")
    private  long maxcount;
    @Value("${apilimittimeout}")
    private  int timeout;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisHelper rh;

//    @Before("execution(* com.fengzkframework.basic.controller..*(..)) && @annotation(limit)")
//    public void requestLimit(JoinPoint joinpoint, RequestLimit limit) {

    //因为没有装redis 暂时去掉
    //@Before("execution(* com.fengzkframework.basic.controller..*(..))")
    public void requestLimit(JoinPoint joinpoint) {
        long count=0;
        try {

            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String ip = HttpRequestUtil.getIpAddr(request);
            String url = request.getRequestURL().toString();
            //记录访问次数；
           //rh.AddApinum(request.getRequestURL().toString(), ip);
          // logger.info("redis 成功");

            String logkey = "log" + request.getRequestURL().toString();
            if (redisTemplate.opsForHash().hasKey(logkey,ip))//判断是否存在；
            {
                Object value =redisTemplate.opsForHash().get(logkey, ip);
                Long lv = Long.valueOf(value.toString())  + 1;
               redisTemplate.opsForHash().put(logkey, ip, lv.toString());
            } else {
                redisTemplate.opsForHash().put(logkey, ip, "1");
            }
            //限制请求次数
            if (redisTemplate != null) {
                String key = "req_limit_".concat(url).concat(ip);
                //加1后看看值
                 count = redisTemplate.opsForValue().increment(key, 1);
                //刚创建
                if (count == 1) {
                    //设置过期时间
                    redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
                }
                if (count > maxcount) {
                    logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + maxcount + "]");
                    //throw new RuntimeException("超出访问次数限制");
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("访问限制aop程序报错:"+ex.getMessage());
        }
        if (count > maxcount) {
           // logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + maxcount + "]");
            throw new RuntimeException("超出访问次数限制");
        }
    }
}
