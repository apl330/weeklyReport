package com.dayatang.weekly.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.dayatang.domain.ValueObject;

public class FileInfo implements ValueObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String url;

	public FileInfo(){}

	public FileInfo(String name, String type, String url) {
		super();
		this.name = name;
		this.type = type;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("url", url).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FileInfo))
			return false;
		FileInfo castOther = (FileInfo) other;
		return new EqualsBuilder().append(url, castOther.url).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(url).toHashCode();
	}


}
