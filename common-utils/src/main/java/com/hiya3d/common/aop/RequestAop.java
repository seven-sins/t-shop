package com.hiya3d.common.aop;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiya3d.base.exception.CustomException;
import com.hiya3d.common.conf.user.context.SysUser;
import com.hiya3d.common.conf.user.context.UserContext;
import com.hiya3d.common.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截输出异常堆栈(改用fluentd方式)
 * 
 * @author Rex.Tan 
 * @date 2018年12月13日 上午9:18:58
 */
@Slf4j
@Aspect
@Component
@Order(999)
public class RequestAop {
	/**
	 * 排除拦截的url列表
	 */
	static final List<String> LOGIN_URL = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("/rest/employee/user/pc/doLogin");
			add("/rest/employee/user/pc/doLogin/v2");
			add("/rest/employee/user/app/doLogin");
			add("/rest/employee/cas/login");
			add("/rest/employee/sso");
			add("/rest/employee/parse/user");
			add("/rest/employee/employeeSignet/signStatusReturnNotice");
			add("/rest/employee/employeeSignet/callback/notice");
			add("/rest/wechat/getWechatUserInfo");
			add("/rest/employee/user/sendMsg");
			add("/rest/employee/user/app/phone/doLogin");
			add("/rest/employee/user/app/phone/resetPasswordByPhone");
			// 第三方数据同步接口
			add("/rest/sync/data");
			// 版本检查接口
			add("/rest/base/app/baseVersionUpgrade");
		}
	};

	@Pointcut("execution(* com.hiya3d..*.*Controller.*(..))")
	public void init() {

	}

	@Before("init()")
	public void beforeAdvice(JoinPoint joinPoint) {
		// 进入方法前拦截
	}

	@Around("init()")
	public Object around(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getRequestURI();
		/**
		 * 设置用户信息
		 */
		if (!LOGIN_URL.contains(url)) {
			SysUser user = new SysUser();
			user.setUserId("100000001");
			user.setUserName("cc");
			UserContext.set(user);
		}
		/**
		 * 输出url
		 */
		Object obj = null;
		String bodyArgs = this.bodyArgs2String(request, pjp.getArgs(), url);
		JSONObject urlArgs = this.urlArgs2String(request, request.getParameterNames(), url);
		try {
			obj = pjp.proceed();
			long endTime = System.currentTimeMillis();
			this.logInfo2Kafka(request, url, bodyArgs, urlArgs, "success", 0, endTime - startTime, true);
		} catch (Throwable e) {
			long endTime = System.currentTimeMillis();
			if (e instanceof CustomException) {
				/**
				 * 拦截到主动抛出的异常
				 */
				CustomException ex = (CustomException) e;
				this.logInfo2Kafka(request, url, bodyArgs, urlArgs, ex.getMsg(), 400, endTime - startTime, false);
				throw ex;
			} else {
				/**
				 * 拦截到未知异常
				 */
				StringWriter stringWriter = new StringWriter();
				e.printStackTrace(new PrintWriter(stringWriter));
				this.logInfo2Kafka(request, url, bodyArgs, urlArgs, "未捕获异常: " + stringWriter.toString(), 500, endTime - startTime, false);
				throw new CustomException("未捕获异常," + e.getMessage());
			}
		} finally {
			UserContext.remove();
		}

		return obj;
	}

	/**
	 * 请录请求耗时
	 * 
	 * @author Tan Ling
	 * @date 2018年12月13日 下午2:51:31
	 * @param url           请求地址
	 * @param args          requestBody中的参数
	 * @param args2         url中的参数
	 * @param message       消息
	 * @param status        接口调用返回状态
	 * @param executionTime 执行耗时
	 * @param isSuccess     是否调用成功
	 */
	@Async
	public void logInfo2Kafka(HttpServletRequest request, String url, String bodyArgs, JSONObject urlArgs,
			String message, Integer status, long executionTime, boolean isSuccess) {
		JSONObject json = new JSONObject();
		json.put("logType", "___rest___");
		if (UserContext.get() != null) {
			json.put("userName", UserContext.get().getUserName());
		}
		json.put("url", url);
		json.put("timestamp", System.currentTimeMillis());
		json.put("visitTime", DateUtils.now2String());
		json.put("message", message);
		json.put("status", status);
		json.put("executionTime", executionTime);
		json.put("channel", request.getHeader("channel"));
		json.put("projectName", "t-shop");
		json.put("requestType", request.getMethod());
		/**
		 * 1.requestBody中的参数
		 */
		json.put("bodyArgs", bodyArgs);
		/**
		 * 2.url中的参数
		 */
		json.put("urlArgs", urlArgs);
		/**
		 * 3.如果请求状态不为0, 在控制台输入请求信息
		 */
		if (status != 0) {
			log.error("\r\n" + json.toJSONString());
		} else {
			log.info("\r\n" + json.toJSONString());
		}
	}

	/**
	 * 读取requestBody中的参数
	 * 
	 * @author Tan Ling
	 * @date 2018年12月13日 下午2:54:31
	 * @param bodyArgs
	 * @param url
	 * @return
	 */
	private String bodyArgs2String(HttpServletRequest request, Object[] bodyArgs, String url) {
		if ("GET".equals(request.getMethod())) {
			return "";
		}
		if (url != null && url.matches("upload")) {
			return "";
		}
		if (url != null && url.matches("image")) {
			return "";
		}
		try {
			if (bodyArgs != null && bodyArgs.length > 0) {
				String body = JSONArray.toJSONString(bodyArgs);
				if (body.matches("image")) {
					return "";
				}
				return body;
			}
		} catch (Exception e) {
			log.error("=============序列化requestBody中的参数出错, " + url);
		}
		return "";
	}

	/**
	 * 读取url中的参数
	 * 
	 * @author Tan Ling
	 * @date 2019年9月12日 下午2:54:40
	 * @param request
	 * @param urlArgs
	 * @param url
	 * @return
	 */
	private JSONObject urlArgs2String(HttpServletRequest request, Enumeration<String> urlArgs, String url) {
		JSONObject urlArgsJson = new JSONObject();
		try {
			if (urlArgs != null) {
				while (urlArgs.hasMoreElements()) {
					try {
						String paraName = (String) urlArgs.nextElement();
						urlArgsJson.put(paraName, request.getParameter(paraName));
					} catch (Exception e) {
						log.error("=============记录url中的参数出错", url);
						break;
					}
				}
			}
		} catch (Exception e) {
			log.error("=============记录url中的参数出错, " + url);
		}
		return urlArgsJson;
	}

}
