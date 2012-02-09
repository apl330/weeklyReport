package com.dayatang.weekly.action;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class ViewReportAction extends BaseAction {

	private static final long serialVersionUID = -2941837203340005519L;

	private long reportId = 0L;

	private WeeklyReport report;

	public String execute() throws Exception {

		if (reportId <= 0L) {
			return ActionSupport.ERROR;
		}

		report = WeeklyReport.get(reportId);

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
