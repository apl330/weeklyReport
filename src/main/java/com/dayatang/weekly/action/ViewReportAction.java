package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.VehicleUsage;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class ViewReportAction extends BaseAction {

	private static final long serialVersionUID = -2941837203340005519L;

	private long reportId = 0L;

	private WeeklyReport report;

	private List<Attachment> attachments = new ArrayList<Attachment>();

	private List<VehicleUsage> vehicleUsages = new ArrayList<VehicleUsage>();

	public String execute() throws Exception {

		if (reportId <= 0L) {
			return ActionSupport.ERROR;
		}

		report = WeeklyReport.get(reportId);

		attachments.addAll(Attachment.findByReport(report));
		
		return ActionSupport.SUCCESS;
	}

	public boolean getCommented() {
		if (report.getStatus() == WeeklyReport.STATUS_COMMENTED) {
			return true;
		}
		return false;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<VehicleUsage> getVehicleUsages() {
		return vehicleUsages;
	}

	public void setVehicleUsages(List<VehicleUsage> vehicleUsages) {
		this.vehicleUsages = vehicleUsages;
	}

}
