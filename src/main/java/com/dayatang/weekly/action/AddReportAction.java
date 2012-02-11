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

	private long reportId = 0L;

	private WeeklyReport report;

	public String execute() throws Exception {
		if (reportId > 0L) {
			report = WeeklyReport.get(reportId);
			System.out.println("\n\n" + report.getVersion() + "\n\n");
		}
		return ActionSupport.SUCCESS;
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
}
