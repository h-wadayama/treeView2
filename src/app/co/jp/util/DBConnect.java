package app.co.jp.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * 処理名     ：データソース取得処理
 * メソッド名 ：getDataSourceConnection()
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
public class DBConnect {

	private String dataSourceJndiName;

	public Connection getDataSourceConnection() throws NamingException, SQLException {

		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup(dataSourceJndiName);
		Connection con = ds.getConnection();

		//con = ds.getConnection();
		System.out.println(con);

		return con;
	}

	public void setDataSourceJndiName(String dataSourceJndiName) {
		this.dataSourceJndiName = dataSourceJndiName;
	}

	public String getDataSourceJndiName() {
		return dataSourceJndiName;
	}

}
