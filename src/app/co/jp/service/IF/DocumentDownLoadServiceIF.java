package app.co.jp.service.IF;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

public interface DocumentDownLoadServiceIF {
	void downLoad(String id,HttpServletResponse response) throws NamingException, SQLException, IOException;
}
