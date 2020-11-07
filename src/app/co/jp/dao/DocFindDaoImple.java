package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.DocFindDaoIF;
import app.co.jp.entity.DocEntity;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;


@Component
public class DocFindDaoImple implements DocFindDaoIF{

	/**
	 * 処理名     ：親取得
	 * メソッド名 ：getPearent()
	 * 引数       ：なし
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocEntity> getPearent() throws NamingException, SQLException {

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		//Doc_TBLから親（最上位階層）取得
		String sql = "SELECT * FROM doc_tbl WHERE parentId is null order by id";
		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		List<DocEntity> docLIst = new ArrayList<DocEntity>();

		while(rs.next()){

			DocEntity docEntity = new DocEntity();
			docEntity.setId(rs.getInt("id"));
			docEntity.setObjectTipe(rs.getString("objectTipe"));
			docEntity.setObjectName(rs.getString("objectName"));
			docEntity.setParentId(rs.getString("parentId"));
//			docEntity.setExtension(rs.getString("extension"));
//			docEntity.setFileid(rs.getInt("fileid"));
			docLIst.add(docEntity);

		}

		 rs.close();
		 ps.close();
		 con.close();

		return docLIst;

	}

	/**
	 * 処理名     ：ドキュメント1件取得
	 * メソッド名 ：getDocument()
	 * 引数       ：String id
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public DocEntity getDocument(int id) throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = DBcon.getDataSourceConnection();

		//Doc_TBLから子階層取得
		String sql = "SELECT * FROM doc_tbl WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();


		rs.next();
		DocEntity docEntity = new DocEntity();
		docEntity.setId(rs.getInt("id"));
		docEntity.setObjectTipe(rs.getString("objectTipe"));
		docEntity.setObjectName(rs.getString("objectName"));
		docEntity.setParentId(rs.getString("parentId"));
//		docEntity.setExtension(rs.getString("extension"));
//		docEntity.setFileid(rs.getInt("fileid"));

		 rs.close();
		 ps.close();
		 con.close();

		return docEntity;


	}

	/**
	 * 処理名     ：子取得
	 * メソッド名 ：getChild()
	 * 引数       ：なし
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocEntity> getChild(String pearentId) throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = DBcon.getDataSourceConnection();

		//Doc_TBLから子階層取得
		String sql = "SELECT * FROM doc_tbl WHERE parentId = ? Order By objectName";
		ps = con.prepareStatement(sql);
		ps.setString(1, pearentId);
		rs = ps.executeQuery();

		List<DocEntity> docLIst = new ArrayList<DocEntity>();

		while(rs.next()){

			DocEntity docEntity = new DocEntity();
			docEntity.setId(rs.getInt("id"));
			docEntity.setObjectTipe(rs.getString("objectTipe"));
			docEntity.setObjectName(rs.getString("objectName"));
			docEntity.setParentId(rs.getString("parentId"));
//			docEntity.setExtension(rs.getString("extension"));
//			docEntity.setFileid(rs.getInt("fileid"));
			docLIst.add(docEntity);

		}

		 rs.close();
		 ps.close();
		 con.close();

		return docLIst;


	}

	/**
	 * 処理名     ：階層取得
	 * メソッド名 ：getHierarchy()
	 * 引数       ：Level,pearentId
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocEntity> getHierarchy(int lv,String pearentId) throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = DBcon.getDataSourceConnection();

		//Doc_TBLから子階層取得
		String sql = "SELECT * FROM doc_tbl WHERE objLevel = ? and parentId = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1,lv);
		ps.setString(2,pearentId);
		rs = ps.executeQuery();

		List<DocEntity> docLIst = new ArrayList<DocEntity>();

		while(rs.next()){

			DocEntity docEntity = new DocEntity();
			docEntity.setId(rs.getInt("id"));
			docEntity.setObjectTipe(rs.getString("objectTipe"));
			docEntity.setObjectName(rs.getString("objectName"));
			docEntity.setParentId(rs.getString("parentId"));
//			docEntity.setExtension(rs.getString("extension"));
//			docEntity.setFileid(rs.getInt("fileid"));
			docLIst.add(docEntity);

		}

		 rs.close();
		 ps.close();
		 con.close();

		return docLIst;

	}

	/**
	 * 処理名     ：階層取得
	 * メソッド名 ：getHierarchy()
	 * 引数       ：Level
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocEntity> getHierarchy(int lv) throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = DBcon.getDataSourceConnection();

		//Doc_TBLから子階層取得
		String sql = "SELECT * FROM doc_tbl WHERE objLevel = ? ";
		ps = con.prepareStatement(sql);
		ps.setInt(1,lv);
		rs = ps.executeQuery();

		List<DocEntity> docLIst = new ArrayList<DocEntity>();

		while(rs.next()){

			DocEntity docEntity = new DocEntity();
			docEntity.setId(rs.getInt("id"));
			docEntity.setObjectTipe(rs.getString("objectTipe"));
			docEntity.setObjectName(rs.getString("objectName"));
			docEntity.setParentId(rs.getString("parentId"));
//			docEntity.setExtension(rs.getString("extension"));
//			docEntity.setFileid(rs.getInt("fileid"));
			docLIst.add(docEntity);

		}

		 rs.close();
		 ps.close();
		 con.close();

		return docLIst;

	}


	public String getNewId() throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		int intNewId;
		String strNewId="";

		con = DBcon.getDataSourceConnection();
		sql = "select max(id) newId from doc_tbl";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()){
			intNewId = rs.getInt("newId");
			strNewId = String.valueOf(intNewId+1);
		}

		return strNewId;
	}

	public String getParentId(String id) throws NamingException, SQLException{

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		int intId = Integer.parseInt(id);
		String strId="";

		con = DBcon.getDataSourceConnection();
		sql = "select parentid from doc_tbl where id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1,intId);
		rs = ps.executeQuery();

		if (rs.next()){
			intId = rs.getInt("parentid");
			strId = String.valueOf(intId);
		}

		return strId;
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
		sql = "select Max(fileid) fileid from doc_tbl";
		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();

		if (rs.next()){
			id = rs.getInt("fileid");
		}

		return id;
	}

}
