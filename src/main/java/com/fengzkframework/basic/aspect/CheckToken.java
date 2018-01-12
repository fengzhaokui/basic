package com.fengzkframework.basic.aspect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.vo.TOKEN;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.service.TokenServiceImpl;
import com.fengzkframework.basic.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

/*
 所有接口检验token；处理返回的错误；
 */
@Service
public class CheckToken extends HandlerInterceptorAdapter {

//	@Qualifier("TokenServiceImpl")
    @Autowired
	private TokenServiceImpl tokenService;

	Logger logger = Logger.getLogger(CheckToken.class);
	Gson gson=new Gson();


	private void SetErrorRespon(HttpServletResponse response, ResultData result) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String str= gson.toJson(result);
			out.append(str);
			logger.info("接口报错，返回:" + str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.print("拦截器飘过");
		if(request.getRequestURL().indexOf("/sendsms")!=-1 || request.getRequestURL().indexOf("/weixin")!=-1|| request.getRequestURL().indexOf("/hello")!=-1)//不验证 token；
		{

			return true;
		}
		ResultData result = new ResultData();
		if (tokenService == null) {//解决service为null无法注入问题
			//System.out.println("tokenService is null!!!");
			BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
			tokenService = (TokenServiceImpl) factory.getBean("tokenServiceImpl");
		}
		// 检查用户所传递的 token 是否合法
		String token = request.getParameter("token");//getUserToken(request);
		if (token == null) {
			result.setRetcode("-1");
			result.setRetmsg("错误, Token不可以为空!");
			SetErrorRespon(response, result);
			// response.setStatus(500);
			return false;
		} else {
			TOKEN data = tokenService.selectByPrimaryKey(token);
			if (data == null) {
				result.setRetcode("-1");
				result.setRetmsg("错误, Token不存在!");
				// response.setStatus(500);
				SetErrorRespon(response, result);
				return false;
			}
		}
		return true;
		// return super.preHandle(request, response, handler);

	}

	//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		StringBuffer url = request.getRequestURL();
//		long startTime = (Long) request.getAttribute("startTime");
//		long endTime = System.currentTimeMillis();
//		long executeTime = endTime - startTime;
//		//if (handler instanceof HandlerMethod) {
//			StringBuilder sb = new StringBuilder(1000);
//			sb.append(url + "接口耗时  : ").append(executeTime).append("ms").append("\n");
//			logger.info(sb.toString());
//		//}
//		super.postHandle(request, response, handler, modelAndView);
//	}

	//
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null ) {
			logger.error(ex);
			ResultData result = new ResultData();
			result.setRetcode(ResultEnum.UNKNOWERROR.getCode());
			result.setRetmsg(ResultEnum.UNKNOWERROR.getMsg());
			result.setData(ex.getMessage());
//			Object arg= request.getAttribute("httparg");
//			if(arg!=null) {
//				String http =  arg.toString();
//				result.setData(http);
//			}
			SetErrorRespon(response, result);
		}

		super.afterCompletion(request, response, handler, ex);
	}

}
