package com.dayatang.weekly.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.domain.QuerySettings;

@Entity
@Table(name = "vehicle_usages")
public class VehicleUsage extends AbstractEntity {

	private static final long serialVersionUID = -3814917335207792092L;

	@ManyToOne
	@JoinColumn(name="report_id")
	private WeeklyReport report;

	// 使用日期
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date fromDate;

	// 车牌号码
	@Column(name = "license_plate_number")
	private String licensePlateNumber;

	// 司机
	private String driver;

	// 开始里程
	@Column(name = "start_mileage")
	private int startMileage;

	@Column(name = "end_mileage")
	private int endMileage;

	// 开始地点
	@Column(name = "from_place")
	private String fromPlace;

	// 结束地点
	@Column(name = "to_place")
	private String toPlace;


	/**
	 * 拿到周报中最后车辆使用情况
	 * @param report
	 * @return
	 */
	public static VehicleUsage getLastOneOf(WeeklyReport report){
		QuerySettings<VehicleUsage> settings = QuerySettings.create(VehicleUsage.class).eq("report", report).desc("id");
		List<VehicleUsage> results = new ArrayList<VehicleUsage>();
		results.addAll(getRepository().find(settings));
		return results.size() > 0 ? results.get(0) : null;
		
	}
	
	
	public VehicleUsage() {
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(int startMileage) {
		this.startMileage = startMileage;
	}

	public int getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(int endMileage) {
		this.endMileage = endMileage;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof VehicleUsage))
			return false;
		VehicleUsage castOther = (VehicleUsage) other;
		return new EqualsBuilder().append(licensePlateNumber, castOther.licensePlateNumber).append(startMileage, castOther.startMileage).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(licensePlateNumber).append(startMileage).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fromDate", fromDate).append("licensePlateNumber", licensePlateNumber).append("driver", driver).append("startMileage", startMileage).append("endMileage", endMileage).append("fromPlace", fromPlace)
				.append("toPlace", toPlace).toString();
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

}
