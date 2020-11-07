package app.co.jp.dao.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.entity.DocEntity;
import app.co.jp.model.DocModel;

public interface DocInsUpdDaoIF {

	void dellDocTableALL() throws NamingException, SQLException;
	void inslDocTableALL(List<DocEntity> docEntityList) throws NamingException, SQLException;
	List<DocEntity> setDocEntity(List<DocModel> docModelList);
}
