package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 添加周报的页面类
 * 
 * @author zjzhai
 * 
 */

public class AddReportAction extends BaseAction {

	private static final long serialVersionUID = -6223024524397861299L;

	private Long reportId = 0L;

	private WeeklyReport report;

	public String execute() throws Exception {
		if (reportId > 0L) {
			report = WeeklyReport.get(reportId);
		}
		return ActionSupport.SUCCESS;
	}

/*	@Action(value = "save-draft.action", results = { @Result(name = "success", type = "redirect", location = "add-report.action", params = { "reportId", "${reportId}" }) })
	public String saveDraft() {
		report.setAuthor(getCurrentUser());
		this.reportApplication.saveReportAsDraft(report);
		report = WeeklyReport.findTheLastOne(getCurrentUser(), new Integer[] { WeeklyReport.STATUS_DRAFT });
		reportId = report.getId();
		return ActionSupport.SUCCESS;
	}*/

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

}
