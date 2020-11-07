package app.co.jp.service.IF;

import java.sql.SQLException;

import javax.naming.NamingException;

import app.co.jp.model.UserModel;

public interface UserCheckerIF {

	Object[] userCheck(UserModel userModel) throws NamingException, SQLException;

}
