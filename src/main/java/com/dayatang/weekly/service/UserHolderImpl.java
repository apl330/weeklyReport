package com.dayatang.weekly.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dayatang.weekly.domain.User;


public class UserHolderImpl implements UserHolder {
	
	private Logger logger = LoggerFactory.getLogger(UserHolderImpl.class);

	private SecurityContext securityContext;
	
	private SecurityContext getSecurityContext() {
		if (securityContext == null) {
			securityContext = SecurityContextHolder.getContext();
		}
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
		logger.info("SecurityContext is setted to " + securityContext.getClass());
	}

	@Override
	public String getUsername() {
		return getSecurityContext().getAuthentication().getPrincipal().toString();
	}

	@Override
	public User getCurrentUser() {
		return User.getByUsername(getUsername());
	}
}
