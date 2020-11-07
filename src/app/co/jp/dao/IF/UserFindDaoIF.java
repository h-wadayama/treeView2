package app.co.jp.dao.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;

public interface UserFindDaoIF {

	List<UserEntity> getUserListAll() throws NamingException, SQLException;
	UserEntity getUser (String userId) throws NamingException, SQLException;
	Object[] getUser (LoginFormModel loginFormModel) throws NamingException, SQLException;
	Object[] getUser(String userCode,String password) throws NamingException, SQLException;

}
