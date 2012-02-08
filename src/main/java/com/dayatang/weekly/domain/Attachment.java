package com.dayatang.weekly.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.domain.QuerySettings;

@Entity
@Table(name = "attachments")
public class Attachment extends AbstractEntity {
	
	private static final long serialVersionUID = 9091988464484909362L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "report_id", nullable = false)
	private WeeklyReport report;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "mime_type")
	private String mimeType;
	
	@Lob
	private byte[] data;

	public Attachment(){}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public static Attachment get(Long id) {
		return getRepository().get(Attachment.class, id);
	}
	
	public static List<Attachment> findByReport(WeeklyReport report) {
		return getRepository().find(QuerySettings.create(Attachment.class).eq("report", report));
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof Attachment))
			return false;
		Attachment castOther = (Attachment) other;
		return new EqualsBuilder().append(report, castOther.report).append(fileName, castOther.fileName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(report).append(fileName).toHashCode();
	}

	@Override
	public String toString() {
		return fileName;
	}

}
