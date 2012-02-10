package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class SaveDraftAction extends BaseAction {

	private static final long serialVersionUID = 7947066700832613426L;
	private WeeklyReport report;

	private long reportId;

	@Action(results = { @Result(name = "success", type = "redirect", location = "add-report.action", params = { "reportId", "${reportId}" }) })
	public String execute() {
		report.setAuthor(getCurrentUser());
		this.reportApplication.saveReportAsDraft(report);
		if (report.getId() == null || report.getId() == 0) {
			reportId = WeeklyReport.findTheLastOne(getCurrentUser(), new Integer[] { WeeklyReport.STATUS_DRAFT }).getId();
		}
		return ActionSupport.SUCCESS;
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

}
