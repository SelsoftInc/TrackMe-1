package com.selsoft.commonutility.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "COMMONUTILITY")
public class CommonUtility {
	
	private String module;
	private String submodule;
	private String code;
	private String value;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
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
