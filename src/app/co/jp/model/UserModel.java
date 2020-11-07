package app.co.jp.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import app.co.jp.util.IF.ByteSize;

public class UserModel {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^[a-zA-Z0-9]*$",message="e.te.fw.8012")
	@NotEmpty(message="e.te.fw.8002")
	private String userid;

	@ByteSize(size = 42,message="e.te.fw.8010")
	@NotEmpty(message="e.te.fw.8002")
	private String name;

	private String password;

	@Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$",message="e.te.fw.8011")
	@ByteSize(size = 20,message="e.te.fw.8010")
	private String mailaddress;

	@Size(min = 7,max = 7,message="e.te.fw.8004")
	private String zipcode;

	@ByteSize(size = 30,message="e.te.fw.8010")
	private String address1;

	@ByteSize(size = 30,message="e.te.fw.8010")
	private String address2;

	@ByteSize(size = 30,message="e.te.fw.8010")
	private String address3;

	@ByteSize(size = 15,message="e.te.fw.8010")
	private String telno;

	private String authority;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
