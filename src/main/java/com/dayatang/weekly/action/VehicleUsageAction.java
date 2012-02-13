package com.dayatang.weekly.action;

import java.util.ArrayList;
import java.util.List;

import com.dayatang.weekly.application.ReportApplication;
import com.dayatang.weekly.domain.VehicleUsage;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class VehicleUsageAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private VehicleUsage vehicleUsage = new VehicleUsage();
	private List<VehicleUsage> vehicleUsages = new ArrayList<VehicleUsage>();
	private WeeklyReport report = new WeeklyReport();
	private Long reportId;
	private int index;



	public VehicleUsage getVehicleUsage() {
		return vehicleUsage;
	}

	public void setVehicleUsage(VehicleUsage vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}

	public List<VehicleUsage> getVehicleUsages() {
		return vehicleUsages;
	}

	public void setVehicleUsages(List<VehicleUsage> vehicleUsages) {
		this.vehicleUsages = vehicleUsages;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String add() {
		report = WeeklyReport.get(reportId);
		report.addVehicleUsage(vehicleUsage);
		getReportApplication().saveReportAsDraft(report);
		return SUCCESS;
	}

	public String remove() {
		report = WeeklyReport.get(reportId);
		vehicleUsage = report.getVehicleUsages().get(index);
		report.removeVehicleUsage(vehicleUsage);
		getReportApplication().saveReportAsDraft(report);
		return SUCCESS;
	}

	public String list() {
		report = WeeklyReport.get(reportId);
		vehicleUsages = report.getVehicleUsages();
		return SUCCESS;
	}
	
	public String displayAddButton() {
		return SUCCESS;
	}
	
	public String displayNewRow() {
		return SUCCESS;
	}
}
