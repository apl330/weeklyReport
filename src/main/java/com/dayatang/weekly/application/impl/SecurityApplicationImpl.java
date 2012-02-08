package com.dayatang.weekly.application.impl;

import com.dayatang.weekly.application.SecurityApplication;
import com.dayatang.weekly.domain.User;




public class SecurityApplicationImpl implements SecurityApplication {

	@Override
	public void saveUser(User user) {
		user.save();
	}

	@Override
	public void removeUser(User user) {
		user.remove();
	}

}
