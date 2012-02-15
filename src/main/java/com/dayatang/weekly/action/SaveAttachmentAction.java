package com.dayatang.weekly.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.annotations.JSON;

import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zjzhai
 * 
 */
@ParentPackage("web")
public class SaveAttachmentAction extends BaseAction {

	private static final long serialVersionUID = -7900945051273759214L;

	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	private long reportId = 0L;

	private Attachment atta;

	@Action(results = { @Result(name = "success", type = "redirect",location="add-attachment.action",params={"reportId","${reportId}"}), @Result(name = "input", type = "redirect", location = "report-list.action"), @Result(name = "error", type = "json") })
	public String execute() {

		if (reportId == 0l || upload == null) {
			return ActionSupport.INPUT;
		}

		if (nonAccess(upload)) {
			return ActionSupport.ERROR;
		}

		WeeklyReport report = WeeklyReport.load(WeeklyReport.class, reportId);
		atta = createAttchment(report, getUpload(), uploadFileName, uploadContentType);
		this.reportApplication.saveAttachment(atta);
		atta = Attachment.getLastOneOf(report);

		return ActionSupport.SUCCESS;
	}

	private Attachment createAttchment(WeeklyReport report, File file, String fileName, String contentType) {
		Attachment result = new Attachment();
		result.setData(getByteFrom(file));
		result.setReport(report);
		result.setMimeType(contentType);
		result.setFileName(fileName);
		return result;
	}

	// 检查文件大小
	private boolean nonAccess(File file) {
		if (file.length() > (1000 * 1000000)) {
			return true;
		}
		return false;
	}

	// 将file转成Byte[]
	private byte[] getByteFrom(File file) {
		byte[] result = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			result = out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(in);
			close(out);
		}
		return result;
	}

	// 关闭流
	private void close(FileInputStream in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		in = null;
	}

	private void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = null;
	}

	@JSON(serialize = false)
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@JSON(serialize = false)
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@JSON(serialize = false)
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public Attachment getAtta() {
		return atta;
	}

	public void setAtta(Attachment atta) {
		this.atta = atta;
	}

}
