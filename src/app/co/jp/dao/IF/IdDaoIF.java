package app.co.jp.dao.IF;

import java.sql.SQLException;

import javax.naming.NamingException;

public interface IdDaoIF {

	int getMaxId() throws SQLException, NamingException;
	void updId(int id) throws NamingException, SQLException;
	void insId(int id) throws NamingException, SQLException;
}
