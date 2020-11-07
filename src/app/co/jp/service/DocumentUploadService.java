package app.co.jp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.co.jp.model.DocModel;
import app.co.jp.service.IF.DocumentUploadServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;

@Service
public class DocumentUploadService implements DocumentUploadServiceIF{

	@Autowired
	PropertiesUtil propertiesUtil;

	public DocModel upload(MultipartFile fileUpload,String id,String parentId,String fileId){

		DocModel docModel = new DocModel();

		try {

			String uploadPath =  propertiesUtil.getText(Const.UPLOAD_FILE_OUT);
			String originalFileName =fileUpload.getOriginalFilename();
			String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
			//String newFileName = id + extension;
			String newFileName = fileId + extension;

			byte[]bytes = fileUpload.getBytes();
			Path path = Paths.get(uploadPath + newFileName );
			//Path path = Paths.get(uploadPath + fileUpload.getOriginalFilename() );

			//UPLOADファイルを出力先に保存する。
			Files.write(path, bytes);

			//DocModelにUPLOADファイルの情報をセット
			docModel.setId(id);
			docModel.setParentId(parentId);
			docModel.setIcon(Const.ICON_FILE_KBN);
			docModel.setText(fileUpload.getOriginalFilename());
			docModel.setFileid(fileId);
			docModel.setExtension(extension);

		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		return docModel;

	}

}
