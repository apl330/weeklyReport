package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.dayatang.weekly.domain.VehicleUsage;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("web")
public class RemoveVehicleAction extends BaseAction {

	private static final long serialVersionUID = 895258735467481375L;

	public long veId = 0L;

	public boolean success = false;

	@Action(results={@Result(name="success", type="json")})
	public String execute() {

		if (veId > 0L) {
			VehicleUsage vu = VehicleUsage.get(VehicleUsage.class, veId);
			if(vu != null){
				this.reportApplication.removeEntity(vu);
			}
			success = true;
		}

		return ActionSupport.SUCCESS;
	}

	@JSON(serialize = false)
	public long getVeId() {
		return veId;
	}

	public void setVeId(long veId) {
		this.veId = veId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
