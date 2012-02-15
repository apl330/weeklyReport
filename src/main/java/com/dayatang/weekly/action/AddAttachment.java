package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class AddAttachment extends BaseAction {

	private static final long serialVersionUID = -6822097065288340628L;

	private long reportId = 0L;

	private WeeklyReport report;

	private List<Attachment> attas = new ArrayList<Attachment>();

	public String execute() {
		report = WeeklyReport.get(reportId);
		attas.addAll(Attachment.findByReport(report));
		return ActionSupport.SUCCESS;
	}

	public List<Attachment> getAttas() {
		return attas;
	}

	public void setAttas(List<Attachment> attas) {
		this.attas = attas;
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
