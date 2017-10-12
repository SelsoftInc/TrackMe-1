package com.selsoft.pdftemplate;

import java.util.Date;

public class Statement {
 private Date statementdate;
 private int CustomerId;
public Date getStatementdate() {
	return statementdate;
}
public void setStatementdate(Date statementdate) {
	this.statementdate = statementdate;
}
public int getCustomerId() {
	return CustomerId;
}
public void setCustomerId(int customerId) {
	CustomerId = customerId;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Statement [statementdate=");
	builder.append(statementdate);
	builder.append(", CustomerId=");
	builder.append(CustomerId);
	builder.append("]");
	return builder.toString();
}
 
 
}
