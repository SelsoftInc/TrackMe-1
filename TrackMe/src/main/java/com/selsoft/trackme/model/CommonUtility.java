package com.selsoft.trackme.model;

import org.springframework.data.annotation.Id;

public class CommonUtility {
	
	private String module;
	private String submodule;
	@Id
	private int code;
	private int value;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSubmodule() {
		return submodule;
	}
	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonUtility [module=");
		builder.append(module);
		builder.append(", submodule=");
		builder.append(submodule);
		builder.append(", code=");
		builder.append(code);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	

}
