package app.co.jp.service.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.model.DocumentModel;

public interface DocumentServiceIF {

	List<DocumentModel> getAllHierarchy() throws NamingException, SQLException;
}
