package app.co.jp.business.IF;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.validation.BindingResult;

import app.co.jp.model.LoginFormModel;

public interface LoginCheckerIF {

	public Object[] loginCheck(LoginFormModel loginForm) throws NamingException, SQLException;
	public Object[] loginItemCheck(BindingResult result);

}
