package com.hiya3d.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hiya3d.common.conf.user.context.UserContext;

/**
 * 日志输出工具
 * @author rex.tan
 * @date 2020年5月11日 下午4:20:23
 */
public class LogUtil {
	static final String PROJECT_NAME = "t-shop";
	static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);
	
	/**
	 * 日志输出
	 * @author Rex.Tan
	 * @date 2020年5月11日 下午4:20:54
	 * @param log
	 */
	public static void printLog(String log) {
		JSONObject json = new JSONObject();
		json.put("logType", "___rest___");
		if(UserContext.get() != null) {
			json.put("userName", UserContext.get().getUserName());
		}
		json.put("url", "系统输出日志");
		json.put("timestamp", System.currentTimeMillis());
		json.put("visitTime", DateUtils.now2String());
		json.put("message", log);
		json.put("projectName", PROJECT_NAME);
		
		LOG.info("\r\n" + json.toJSONString());
	}
	
	public static void printFailedLog(String log) {
		JSONObject json = new JSONObject();
		json.put("logType", "___rest___");
		if(UserContext.get() != null) {
			json.put("userName", UserContext.get().getUserName());
		}
		json.put("status", 400);
		json.put("url", "系统输出日志");
		json.put("timestamp", System.currentTimeMillis());
		json.put("visitTime", DateUtils.now2String());
		json.put("message", log);
		json.put("projectName", PROJECT_NAME);
		
		LOG.info("\r\n" + json.toJSONString());
	}
	
	public static void printExceptionLog(String log) {
		JSONObject json = new JSONObject();
		json.put("logType", "___rest___");
		if(UserContext.get() != null) {
			json.put("userName", UserContext.get().getUserName());
		}
		json.put("status", 500);
		json.put("url", "系统输出日志");
		json.put("timestamp", System.currentTimeMillis());
		json.put("visitTime", DateUtils.now2String());
		json.put("message", log);
		json.put("projectName", PROJECT_NAME);
		
		LOG.info("\r\n" + json.toJSONString());
	}
	
	public static void printLog(String log, Integer status) {
		JSONObject json = new JSONObject();
		json.put("logType", "___rest___");
		if(UserContext.get() != null) {
			json.put("userName", UserContext.get().getUserName());
		}
		json.put("status", status);
		json.put("url", "系统输出日志");
		json.put("timestamp", System.currentTimeMillis());
		json.put("visitTime", DateUtils.now2String());
		json.put("message", log);
		json.put("projectName", PROJECT_NAME);
		
		LOG.info("\r\n" + json.toJSONString());
	}
	
	public static void printException(Exception e) {
		try {
			JSONObject json = new JSONObject();
			json.put("logType", "___rest___");
			if(UserContext.get() != null) {
				json.put("userName", UserContext.get().getUserName());
			}
			json.put("url", "系统输出日志");
			json.put("timestamp", System.currentTimeMillis());
			json.put("visitTime", DateUtils.now2String());
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			json.put("status", 500);
			json.put("message", stringWriter.toString());
			json.put("projectName", PROJECT_NAME);
			
			LOG.info("\r\n" + json.toJSONString());
		} catch(Exception ex) {
			printLog("输出异常信息失败, " + e.getMessage());
		}
	}
	
	public static void printException(String msg, Exception e) {
		try {
			JSONObject json = new JSONObject();
			json.put("logType", "___rest___");
			if(UserContext.get() != null) {
				json.put("userName", UserContext.get().getUserName());
			}
			json.put("url", "系统输出日志");
			json.put("timestamp", System.currentTimeMillis());
			json.put("visitTime", DateUtils.now2String());
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			json.put("status", 500);
			json.put("message", stringWriter.toString());
			json.put("projectName", PROJECT_NAME);
			
			LOG.info("\r\n" + msg + json.toJSONString());
		} catch(Exception ex) {
			printLog("输出异常信息失败, " + e.getMessage());
		}
	}
	
	/**
	 * 输出sql
	 * @author Rex.Tan
	 * @date 2020年5月11日 下午4:18:44
	 * @param configuration
	 * @param boundSql
	 * @param sqlId
	 */
	public static void printSql(Configuration configuration, BoundSql boundSql, String sqlId) {
		try {
			String sql = getSql(configuration, boundSql, sqlId);
			JSONObject json = new JSONObject();
			json.put("logType", "___sql___");
			if(UserContext.get() != null) {
				json.put("userName", UserContext.get().getUserName());
			}
			json.put("timestamp", System.currentTimeMillis());
			json.put("visitTime", DateUtils.now2String());
			json.put("sql", sql);
			json.put("projectName", PROJECT_NAME);
			
			LOG.info("\r\n" + json.toJSONString());
		} catch (Exception e) {
			LOG.error("输出sql语句时出错: ", e);
		}
	}

	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
		return showSql(configuration, boundSql);
	}

	/**
	 * 如果参数是String添加单引号, 如果是日期转换为时间格式器并加单引号
	 * 
	 * @author Rex.Tan
	 * @date 2020年5月7日 下午6:54:30
	 * @param obj
	 * @return
	 */
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}
		}
		return value;
	}

	/**
	 * 进行？的替换
	 * @author Rex.Tan
	 * @date 2020年5月7日 下午6:54:44
	 * @param configuration
	 * @param boundSql
	 * @return
	 */
	public static String showSql(Configuration configuration, BoundSql boundSql) {
		// 获取参数
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		// sql语句中多个空格都用一个空格代替
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if ((parameterMappings != null && !parameterMappings.isEmpty()) && parameterObject != null) {
			// 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			// 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
			} else {
				// MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						// 该分支是动态sql
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						// 打印出缺失，提醒该参数缺失并防止错位
						sql = sql.replaceFirst("\\?", "缺失");
					}
				}
			}
		}

		return sql;
	}

}
