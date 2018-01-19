package com.fengzkframework.basic;

import com.fengzkframework.basic.aspect.CheckToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan//处理过滤器
@EnableCaching// 开启缓存，需要显示的指定
//@EnableEurekaClient
public class BasicApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BasicApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
		System.out.print("启动成功");
	}

}
/*
处理拦截器
 */
@Configuration
class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckToken()).addPathPatterns("/**");
	}
}
