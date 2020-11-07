package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.DocInsUpdDaoIF;
import app.co.jp.entity.DocEntity;
import app.co.jp.model.DocModel;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.Const;
import app.co.jp.util.DBConnect;

@Component
public class DocInsUpdDaoImpl implements DocInsUpdDaoIF{

    private static final Logger logger = LoggerFactory
            .getLogger(DocInsUpdDaoImpl.class);

	public void dellDocTableALL() throws NamingException, SQLException{

		logger.warn("DocInsUpdDaoImpl#dellDocTableALL() Start");

		String sql = "";
		sql = "DELETE FROM DOC_TBL  ";
		logger.warn(sql);

		CommonUtil commonUtil = new CommonUtil();
		DBConnect DBcon = commonUtil.getContext();

		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();

		//con.commit();

		ps.close();
		con.close();

		logger.warn("DocInsUpdDaoImpl#dellDocTableALL() End");
	}

	public void inslDocTableALL(List<DocEntity> docEntityList) throws NamingException, SQLException{

		logger.warn("DocInsUpdDaoImpl#inslDocTableALL(List<DocEntity> docEntity) Start");
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sdf.format(cl.getTime());

		CommonUtil commonUtil = new CommonUtil();
		DBConnect DBcon = commonUtil.getContext();

		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();

		for(DocEntity docEntity:docEntityList ){

			String sql = "";
			sql = "INSERT INTO doc_tbl (";
			sql = sql + "id";
			sql = sql + ",objectName";
			sql = sql + ",objectTipe";
			sql = sql + ",parentId";
			sql = sql + ",insdate";
			sql = sql + ",upddate";
			sql = sql + ")";
			sql = sql + "values (";
			sql = sql + "?";
			sql = sql + ",?";
			sql = sql + ",?";
			sql = sql + ",?";
			sql = sql + ",?";
			sql = sql + ",?";
			sql = sql + ")";

			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) docEntity.getId());
			ps.setString(2, docEntity.getObjectName());
			ps.setString(3, docEntity.getObjectTipe());
			ps.setString(4, docEntity.getParentId());
//			ps.setString(5, docEntity.getExtension());

//			if (docEntity.getFileid() != null  ){
//				ps.setInt(6, (int)docEntity.getFileid());
//			}else{
//				ps.setNull(6, java.sql.Types.NULL);
//			}

			ps.setString(5, sysDate);
			ps.setString(6, sysDate);
			ps.executeUpdate();
			logger.warn(sql);

		}
		con.commit();
		ps.close();
		con.close();

		logger.warn("DocInsUpdDaoImpl#inslDocTableALL(List<DocEntity> docEntity) End");

	}

	public List<DocEntity> setDocEntity(List<DocModel> docModelList){

		List<DocEntity> docEntityList = new ArrayList<DocEntity>();

		for(DocModel doc:docModelList  ){
			String extension="";
			if (doc.getIcon().equals(Const.ICON_FILE_KBN)){
				extension=doc.getText().substring(doc.getText().lastIndexOf("."));
			}

			DocEntity docEntity = new DocEntity();
			docEntity.setId(Integer.parseInt(doc.getId()));
			docEntity.setObjectName(doc.getText());
			docEntity.setObjectTipe(doc.getIcon());
			docEntity.setParentId(doc.getParentId());
			//docEntity.setExtension(doc.getExtension());
//			docEntity.setExtension(extension);
//			if (!StringUtils.isEmpty(doc.getFileid())){
//				docEntity.setFileid(Integer.parseInt(doc.getFileid()));
//			}

			docEntityList.add(docEntity);
		}

		return docEntityList;
	}


}
