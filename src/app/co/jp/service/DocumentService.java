package app.co.jp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.co.jp.dao.IF.DocumentFindDaoIF;
import app.co.jp.entity.DocEntity;
import app.co.jp.model.DocumentModel;
import app.co.jp.service.IF.DocumentServiceIF;

@Service
public class DocumentService implements DocumentServiceIF{

	@Autowired
	DocumentFindDaoIF documentFindDao;

	/**
	 * 処理名     ：全階層　取得
	 * メソッド名 ：getAllHierarchy()
	 * 引数       ：なし
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-04-17
	 */
	public List<DocumentModel> getAllHierarchy() throws NamingException, SQLException {

		List<DocEntity> documentList = new ArrayList<DocEntity>();
		documentList = documentFindDao.getAllHierarchy();

		List<DocumentModel> DocModelList = new ArrayList<DocumentModel>();

		for (int i = 0; i < documentList.size(); i++) {
			DocumentModel  docModel = new DocumentModel();
			docModel.setId(String.valueOf(documentList.get(i).getId()));
			docModel.setObjectTipe(documentList.get(i).getObjectTipe());
			docModel.setObjectName(documentList.get(i).getObjectName());
			docModel.setParentId(documentList.get(i).getParentId());
			DocModelList.add(docModel);
		}

		return DocModelList;

	}


}
