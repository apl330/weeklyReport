package com.dayatang.weekly.action;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 提交周报
 * @author zjzhai
 *
 */
public class CommitReport extends BaseAction {
	
	private static final long serialVersionUID = -1307482417464899314L;
	
	private WeeklyReport report;

	public String execute() throws Exception {
		if (report == null) {
			return ActionSupport.INPUT;
		}
		this.reportApplication.saveReportAsDraft(report);
		return SUCCESS;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}
}
