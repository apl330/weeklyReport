package com.dayatang.weekly.application;


import com.dayatang.annotations.Transactional;
import com.dayatang.weekly.domain.User;

public interface SecurityApplication {
	
	@Transactional
	void saveUser(User user);
	
	@Transactional
	void removeUser(User user);

}
