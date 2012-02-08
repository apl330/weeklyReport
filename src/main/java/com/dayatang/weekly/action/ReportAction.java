package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dayatang.weekly.application.ReportApplication;
import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.Constants;
import com.dayatang.weekly.domain.WeeklyReport;

public class ReportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	

	private List<WeeklyReport> reports = new ArrayList<WeeklyReport>();
	private Long reportId;
	private WeeklyReport report = new WeeklyReport();
	private String doneWorks;
	private String toDoWorks;
	private String memo;
	private String comment;

	private String operation;

	// for search
	private Date formDateForSearch;
	private Date toDateForSearch;
	private String reportStatusForSearch;
	private String toSearch;


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

	/**
	 * @return the doneWorks
	 */
	public String getDoneWorks() {
		return doneWorks;
	}

	/**
	 * @return the toDoWorks
	 */
	public String getToDoWorks() {
		return toDoWorks;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	public List<WeeklyReport> getReports() {
		return reports;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getFormDateForSearch() {
		return formDateForSearch;
	}

	public void setFormDateForSearch(Date formDateForSearch) {
		this.formDateForSearch = formDateForSearch;
	}

	public Date getToDateForSearch() {
		return toDateForSearch;
	}

	public void setToDateForSearch(Date toDateForSearch) {
		this.toDateForSearch = toDateForSearch;
	}

	public String getReportStatusForSearch() {
		return reportStatusForSearch;
	}

	public void setReportStatusForSearch(String reportStatusForSearch) {
		this.reportStatusForSearch = reportStatusForSearch;
	}

	public String getToSearch() {
		return toSearch;
	}

	public void setToSearch(String toSearch) {
		this.toSearch = toSearch;
	}

	public List<Attachment> getAttachments() {
		return Attachment.findByReport(report);
	}

	/* 需要实现 */
	/*
	public boolean isAllowComment() {
		return true;
	}


	public String list() {
		if (toSearch != null) {
			returnSearchReports();
		} else {
			returnCommonreReports();
		}
		return SUCCESS;
	}*/
	/* 需要实现 */

	private void returnSearchReports() {
		report.setStatus(reportStatusForSearch.equals("submitted") ? WeeklyReport.STATUS_SUBMITTED : WeeklyReport.STATUS_COMMENTED);
		report.setProjectName(report.getProjectName().trim());
		reports = WeeklyReport.findByExample(report, formDateForSearch, toDateForSearch);
	}

	/*                         */
/*	private void returnCommonreReports() {
		if (request.isUserInRole(Constants.ROLE_HEAD)) {
			reports = WeeklyReport.findAll(getStatusSArray(report.getStatus()));
		} else if (request.isUserInRole(Constants.ROLE_MANAGER)) {
			reports = WeeklyReport.findByAuthor(this.getCurrentUser(), getStatusSArray(report.getStatus()));
		}
	}*/

	/*得到周报的状态*/
/*	private Integer[] getStatusSArray(int status) {
		if (status > 0) {
			return new Integer[] { status };
		}
		if (request.isUserInRole(Constants.ROLE_HEAD)) {
			return new Integer[] { WeeklyReport.STATUS_SUBMITTED, WeeklyReport.STATUS_COMMENTED };
		}
		return new Integer[] { WeeklyReport.STATUS_SUBMITTED, WeeklyReport.STATUS_COMMENTED, WeeklyReport.STATUS_DRAFT };
	}*/

	public String view() {
		report = WeeklyReport.get(reportId);
		doneWorks = textToHtml(report.getDoneWorks());
		toDoWorks = textToHtml(report.getToDoWorks());
		memo = textToHtml(report.getMemo());
		return SUCCESS;
	}

	private String textToHtml(String data) {
		if (data == null) {
			return "";
		}
		String result = data.replaceAll("\n\r", "<br>");
		result = result.replaceAll("\n", "<br>");
		result = result.replaceAll("\r", "<br>");
		return result;
	}

	public String add() {
		GregorianCalendar cal = initDate();
		report.setFromDate(getMonday(cal));
		report.setToDate(getSunday(cal));
		operation = "add";
		return SUCCESS;
	}

	private Date getMonday(GregorianCalendar cal) {
		cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		return cal.getTime();

	}

	private Date getSunday(GregorianCalendar cal) {
		cal.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.SUNDAY);
		return cal.getTime();
	}

	private GregorianCalendar initDate() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(GregorianCalendar.MONDAY);
		return cal;
	}

	public String edit() {
		operation = "edit";
		report = WeeklyReport.get(reportId);
		System.out.println("doneWorks: " + report.getDoneWorks());
		return SUCCESS;
	}

	// 存为草稿
	/*
	 * public String saveAsDraft() { if(reportId == null ||
	 * reportId.longValue()<= 0){
	 * report.setAuthor(userManager.getUserByUsername(
	 * request.getUserPrincipal().getName())); } else { WeeklyReport report1 =
	 * WeeklyReport.get(reportId); BeanUtils.copyProperties(report, report1, new
	 * String[] {"author", "vehicleUsages"}); report = report1; }
	 * getReportApplication().saveReportAsDraft(report); return SUCCESS; }
	 */

	// 周报提交
	/*
	 * public String submit() { if(reportId == null || reportId.longValue()<=
	 * 0){
	 * report.setAuthor(userManager.getUserByUsername(request.getUserPrincipal
	 * ().getName())); } else { WeeklyReport report1 =
	 * WeeklyReport.get(reportId); BeanUtils.copyProperties(report, report1, new
	 * String[] {"author", "vehicleUsages"}); report = report1; }
	 * getReportApplication().submitReport(report); return SUCCESS; }
	 */

	public String comment() {
		WeeklyReport report1 = WeeklyReport.get(reportId);
		report1.setComment(report.getComment());
		getReportApplication().commentReport(report1);
		report.setStatus(0);
		return SUCCESS;
	}
}
