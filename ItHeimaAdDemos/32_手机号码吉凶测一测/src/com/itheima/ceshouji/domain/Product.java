package com.itheima.ceshouji.domain;
/*
 * 
<smartresult>
	<product type="mobile">
	    <phonenum>13691689238</phonenum>
	    <location>�㶫�����ƶ������п�</location>
	    <phoneJx>����ɵã������ѻ��ս緢չ�������ɹ� �״���</phoneJx>
	</product>
</smartresult>
 * 
 */
public class Product {

	private String type;
	private String phonenum;
	private String location;
	private String phoneJx;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoneJx() {
		return phoneJx;
	}
	public void setPhoneJx(String phoneJx) {
		this.phoneJx = phoneJx;
	}
	
}
