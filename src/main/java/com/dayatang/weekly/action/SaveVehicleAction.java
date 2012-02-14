package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.VehicleUsage;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("web")
public class SaveVehicleAction extends BaseAction {

	private static final long serialVersionUID = 3322841986492312467L;

	private long reportId = 0L;

	private VehicleUsage vehicleUsage;

	@Action(results = { @Result(name = "success", type = "json") })
	public String execute() {

		if (reportId > 0L && vehicleUsage != null) {
			WeeklyReport report = WeeklyReport.get(reportId);
			report.getVehicleUsages().add(vehicleUsage);
			this.reportApplication.saveEntity(report);
			vehicleUsage = VehicleUsage.getLastOneOf(report);
		}

		return ActionSupport.SUCCESS;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public VehicleUsage getVehicleUsage() {
		return vehicleUsage;
	}

	public void setVehicleUsage(VehicleUsage vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}

}
