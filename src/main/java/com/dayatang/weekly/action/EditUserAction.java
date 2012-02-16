package com.dayatang.weekly.action;

import com.dayatang.weekly.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class EditUserAction extends BaseAction {

	private static final long serialVersionUID = -28402633887062197L;
	private User user;

	public String execute() {
		user = getCurrentUser();
		return ActionSupport.SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
