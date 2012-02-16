package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.User;
import com.opensymphony.xwork2.ActionSupport;

@Action(value="save-user.action",results={@Result(name="success",location="edit-user.action",type="redirect"),@Result(name="input",location="edit-user.action",type="redirect")})
public class SaveUserAction extends BaseAction {

	private static final long serialVersionUID = -4514248599495813646L;

	private User user;

	public String execute() {
		if(null == user || user.getId() == null || user.getId() == 0L){
			System.out.println(user.getEmail() + "\n\n\n");
			return ActionSupport.INPUT;
		}
		this.reportApplication.saveEntity(user);
		return ActionSupport.SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
