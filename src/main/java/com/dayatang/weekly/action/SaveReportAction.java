package com.dayatang.weekly.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.dayatang.weekly.application.ReportApplication;
import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.VehicleUsage;
import com.dayatang.weekly.domain.WeeklyReport;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "web")
public class SaveReportAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// 上传文件使用
	private List<File> upload = new ArrayList<File>();

	private List<String> uploadFileName = new ArrayList<String>();

	private List<String> uploadContentType = new ArrayList<String>();

	private long reportId = 0L;

	private WeeklyReport report;

	private List<VehicleUsage> vehicleUsages = new ArrayList<VehicleUsage>();

	private int status = 1;

	@Action(results = { @Result(name = "success", type = "redirect", location = "report-list.action"), @Result(name = "input", type = "json") })
	public String execute() {

		/*for (VehicleUsage ve : report.getVehicleUsages()) {
			System.out.println(ve.getDriver() + "\n\n\n");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

		report.setAuthor(getCurrentUser());

		if (status == WeeklyReport.STATUS_DRAFT) {// 草稿
			this.reportApplication.saveReportAsDraft(report);
			report = WeeklyReport.findTheLastOne(getCurrentUser(), new Integer[] { WeeklyReport.STATUS_DRAFT });
			reportId = report.getId();
			return ActionSupport.INPUT;

		} else if (status == WeeklyReport.STATUS_SUBMITTED) { // 呈报
			this.reportApplication.submitReport(report);
			report = WeeklyReport.findTheLastOne(getCurrentUser(), new Integer[] { WeeklyReport.STATUS_COMMENTED });
			if (getUpload() != null && !getUpload().isEmpty()) {
				List<Attachment> attas = upload(report, getUpload(), getUploadFileName(), getUploadContentType());
				SaveAttachments(this.reportApplication, attas);
			}
			return ActionSupport.SUCCESS;

		} else {
			return ActionSupport.INPUT;
		}
	}

	// 上传文件
	private List<Attachment> upload(WeeklyReport report, List<File> files, List<String> uploadsFileName, List<String> uploadDocTypes) {
		List<Attachment> results = new ArrayList<Attachment>();
		for (File file : files) {
			Attachment attachment = new Attachment();
			try {
				attachment.setData(getByteFrom(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			attachment.setReport(report);
			int index = files.indexOf(file);
			attachment.setFileName(uploadsFileName.get(index));
			attachment.setMimeType(uploadDocTypes.get(index));
			results.add(attachment);
		}
		return results;
	}

	// 保存附件
	private void SaveAttachments(ReportApplication app, List<Attachment> attachments) {
		for (Attachment at : attachments) {
			app.saveAttachment(at);
		}
	}

	// 将file转成Byte[]
	private byte[] getByteFrom(File file) throws IOException {
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

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public WeeklyReport getReport() {
		return report;
	}

	public void setReport(WeeklyReport report) {
		this.report = report;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<VehicleUsage> getVehicleUsages() {
		return vehicleUsages;
	}

	public void setVehicleUsages(List<VehicleUsage> vehicleUsages) {
		this.vehicleUsages = vehicleUsages;
	}

}
