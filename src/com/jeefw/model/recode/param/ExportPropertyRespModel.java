package com.jeefw.model.recode.param;

import java.io.Serializable;

public class ExportPropertyRespModel <T,G> extends BaseModel<T,G> implements Serializable{
	
	private String id;
	
	private String buildcode;
	
	private String year;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildcode() {
		return buildcode;
	}

	public void setBuildcode(String buildcode) {
		this.buildcode = buildcode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
}
