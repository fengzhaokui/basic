package com.fengzkframework.basic.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import org.springframework.core.annotation.Order;

/**
 * 过滤器
 */
@Order(1)//表示执行过滤顺序，值越小，越先执行
@WebFilter(filterName="TokenFilter",urlPatterns="/*")
public class TokenFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TokenFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
    // System.out.print(getUserToken(request));
		chain.doFilter(request, response);
	}
	
	private String getUserToken(ServletRequest request) {
		
		Gson gson = new Gson();
		System.out.print( "过滤器拦截接口参数：" + gson.toJson(request.getParameterMap()));
		return request.getParameter("token");

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		//System.out.println("过滤器初始化");
	}

}
