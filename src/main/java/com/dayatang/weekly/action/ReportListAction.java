package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.dayatang.weekly.domain.Role;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class ReportListAction extends BaseAction {

	private static final long serialVersionUID = 2577807854273795934L;

	// 项目名
	private String projectName;

	// 时间范围
	private Date fromDate;
	private Date toDate;

	// 呈报人的名字
	private String userRealName;

	// 是否过滤掉未审阅的
	private boolean read = true;

	private boolean search = false;

	private List<WeeklyReport> reports = new ArrayList<WeeklyReport>();

	public String execute() {

		if (search) {
			
			reports.addAll(WeeklyReport.search(getCurrentUser(),projectName, getFirst(userRealName), getLast(userRealName), fromDate, toDate, createStatus(read)));

		} else {

			// 老板
			if (getUserHolder().getCurrentUser().getRoles().contains(Role.ROLE_HEAD)) {
				Integer[] status = new Integer[] { WeeklyReport.STATUS_SUBMITTED };
				reports.addAll(WeeklyReport.findAll(status));
				reports.addAll(WeeklyReport.findByAuthor(getCurrentUser(), new Integer[] { WeeklyReport.STATUS_DRAFT }));
			} else {
				// 员工
				Integer[] status = new Integer[] { WeeklyReport.STATUS_COMMENTED, WeeklyReport.STATUS_SUBMITTED, WeeklyReport.STATUS_DRAFT };
				reports.addAll(WeeklyReport.findByAuthor(getCurrentUser(), status));
			}
		}

		return ActionSupport.SUCCESS;
	}

	// 过滤周报的状态
	private List<Integer> createStatus(boolean read) {
		List<Integer> results = new ArrayList<Integer>();
		results.add(WeeklyReport.STATUS_SUBMITTED);
		if (getCurrentUser().getRoles().contains(Role.ROLE_HEAD)) {
			results.add(WeeklyReport.STATUS_DRAFT);
		}
		// 包括审阅的时候
		if (read) {
			results.add(WeeklyReport.STATUS_COMMENTED);
		}
		return results;
	}

	// 将名字拆成姓和名
	private String getLast(String fullName) {
		String full = StringUtils.trim(fullName);
		if (StringUtils.length(full) <= 1) {
			return "";
		}
		return full.substring(1);
	}

	private String getFirst(String fullName) {
		String full = StringUtils.trim(fullName);
		if (StringUtils.length(full) <= 1) {
			return "";
		}
		return full.substring(0, 1);
	}

	public String getProjectName() {

		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public List<WeeklyReport> getReports() {
		return reports;
	}

	public void setReports(List<WeeklyReport> reports) {
		this.reports = reports;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

}
