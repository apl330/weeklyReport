package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class CommitReportAction extends BaseAction {

	private static final long serialVersionUID = -2426741228027368194L;

	private WeeklyReport report;

	@Action(results = { @Result(name = "success", type = "redirect", location = "report-list.action") })
	public String execute() {
		report.setAuthor(getCurrentUser());
		
		this.reportApplication.submitReport(report);
		return ActionSupport.SUCCESS;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

}
