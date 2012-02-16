package com.dayatang.weekly.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.domain.QuerySettings;

@Entity
@Table(name = "weekly_reports")
public class WeeklyReport extends AbstractEntity implements Comparable<WeeklyReport> {
	public static final int STATUS_DRAFT = 1;
	public static final int STATUS_SUBMITTED = 2;
	public static final int STATUS_COMMENTED = 3;

	private static final long serialVersionUID = -5694886020325909212L;

	@Column(name = "project_name")
	private String projectName;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "work_place")
	private String workPlace;

	@Lob
	@Column(name = "done_works")
	private String doneWorks;

	@Lob
	@Column(name = "todo_works")
	private String toDoWorks;

	@Lob
	private String comment;

	private int status;

	/* 备注 */
	@Lob
	private String memo;

	@Temporal(TemporalType.DATE)
	@Column(name = "submit_date")
	private Date submitDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "comment_date")
	private Date commentDate;

	@OneToMany(mappedBy="report")
	private List<VehicleUsage> vehicleUsages ;

	@Transient
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", Locale.CHINA);

	public WeeklyReport() {
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate
	 *            the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public List<VehicleUsage> getVehicleUsages() {
		return vehicleUsages;
	}

	public void setVehicleUsages(List<VehicleUsage> vehicleUsages) {
		this.vehicleUsages = vehicleUsages;
	}

	public void addVehicleUsage(VehicleUsage vehicleUsage) {
		vehicleUsages.add(vehicleUsage);
	}

	public void addVehicleUsages(List<VehicleUsage> vehicleUsages) {
		this.vehicleUsages.addAll(vehicleUsages);
	}

	public void removeVehicleUsage(VehicleUsage vehicleUsage) {
		vehicleUsages.remove(vehicleUsage);
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getDoneWorks() {
		return doneWorks;
	}

	public void setDoneWorks(String doneWorks) {
		this.doneWorks = doneWorks;
	}

	public String getToDoWorks() {
		return toDoWorks;
	}

	public void setToDoWorks(String toDoWorks) {
		this.toDoWorks = toDoWorks;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusString() {
		if (status == WeeklyReport.STATUS_DRAFT) {
			return resourceBundle.getString("weeklyReport.status.draft");
		}
		if (status == WeeklyReport.STATUS_SUBMITTED) {
			return resourceBundle.getString("weeklyReport.status.submitted");
		}
		if (status == WeeklyReport.STATUS_COMMENTED) {
			return resourceBundle.getString("weeklyReport.status.commented");
		}
		return "";
	}

	public void saveAsDraft() {
		setStatus(STATUS_DRAFT);
		this.save();
	}

	public void submit() {
		setStatus(STATUS_SUBMITTED);
		setSubmitDate(new Date());
		this.save();
	}

	public void makeComment() {
		setStatus(STATUS_COMMENTED);
		setCommentDate(new Date());
		this.save();
	}

	public static WeeklyReport get(Long id) {
		return getRepository().get(WeeklyReport.class, id);
	}

	public static List<WeeklyReport> findAll(Integer[] status) {
		QuerySettings<WeeklyReport> querySettings = QuerySettings.create(WeeklyReport.class).desc("submitDate").desc("id");
		if (status.length > 0) {
			querySettings = querySettings.in("status", status);
		}
		return getRepository().find(querySettings);
	}

	public static List<WeeklyReport> findByAuthor(User user, Integer status[]) {
		QuerySettings<WeeklyReport> querySettings = QuerySettings.create(WeeklyReport.class).eq("author.id", user.getId()).desc("id");
		if (status.length > 0) {
			querySettings = querySettings.in("status", status);
		}
		return getRepository().find(querySettings);
	}

	public static WeeklyReport findTheLastOne(User user, Integer status[]) {
		QuerySettings<WeeklyReport> querySettings = QuerySettings.create(WeeklyReport.class).eq("author.id", user.getId()).desc("id");
		if (status.length > 0) {
			querySettings = querySettings.in("status", status);
		}
		return getRepository().getSingleResult(querySettings);
	}

	
	public static List<WeeklyReport> findByExample(WeeklyReport report, final Date fromDate, final Date toDate) {
		QuerySettings<WeeklyReport> querySettings = QuerySettings.create(WeeklyReport.class).desc("submitDate");
		if (report.getAuthor() != null && !StringUtils.isEmpty(report.getAuthor().getUsername())) {
			querySettings = querySettings.eq("author.lastName", report.getAuthor().getUsername());
		}
		if (!StringUtils.isEmpty(report.getProjectName())) {
			querySettings = querySettings.eq("projectName", report.getProjectName());
		}
		if (!StringUtils.isEmpty(report.getWorkPlace())) {
			querySettings = querySettings.eq("workPlace", report.getWorkPlace());
		}
		if (report.getStatus() > 0) {
			querySettings = querySettings.eq("status", report.getStatus());
		}
		if (fromDate != null) {
			querySettings = querySettings.ge("submitDate", fromDate);
		}
		if (toDate != null) {
			querySettings = querySettings.le("submitDate", toDate);
		}
		return getRepository().find(querySettings);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("projectName", projectName).append("author", author).append("fromDate", fromDate).append("toDate", toDate).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof WeeklyReport))
			return false;
		WeeklyReport castOther = (WeeklyReport) other;
		return new EqualsBuilder().append(projectName, castOther.projectName).append(fromDate, castOther.fromDate).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(projectName).append(fromDate).toHashCode();
	}

	public int compareTo(WeeklyReport other) {
		return -this.getFromDate().compareTo(other.getFromDate());
	}

	public boolean isAllowEditBy(User user) {
		return getAuthor().equals(user) && getStatus() == WeeklyReport.STATUS_DRAFT;
	}

	public boolean isAllowCommentBy(User user) {
		return user.getRoles().contains(Role.ROLE_HEAD) && getStatus() > WeeklyReport.STATUS_DRAFT;
	}

}
