package com.fengzkframework.basic.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtils implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context )
			throws BeansException {
		SpringBeanUtils.applicationContext = context;
		
	}

	public static Object getBean( String beanName ) {
		return SpringBeanUtils.applicationContext.getBean( beanName );
	}
	
	public static void setSpringContext(ApplicationContext context ){
		applicationContext = context;
	}
}
