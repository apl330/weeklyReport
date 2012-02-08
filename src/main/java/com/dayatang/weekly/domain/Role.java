package com.dayatang.weekly.domain;


import java.io.Serializable;

/**
 * 用户角色
 */
public enum Role implements Serializable {
	
	/**
	 * 管理层
	 */
	ROLE_PMO,
	
	/**
	 * 系统管理员
	 */
	ROLE_SUPERVISOR,

	/**
	 * 事业部经理
	 */
	ROLE_DM,
	
	/**
	 * 项目经理
	 */
	ROLE_PM,
	
	/**
	 * 普通用户
	 */
	ROLE_USER
	
}
