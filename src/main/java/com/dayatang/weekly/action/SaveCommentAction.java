package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class SaveCommentAction extends BaseAction {

	private static final long serialVersionUID = 5893121810800637298L;

	private long reportId = 0L;

	private String commen;

	@Action(results = { @Result(name = "success", type = "redirect", location = "/report-list.action"), @Result(name = "input", type = "redirect", location = "/report-list.action") })
	public String execute() {
		if (reportId == 0L) {
			return ActionSupport.INPUT;
		}
		WeeklyReport report = WeeklyReport.get(reportId);
		report.setComment(commen);
		reportApplication.commentReport(report);
		return ActionSupport.SUCCESS;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public String getCommen() {
		return commen;
	}

	public void setCommen(String commen) {
		this.commen = commen;
	}

}
