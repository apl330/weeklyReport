package com.dayatang.weekly.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.dayatang.domain.ValueObject;

@Embeddable
public class VehicleUsage implements ValueObject {

	private static final long serialVersionUID = -3814917335207792092L;

	//使用日期
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date fromDate;
	
	//车牌号码
	@Column(name = "license_plate_number")
	private String licensePlateNumber;
	
	private String driver;
	
	//开始里程
	@Column(name = "start_mileage")
	private int startMileage;
	
	@Column(name = "end_mileage")
	private int endMileage;
	
	//开始地点
	@Column(name = "from_place")
	private String fromPlace;
	
	//结束地点
	@Column(name = "to_place")
	private String toPlace;

	public VehicleUsage() {
	}

	public VehicleUsage(Date fromDate, String licensePlateNumber, String driver, int startMileage, int endMileage,
			String fromPlace, String toPlace) {
		super();
		this.fromDate = fromDate;
		this.licensePlateNumber = licensePlateNumber;
		this.driver = driver;
		this.startMileage = startMileage;
		this.endMileage = endMileage;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the licensePlateNumber
	 */
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	/**
	 * @param licensePlateNumber the licensePlateNumber to set
	 */
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the startMileage
	 */
	public int getStartMileage() {
		return startMileage;
	}

	/**
	 * @param startMileage the startMileage to set
	 */
	public void setStartMileage(int startMileage) {
		this.startMileage = startMileage;
	}

	/**
	 * @return the endMileage
	 */
	public int getEndMileage() {
		return endMileage;
	}

	/**
	 * @param endMileage the endMileage to set
	 */
	public void setEndMileage(int endMileage) {
		this.endMileage = endMileage;
	}

	public int getMileage() {
		return endMileage - startMileage;
	}

	/**
	 * @return the fromPlace
	 */
	public String getFromPlace() {
		return fromPlace;
	}

	/**
	 * @param fromPlace the fromPlace to set
	 */
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	/**
	 * @return the toPlace
	 */
	public String getToPlace() {
		return toPlace;
	}

	/**
	 * @param toPlace the toPlace to set
	 */
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof VehicleUsage))
			return false;
		VehicleUsage castOther = (VehicleUsage) other;
		return new EqualsBuilder().append(licensePlateNumber, castOther.licensePlateNumber).append(startMileage,
				castOther.startMileage).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(licensePlateNumber).append(startMileage).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fromDate", fromDate)
				.append("licensePlateNumber", licensePlateNumber).append("driver", driver).append("startMileage",
						startMileage).append("endMileage", endMileage).append("fromPlace", fromPlace).append("toPlace",
						toPlace).toString();
	}

}
