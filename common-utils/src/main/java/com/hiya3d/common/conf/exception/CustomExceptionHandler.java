package com.hiya3d.common.conf.exception;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.hiya3d.base.date.CustomDateFormat;
import com.hiya3d.base.exception.CustomException;
import com.hiya3d.base.response.Result;
import com.hiya3d.common.utils.LogUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常捕获
 * @author Rex.Tan
 * @date 2018年12月5日 下午3:10:30
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

	/**
	 * 参数验证异常处理
	 * @author Rex.Tan
	 * @date 2018年12月5日 上午11:41:12
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object methodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult != null) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			if (errors != null && !errors.isEmpty()) {
				String errorMsg = errors.get(0).getDefaultMessage();
				LogUtil.printException("未捕获异常: ", e);
				return new Result<>(400, errorMsg);
			}
		}
		LogUtil.printException("未捕获异常: ", e);
		return new Result<>(400, "参数验证错误!");
	}
	
	/**
	 * 参数解析异常
	 * @author Rex.Tan
	 * @date 2018年12月5日 上午11:40:56
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
		if(e.getCause() instanceof JsonMappingException) {
			JsonMappingException jsonMappingException = (JsonMappingException) e.getCause();
			if(jsonMappingException.getCause() instanceof JsonParseException) {
				JsonParseException jsonParseException = (com.fasterxml.jackson.core.JsonParseException) jsonMappingException.getCause();
				return new Result<>(400, "参数解析异常: " + jsonParseException.getOriginalMessage());
			} else if(jsonMappingException instanceof InvalidFormatException) {
				InvalidFormatException invalidFormatException = (InvalidFormatException) jsonMappingException;
				return new Result<>(400, "参数解析异常: " + invalidFormatException.getOriginalMessage());
			}
		} 
		
		return new Result<>(400, "未捕获异常: " + e.getMessage());
	}

	@ExceptionHandler(CustomException.class)
	public Object customException(CustomException e) {
		return new Result<>(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public Object commonException(Exception e) {
		//		LogUtil.printException("未捕获异常: ", e);
		log.error("未捕获异常", e);
		return new Result<>(400, "未捕获异常: " + e.getMessage());
	}

	/**
	 * 初始化绑定操作(全局形式)(GET请求)
	 * 
	 * @author Tan Ling
	 * @date 2019年6月21日 下午2:02:57
	 * @param binder
	 */
	@InitBinder
	public void dataBind(WebDataBinder binder) {
		// 給指定类型注册类型转换器操作
		DateFormat dateFmt = new ObjectMapper().getDateFormat();
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new CustomDateFormat(dateFmt), true));
	}
}
