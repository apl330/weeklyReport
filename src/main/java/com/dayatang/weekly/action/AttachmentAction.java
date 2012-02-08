package com.dayatang.weekly.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import com.dayatang.weekly.application.ReportApplication;
import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

public class AttachmentAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	private Long reportId;
	private WeeklyReport report = new WeeklyReport();

	private Long attachmentId;
	private Attachment attachment = new Attachment();
	private List<Attachment> attachments = new ArrayList<Attachment>();

	private ReportApplication reportApplication;

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

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
		attachment = Attachment.get(attachmentId);
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		try {
			this.upload = upload;
		} catch (Exception e) {
			addFieldError("upload", "文件大小不能超过120MB");
		}
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	/**
	 * @param reportApplication
	 *            the reportApplication to set
	 */
	public void setReportApplication(ReportApplication reportApplication) {
		this.reportApplication = reportApplication;
	}

	public String remove() {
		Attachment attachment = Attachment.get(attachmentId);
		getRepository().remove(attachment);
		return SUCCESS;
	}

	public String upload() throws Exception {
		FileInputStream in = new FileInputStream(getUpload());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] temp = new byte[1024];
		int size = 0;
		while ((size = in.read(temp)) != -1) {
			out.write(temp, 0, size);
		}
		in.close();
		attachment = new Attachment();
		attachment.setData(out.toByteArray());
		in.close();
		out.close();
		attachment.setReport(WeeklyReport.get(reportId));
		attachment.setFileName(uploadFileName);
		attachment.setMimeType(uploadContentType);
		getReportApplication().saveAttachment(attachment);
		return SUCCESS;
	}

	public String getUrlEncodedFilename() throws UnsupportedEncodingException {
		//return new String(attachment.getFileName().getBytes("ISO-8859-1"), "UTF-8");
		//String result = new String(attachment.getFileName().getBytes("ISO-8859-1"), "UTF-8");
		//return new String(result.getBytes(), "ISO-8859-1");
		//return MimeUtility.encodeText(attachment.getFileName(), "UTF-8", "B");
		return URLEncoder.encode(attachment.getFileName(), "UTF-8");
	}
	
	public String toUpload() {
		return SUCCESS;
	}

	public String download() {
		return SUCCESS;
	}

	public String list() {
		report = WeeklyReport.get(reportId);
		attachments = Attachment.findByReport(WeeklyReport.get(reportId));
		return SUCCESS;
	}
	
	public InputStream getData() {
		return new ByteArrayInputStream(attachment.getData());
	}

}
