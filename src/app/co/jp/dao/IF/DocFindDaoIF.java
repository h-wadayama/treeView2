package app.co.jp.dao.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.entity.DocEntity;


public interface DocFindDaoIF {

	List<DocEntity> getPearent() throws NamingException, SQLException;
	List<DocEntity> getChild(String pearentId) throws NamingException, SQLException;
	List<DocEntity> getHierarchy(int lv) throws NamingException, SQLException;
	List<DocEntity> getHierarchy(int lv,String pearentId) throws NamingException, SQLException;
	String getNewId() throws NamingException, SQLException;
	String getParentId(String id) throws NamingException, SQLException;
	DocEntity getDocument(int id) throws NamingException, SQLException;
	int getMaxFileId() throws NamingException, SQLException;
}
