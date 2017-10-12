package com.selsoft.pdftemplate;

public class RentalCompany {
	private String streetAddress;
	private String city;
	private int stZip;
	private int phone;
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getStZip() {
		return stZip;
	}
	public void setStZip(int stZip) {
		this.stZip = stZip;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RentalCompany [streetAddress=");
		builder.append(streetAddress);
		builder.append(", city=");
		builder.append(city);
		builder.append(", stZip=");
		builder.append(stZip);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}
	

}
