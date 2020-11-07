package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.IdDaoIF;
import app.co.jp.entity.IdEntity;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;

@Component
public class IdDaoImple implements IdDaoIF{
	public int getMaxId() throws SQLException, NamingException{

		IdEntity idEntity = new IdEntity();
		int id = 0;

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		String sql = "select Max(id) id from id_tbl";

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()){
			idEntity.setId(rs.getInt("id"));
			id = idEntity.getId();
		}
		return id;
	}

	public void updId(int id) throws NamingException, SQLException{

		CommonUtil commonUtil = new CommonUtil();
		DBConnect DBcon = commonUtil.getContext();

		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();

		String sql = "";
		sql = "UPDATE id_tbl ";
		sql = sql + "SET ";
		sql = sql + "id =  ? ";
		sql = sql + "WHERE no = 1";

		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();

		con.commit();
		ps.close();
		con.close();

	}

	public void insId(int id) throws NamingException, SQLException{

		CommonUtil commonUtil = new CommonUtil();
		DBConnect DBcon = commonUtil.getContext();

		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();

		String sql = "";
		sql = "INSERT INTO id_tbl (";
		sql = sql + "no ";
		sql = sql + ",id " ;
		sql = sql + ") VALUES (";
		sql = sql + " ? ";
		sql = sql + ",? )";

		ps = con.prepareStatement(sql);
		ps.setInt(1, 1);
		ps.setInt(2, id);
		ps.executeUpdate();

		con.commit();
		ps.close();
		con.close();

	}

}
