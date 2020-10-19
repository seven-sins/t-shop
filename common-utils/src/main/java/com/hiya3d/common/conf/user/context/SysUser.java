package com.hiya3d.common.conf.user.context;

import java.io.Serializable;

import lombok.Data;

/**
 * @author rex.tan
 * @date 2019年11月22日 下午2:18:29
 */
@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;

	private String userName;

	private String userCode;

	private String phone;

	private String idCard;

	private String role;

	private Integer sex;
	
	private String shopId;
}
