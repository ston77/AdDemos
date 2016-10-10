package com.itheima.querycontacts.domain;

public class ContactInfo {

	private String name;
	private String number ;
	private String email ;
	private String im;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIm() {
		return im;
	}
	public void setIm(String im) {
		this.im = im;
	}
	@Override
	public String toString() {
		return "ContactInfo [name=" + name + ", number=" + number + ", email="
				+ email + ", im=" + im + "]";
	}
	
	
}
