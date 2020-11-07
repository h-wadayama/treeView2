package app.co.jp.service.IF;

import java.sql.SQLException;

import javax.naming.NamingException;

public interface IdServiceIF {

	int getMaxId() throws SQLException, NamingException;
	void updId(int id) throws NamingException, SQLException;
	void insId(int id) throws NamingException, SQLException;

}
