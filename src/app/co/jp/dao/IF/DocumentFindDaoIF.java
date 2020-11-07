package app.co.jp.dao.IF;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import app.co.jp.entity.DocEntity;

public interface DocumentFindDaoIF {

	List<DocEntity> getAllHierarchy() throws NamingException, SQLException;

}
