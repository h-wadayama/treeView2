package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import app.co.jp.entity.FileEntity;
import app.co.jp.model.FileModel;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;

@Component
public class FileDao {

	public FileEntity getFileInfo(int id) throws NamingException, SQLException{

		FileEntity fileEntity = new FileEntity();

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = DBcon.getDataSourceConnection();

		String sql = "SELECT * FROM file_tbl WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();

		if (rs.next()){
			fileEntity.setId(id);
			fileEntity.setFileid(rs.getInt("fileid"));
			fileEntity.setExtension(rs.getString("extension"));
		}

		return fileEntity;
	}


	public void insFileInfo(FileEntity fileEntity) throws NamingException, SQLException{

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sdf.format(cl.getTime());

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();

		String sql = "INSERT INTO FILE_TBL ";
		sql = sql + "(";
		sql = sql + "id";
		sql = sql + ",fileid";
		sql = sql + ",extension";
		sql = sql + ",insdate";
		sql = sql + ",upddate";
		sql = sql + ")";
		sql = sql + " VALUES ";
		sql = sql + "(";
		sql = sql + "?";
		sql = sql + ",?";
		sql = sql + ",?";
		sql = sql + ",?";
		sql = sql + ",?";
		sql = sql + ")";

		ps = con.prepareStatement(sql);
		ps.setInt(1, (int)fileEntity.getId());
		ps.setInt(2, (int)fileEntity.getFileid());
		ps.setString(3, fileEntity.getExtension());
		ps.setString(4, sysDate);
		ps.setString(5, sysDate);

		ps.executeQuery();

		con.commit();
		ps.close();
		con.close();

	}

	public int getMaxFileId() throws NamingException, SQLException{

		int id =0;

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";

		con = DBcon.getDataSourceConnection();
		sql = "select Max(fileid) fileid from file_tbl";
		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		if (rs.next()){
			id = rs.getInt("fileid");
		}

		return id;


	}


	public FileEntity setFileEntity(FileModel fileModel){

		FileEntity fileEntity = new FileEntity();

		fileEntity.setId(Integer.parseInt(fileModel.getId()));
		fileEntity.setFileid(Integer.parseInt(fileModel.getFileid()));
		fileEntity.setExtension(fileModel.getExtension());

		return fileEntity;
	}


}
