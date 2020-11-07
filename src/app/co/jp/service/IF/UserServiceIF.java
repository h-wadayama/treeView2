package app.co.jp.service.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.support.PagedListHolder;

import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;
import app.co.jp.model.UserModel;

public interface UserServiceIF {

	List<UserModel> setUserListToFormService(List<UserEntity> userLIst) ;
	UserModel setUserToFormService(UserEntity user);
	UserEntity setUserFormToEntityService(UserModel user) ;
	List<UserModel> getUserListAll() throws NamingException, SQLException;
	UserEntity getUser (String userId) throws NamingException, SQLException;
	Object[] getUser (LoginFormModel loginFormModel) throws NamingException, SQLException;
	Object[] getUser(String userCode,String password) throws NamingException, SQLException;
	int delUserTbl(String userId) throws NamingException, SQLException;
	void insUserTbl(UserEntity userEntity) throws NamingException, SQLException;
	void updUserTbl(UserEntity userEntity) throws NamingException, SQLException;
	UserModel setUserModel(UserModel user);
	PagedListHolder<UserModel> userListPageingSet(int page) throws NamingException, SQLException;
	void updPasssword(String userId, String password) throws NamingException, SQLException;
}
