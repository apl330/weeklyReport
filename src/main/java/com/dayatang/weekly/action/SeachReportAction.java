package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class SeachReportAction extends BaseAction {

	private static final long serialVersionUID = -9203764838309530310L;

	private WeeklyReport report;

	private List<WeeklyReport> reports = new ArrayList<WeeklyReport>();

	@Action(results = { @Result(name = "success", type = "redirect", location = "list-report.action") })
	public String execute() {

		if (report != null) {
			reports.addAll(WeeklyReport.findByExample(report, report.getFromDate(), report.getToDate()));
		}

		return ActionSupport.SUCCESS;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public List<WeeklyReport> getReports() {
		return reports;
	}

	public void setReports(List<WeeklyReport> reports) {
		this.reports = reports;
	}

}
