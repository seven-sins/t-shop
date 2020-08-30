package com.hiya3d.common.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Rex.Tan
 * @date 2019年10月15日 下午5:50:43
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sys.config")
public class SysConfig {
	/**
	 * 域名
	 */
	private String domain;
	/**
	 * 上传路径
	 */
	private String uploadPath;
	/**
	 * 上传图片路径
	 */
	private String uploadImagePath;
	/**
	 * 上传附件路径
	 */
	private String uploadAdjunct;
}
