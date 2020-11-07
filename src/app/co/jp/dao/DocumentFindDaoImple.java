package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.DocumentFindDaoIF;
import app.co.jp.entity.DocEntity;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;

@Component
public class DocumentFindDaoImple implements DocumentFindDaoIF{

	/**
	 * 処理名     ：階層全件取得
	 * メソッド名 ：getAllHierarchy()
	 * 引数       ：なし
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocEntity> getAllHierarchy() throws NamingException, SQLException {

		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		//Doc_TBLから親（最上位階層）取得
		String sql = "SELECT * FROM doc_tbl order by id";
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
			docLIst.add(docEntity);

		}

		 rs.close();
		 ps.close();
		 con.close();

		return docLIst;

	}


}
