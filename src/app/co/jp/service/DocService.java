package app.co.jp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.co.jp.dao.FileDao;
import app.co.jp.dao.IF.DocFindDaoIF;
import app.co.jp.dao.IF.DocInsUpdDaoIF;
import app.co.jp.entity.DocEntity;
import app.co.jp.entity.FileEntity;
import app.co.jp.model.DocModel;
import app.co.jp.model.FileModel;
import app.co.jp.service.IF.DocServiceIF;
import app.co.jp.util.Const;


@Service
public class DocService implements DocServiceIF{

	@Autowired
	DocFindDaoIF docFindDao;

	@Autowired
	DocInsUpdDaoIF docInsUpdDao;

	@Autowired
	FileDao fileDao;

	/**
	 * 処理名     ：親情報　取得
	 * メソッド名 ：getPearent()
	 * 引数       ：なし
	 * 戻り値     ：List<DocEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocModel> getPearent() throws NamingException, SQLException {

		List<DocEntity> docList = new ArrayList<DocEntity>();
		docList = docFindDao.getPearent();

		List<DocModel> DocModelList = new ArrayList<DocModel>();

		for (int i = 0; i < docList.size(); i++) {
			DocModel  docModel = new DocModel();
			String jtreeId = Const.JTREE_PREFIX + String.valueOf(docList.get(i).getId());

			//docModel.setId(String.valueOf(docList.get(i).getId()));
			docModel.setId(jtreeId);

			switch (docList.get(i).getObjectTipe()){
			case "1":
				docModel.setIcon(Const.ICON_FOLDER );
				break;
			case "2":
				docModel.setIcon(Const.ICON_FILE);
				break;
			default:
				docModel.setIcon(Const.ICON_FOLDER );
			}

//			State state = new State();
//			state.setDisabled(true);
//			state.setLoaded(false);
//			state.setOpened(false);
//			state.setSelected(false);
//			docModel.setState(state);

			docModel.setText(docList.get(i).getObjectName());
//			docModel.setObjLevel(docList.get(i).getObjLevel());
			DocModelList.add(docModel);

			//子階層取得
			//DocModelList.get(i).setChildHierarchy(this.getChild(docModel.getParentId()));


		}

		return DocModelList;

	}

	/**
	 * 処理名     ：子情報　取得
	 * メソッド名 ：getChild()
	 * 引数       ：なし
	 * 戻り値     ：List<DocModelList>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocModel> getChild(String pearenrId) throws NamingException, SQLException {

		List<DocEntity> docList = new ArrayList<DocEntity>();
		List<DocModel> DocModelList = new ArrayList<DocModel>();
		docList = docFindDao.getChild(pearenrId);

		for (int i = 0; i < docList.size(); i++) {
			DocModel  docModelChild = new DocModel();
			String jtreeId = Const.JTREE_PREFIX + String.valueOf(docList.get(i).getId());
			String jtreeParentId = "";
			if (!docList.get(i).getParentId().isEmpty()){
				jtreeParentId = Const.JTREE_PREFIX + docList.get(i).getParentId();
			}
			//docModelChild.setId(String.valueOf(docList.get(i).getId()));
			docModelChild.setId (jtreeId);


			switch (docList.get(i).getObjectTipe()){
			case "1":
				docModelChild.setIcon(Const.ICON_FOLDER );
				break;
			case "2":
				docModelChild.setIcon(Const.ICON_FILE);
				break;
			default:
				docModelChild.setIcon(Const.ICON_FOLDER );
			}

/*
			State state = new State();
			state.setDisabled(true);
			state.setLoaded(false);
			state.setOpened(false);
			state.setSelected(false);
			docModelChild.setState(state);
*/
			docModelChild.setText(docList.get(i).getObjectName());
//			docModelChild.setObjLevel(docList.get(i).getObjLevel());
//			docModelChild.setParentId(docList.get(i).getParentId());
			docModelChild.setParentId(jtreeParentId);
			DocModelList.add(docModelChild);
		}

		return DocModelList;


	}

	/**
	 * 処理名     ：階層取得
	 * メソッド名 ：getHierarchy()
	 * 引数       ：Level
	 * 戻り値     ：List<DocModel>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocModel> getChild(int lv) throws NamingException, SQLException {

		List<DocEntity> docList = new ArrayList<DocEntity>();
		List<DocModel> DocModelList = new ArrayList<DocModel>();
		docList = docFindDao.getHierarchy(lv);

		for (int i = 0; i < docList.size(); i++) {
			DocModel  docModelChild = new DocModel();
			docModelChild.setId(String.valueOf(docList.get(i).getId()));

			switch (docList.get(i).getObjectTipe()){
			case "1":
				docModelChild.setIcon(Const.ICON_FOLDER );
				break;
			case "2":
				docModelChild.setIcon(Const.ICON_FILE);
				break;
			default:
				docModelChild.setIcon(Const.ICON_FOLDER );
			}

			docModelChild.setText(docList.get(i).getObjectName());
//			docModelChild.setObjLevel(docList.get(i).getObjLevel());
			docModelChild.setParentId(docList.get(i).getParentId());
			DocModelList.add(docModelChild);
		}

		return DocModelList;

	}

	/**
	 * 処理名     ：階層取得
	 * メソッド名 ：getHierarchy()
	 * 引数       ：Level,pearentId
	 * 戻り値     ：List<DocModel>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<DocModel> getChild(int lv,String pearenrId ) throws NamingException, SQLException {

		List<DocEntity> docList = new ArrayList<DocEntity>();
		List<DocModel> DocModelList = new ArrayList<DocModel>();
		docList = docFindDao.getHierarchy(lv,pearenrId);

		for (int i = 0; i < docList.size(); i++) {
			DocModel  docModelChild = new DocModel();
			docModelChild.setId(String.valueOf(docList.get(i).getId()));

			switch (docList.get(i).getObjectTipe()){
			case "1":
				docModelChild.setIcon(Const.ICON_FOLDER );
				break;
			case "2":
				docModelChild.setIcon(Const.ICON_FILE);
				break;
			default:
				docModelChild.setIcon(Const.ICON_FOLDER );
			}

			docModelChild.setText(docList.get(i).getObjectName());
//			docModelChild.setObjLevel(docList.get(i).getObjLevel());
			docModelChild.setParentId(docList.get(i).getParentId());
			DocModelList.add(docModelChild);
		}

		return DocModelList;

	}


	/**
	 * 処理名     ：階層取得
	 * メソッド名 ：getTree()
	 * 引数       ：なし
	 * 戻り値     ：List<DocModelList>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-04-15
	 */
	public List<DocModel> getTree() throws NamingException, SQLException {

		List<DocModel> doc = new ArrayList<DocModel>();
		List<DocModel> docModelList = new ArrayList<DocModel>();
		docModelList = this.getPearent();

		for(int i=0 ; i < docModelList.size(); i++ ){

			//子階層取得
			DocModel docModel = setHierarchy(docModelList.get(i),docModelList.get(i));
			doc.add(docModel);

		}

		return doc;

	}

	/**
	 * 処理名     ：再帰的に階層取得
	 * メソッド名 ：setHierarchy
	 * 引数       ：DocModel docModel,DocModel nodeChildren
	 * 戻り値     ：DocModel
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-04-15
	 */
	public DocModel setHierarchy (DocModel docModel,DocModel nodeChildren)
			throws NamingException, SQLException{

		List<DocModel> docList = this.getChild(nodeChildren.getId().replace(Const.JTREE_PREFIX, ""));

		if (docList.size()>0){
			nodeChildren.setChildren(docList);
		}

		for (DocModel nextNode : docList) {
			this.setHierarchy (docModel,nextNode);
		}
		return docModel;

	}

	/**
	 * 処理名     ：階層形式からリスト形式に置き換え
	 * メソッド名 ：setChildDocModel
	 * 引数       ：String parentId,DocModel docModel,List<DocModel> docModelList
	 * 戻り値     ：List<DocModel>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-05-20
	 */
	public List<DocModel> setChildDocModel(String parentId,
			DocModel docModel,
			List<DocModel> docModelList){

		List<DocModel> chileList = new ArrayList<DocModel>();

		docModelList.add(this.setDocModelList(docModel, parentId));
		DocModel nextNode = new DocModel();

		chileList = docModel.getChildren();
		parentId = docModel.getId();

		for(DocModel doc:chileList){
			nextNode = doc;
			this.setChildDocModel(parentId, nextNode, docModelList);

		}

		return docModelList;

	}

	/**
	 * 処理名     ：DocModelの値を別のDocModelにセット
	 * メソッド名 ：setDocModelList
	 * 引数       ：DocModel docModel,String parentId
	 * 戻り値     ：DocModel
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-05-20
	 */
	public DocModel setDocModelList(DocModel docModel,String parentId){

		DocModel docModelNew = new DocModel();
		String id = docModel.getId().replace(Const.JTREE_PREFIX, "");
		String parentIdNew = parentId.replace(Const.JTREE_PREFIX, "");

		docModelNew.setId(id);
		docModelNew.setText(docModel.getText());

		if (docModel.getIcon()== null){
			docModel.setIcon(Const.ICON_FILE);
		}

		switch (docModel.getIcon()){
		case Const.ICON_FOLDER :
			docModelNew.setIcon(Const.ICON_FOLDER_KBN);
			break;
		case Const.ICON_FILE:
			docModelNew.setIcon(Const.ICON_FILE_KBN);
			break;
		default:
			docModelNew.setIcon(Const.ICON_FILE_KBN);
		}
		docModelNew.setParentId(parentIdNew);
		docModelNew.setExtension(docModel.getExtension());

		return docModelNew;
	}


	public void dellDocTableALL() throws NamingException, SQLException{

		docInsUpdDao.dellDocTableALL();
	}

	public void insDocTbl(List<DocModel> docModelList) throws NamingException, SQLException{

		List<DocEntity> docEntity = new ArrayList<DocEntity>();

		/*
		 * DOC_TBL INSERT
		 */
		docEntity = docInsUpdDao.setDocEntity(docModelList);
		docInsUpdDao.inslDocTableALL(docEntity);

	}

	public String getNewId() throws NamingException, SQLException{

		return docFindDao.getNewId();
	}

	public String getParentId(String id) throws NamingException, SQLException{

		return docFindDao.getParentId(id);

	}

	public DocModel getDocument(String id) throws NamingException, SQLException{

		DocEntity docEntity = new DocEntity();
		DocModel docModel = new DocModel();

		docEntity= docFindDao.getDocument(Integer.parseInt(id));

		docModel.setId(String.valueOf(docEntity.getId()));
		docModel.setText(docEntity.getObjectName());
		docModel.setIcon(docEntity.getObjectTipe());
		docModel.setParentId(docEntity.getParentId());
//		docModel.setExtension(docEntity.getExtension());

		return docModel;

	}

	public int getMaxFileId() throws NamingException, SQLException{

		int id = 0;
		//id = docFindDao.getMaxFileId();
		id = fileDao.getMaxFileId();

		return id;
	}

	public void insFileInfo(FileModel fileModel) throws NamingException, SQLException{

		FileEntity fileEntity = new FileEntity();
		fileEntity = fileDao.setFileEntity(fileModel);
		fileDao.insFileInfo(fileEntity);

	}

	public FileModel getFileInfo(int id) throws NamingException, SQLException {

		FileEntity fileEntity = new FileEntity();
		FileModel fileModel = new FileModel();

		fileEntity = fileDao.getFileInfo(id);
		fileModel.setId(String.valueOf(fileEntity.getId()));
		fileModel.setFileid(String.valueOf(fileEntity.getFileid()));
		fileModel.setExtension(fileEntity.getExtension());

		return fileModel;
	}

}
