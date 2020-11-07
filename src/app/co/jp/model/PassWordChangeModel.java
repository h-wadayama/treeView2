package app.co.jp.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PassWordChangeModel {
	private static final long serialVersionUID = 1L;

	private String loginId;

	@NotEmpty(message="e.te.fw.8002")
	private String oldPassword;

	@NotEmpty(message="e.te.fw.8002")
	@Size(min = 8,max = 8,message="e.te.fw.8004")
	private String newPassword;

	@NotEmpty(message="e.te.fw.8002")
	private String newPasswordSeccond;

	private String nowPassword;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordSeccond() {
		return newPasswordSeccond;
	}

	public void setNewPasswordSeccond(String newPasswordSeccond) {
		this.newPasswordSeccond = newPasswordSeccond;
	}

	public String getNowPassword() {
		return nowPassword;
	}

	public void setNowPassword(String nowPassword) {
		this.nowPassword = nowPassword;
	}



}
