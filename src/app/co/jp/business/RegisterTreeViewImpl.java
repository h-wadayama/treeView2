package app.co.jp.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.co.jp.business.IF.RegisterTreeViewIF;
import app.co.jp.model.DocModel;
import app.co.jp.model.FileModel;
import app.co.jp.service.IdService;
import app.co.jp.service.IF.DocServiceIF;
import app.co.jp.service.IF.DocumentServiceIF;
import app.co.jp.service.IF.DocumentUploadServiceIF;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.Const;

@Component
public class RegisterTreeViewImpl implements RegisterTreeViewIF{

	@Autowired
	DocServiceIF docService;

	@Autowired
	DocumentServiceIF documentService;

	@Autowired
	DocumentUploadServiceIF documentUploadService;

	@Autowired
	CommonUtil commonUtil;

	@Autowired
	IdService idService;


	public String registerInTree(MultipartFile fileUpload,String jsonData,String parentId,String icon) {

		String rtnJson = "";
		DocModel docModelUpload = new DocModel();
		List<DocModel> docModelList = new ArrayList<DocModel>();
		List<DocModel> docModelTree = new ArrayList<DocModel>();
		parentId = parentId.replace(Const.JTREE_PREFIX, "");

		ObjectMapper mapper = new ObjectMapper();

		try {
			DocModel docModel = new DocModel();
			List<DocModel> docModelListFromJson = new ArrayList<DocModel>();
			docModelListFromJson=  mapper.readValue(jsonData, new TypeReference<List<DocModel>>(){});
			docModel = docModelListFromJson.get(0);
			docModelList = docService.setChildDocModel("", docModel, docModelList);

			FileModel fileModel = new FileModel();

			//String newId = docService.getNewId();


			if(!fileUpload.getOriginalFilename().isEmpty() && !parentId.isEmpty()){
				//アップロードを実行したとき
				String newId = String.valueOf(commonUtil.getMaxId(docModelList) + 1);
				//String newFileId = String.valueOf(docService.getMaxFileId() + 1);
				String newFileId = String.valueOf(idService.getMaxId()+1);

				String originalFileName =fileUpload.getOriginalFilename();
				String extension=originalFileName.substring(originalFileName.lastIndexOf("."));

				if (icon.equals(Const.ICON_FILE)){
					//ファイルを選択してアップロードを実行したときは、親のIdを取得する。
					parentId = docService.getParentId(parentId);
				}
				docModelUpload = documentUploadService.upload(fileUpload,newId,parentId,newFileId);

				//Uploadしたファイル情報をdocModeに追加
				docModelList.add(docModelUpload);

				//ファイル情報をモデルに追加
				fileModel.setId(newId);
				fileModel.setFileid(newFileId);
				fileModel.setExtension(extension);

				//Fileの追加情報を登録する。
				docService.insFileInfo(fileModel);
			}

			/*
			 * DOC_TBL全件削除
			 */
			docService.dellDocTableALL();

			//DocTbleに登録する。
			docService.insDocTbl(docModelList);

			//階層再取得
			docModelTree.addAll(docService.getTree());

			//ModelをJson形式に置き換える。
			rtnJson = mapper.writeValueAsString(docModelTree);


			System.out.print("OK");;

		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return rtnJson;

	}





}
