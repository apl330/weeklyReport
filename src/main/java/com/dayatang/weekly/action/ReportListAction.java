package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class ReportListAction extends BaseAction {

	private static final long serialVersionUID = 2577807854273795934L;

	private List<WeeklyReport> reports = new ArrayList<WeeklyReport>();
	
	public String execute() {
		
		reports.addAll(WeeklyReport.findAll(new Integer[]{WeeklyReport.STATUS_COMMENTED}));
		for(WeeklyReport wr : reports){
			System.out.println(wr.getAuthor() + " : " + wr.getAuthor().getLastName() + "\n\n\n");
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
