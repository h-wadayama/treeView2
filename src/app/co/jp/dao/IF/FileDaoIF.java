package app.co.jp.dao.IF;

import java.sql.SQLException;

import javax.naming.NamingException;

import app.co.jp.entity.FileEntity;
import app.co.jp.model.FileModel;

public interface FileDaoIF {

	FileEntity getFileInfo(int id) throws NamingException, SQLException;
	void insFileInfo(FileEntity fileEntity) throws NamingException, SQLException;
	FileEntity setFileEntity(FileModel fileModel);
	int getMaxFileId() throws NamingException, SQLException;

}
