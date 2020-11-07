package app.co.jp.model;


import org.hibernate.validator.constraints.NotEmpty;

public class LoginFormModel {

	@NotEmpty(message="e.te.fw.8002")
	private String loginId;
	@NotEmpty(message="e.te.fw.8002")
	private String password;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
