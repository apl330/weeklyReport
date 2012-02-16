package com.dayatang.weekly.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.Attachment;
import com.opensymphony.xwork2.ActionSupport;

public class RemoveAttachmentAction extends BaseAction {

	private static final long serialVersionUID = -4723893188303904848L;

	private long id = 0L;
	private long reportId = 0L;

	@Action(results = { @Result(name = "success", type="redirect" , location="add-attachment.action",params={"reportId","${reportId}"}) })
	public String execute() {
		
		Attachment atta = Attachment.get(id);
		if(atta!=null){
			this.reportApplication.removeAttachment(atta);
		}
		return ActionSupport.SUCCESS;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

}
