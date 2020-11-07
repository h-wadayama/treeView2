package app.co.jp.service.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.model.DocModel;
import app.co.jp.model.FileModel;

public interface DocServiceIF {

	List<DocModel> getPearent() throws NamingException, SQLException;
	List<DocModel> getChild(String pearenrId) throws NamingException, SQLException;
	List<DocModel> getChild(int lv) throws NamingException, SQLException;
	List<DocModel> getChild(int lv,String pearenrId ) throws NamingException, SQLException;
	List<DocModel> getTree() throws NamingException, SQLException;
	List<DocModel> setChildDocModel(String parentId,DocModel docModel,List<DocModel> docModelList);
	DocModel setDocModelList(DocModel docModel,String parentId);
	void insDocTbl(List<DocModel> docModelList) throws NamingException, SQLException;
	void dellDocTableALL() throws NamingException, SQLException;
	String getNewId() throws NamingException, SQLException;
	String getParentId(String id) throws NamingException, SQLException;
	DocModel getDocument(String id) throws NamingException, SQLException;
	int getMaxFileId() throws NamingException, SQLException;
	void insFileInfo(FileModel fileModel) throws NamingException, SQLException;
	FileModel getFileInfo(int id) throws NamingException, SQLException;
}
