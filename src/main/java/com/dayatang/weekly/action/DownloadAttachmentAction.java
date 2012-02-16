package com.dayatang.weekly.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.dayatang.weekly.domain.Attachment;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zjzhai
 *
 */
/**
 * @author zjzhai
 * 
 */
@Action(value = "dowload-attachment", results = { @Result(name = "success", type = "stream") }, 
params = { "contentType", "${contentType}", 
		"inputName", "inputStream", 
		"bufferSize", "4096" ,
		"contentDisposition","attachment;filename=\"${contentDisposition}\""})
@Namespace("/")
public class DownloadAttachmentAction extends BaseAction {

	private static final long serialVersionUID = -7428537974210559433L;

	private long id = 0L;

	private String contentType;
	private String contentDisposition;

	private InputStream inputStream;

	public InputStream getInputStream() {
		long attaId = this.getId();
		Attachment at = Attachment.get(attaId);
		contentType = at.getMimeType();
		contentDisposition = at.getFileName();
		inputStream = new ByteArrayInputStream(at.getData());
		return inputStream;
	}

	public String execute() {
		return ActionSupport.SUCCESS;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
