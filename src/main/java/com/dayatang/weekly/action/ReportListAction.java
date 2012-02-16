package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import com.dayatang.weekly.domain.Role;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class ReportListAction extends BaseAction {

	private static final long serialVersionUID = 2577807854273795934L;

	private List<WeeklyReport> reports = new ArrayList<WeeklyReport>();
	
	public String execute() {
		//老板
		if(getUserHolder().getCurrentUser().getRoles().contains(Role.ROLE_HEAD)){
			Integer[] status = new Integer[] { WeeklyReport.STATUS_COMMENTED, WeeklyReport.STATUS_SUBMITTED };
			reports.addAll(WeeklyReport.findAll(status));
		}else{
			//员工
			Integer[] status = new Integer[] { WeeklyReport.STATUS_COMMENTED, WeeklyReport.STATUS_SUBMITTED,WeeklyReport.STATUS_DRAFT };
			reports.addAll(WeeklyReport.findByAuthor(getCurrentUser(), status));
		}
			
	
		return ActionSupport.SUCCESS;
	}

	public List<WeeklyReport> getReports() {
		return reports;
	}

	public void setReports(List<WeeklyReport> reports) {
		this.reports = reports;
	}

}
