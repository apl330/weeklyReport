package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value = "web")
public class SaveReportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private long reportId = 0L;

	private WeeklyReport report;

	private int status = 1;
	@Action(results = {
			@Result(name = "success", type = "redirect", location = "report-list.action"),
			@Result(name = "input", type = "json") })
	public String execute() {
		report.setAuthor(getCurrentUser());
		if (status == WeeklyReport.STATUS_DRAFT) {
			this.reportApplication.saveReportAsDraft(report);
			report = WeeklyReport.findTheLastOne(getCurrentUser(),
					new Integer[] { WeeklyReport.STATUS_DRAFT });
			reportId = report.getId();
			return ActionSupport.INPUT;
		} else if (status == WeeklyReport.STATUS_SUBMITTED) {
			this.reportApplication.submitReport(report);
			return ActionSupport.SUCCESS;
		} else {
			return ActionSupport.INPUT;
		}
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
