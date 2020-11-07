package app.co.jp.dao.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.entity.UserEntity;
import app.co.jp.model.DocModel;

public interface UserInsUpdDaoIF {

	void updUserTbl(UserEntity userEntity) throws NamingException, SQLException;
	void insUserTbl(UserEntity userEntity) throws NamingException, SQLException;
	int delUserTbl(String userId) throws NamingException, SQLException;
	List<DocModel> getHierarchy(int lv) throws NamingException, SQLException;
	void updPasssword(String userId, String password) throws NamingException, SQLException;

}
