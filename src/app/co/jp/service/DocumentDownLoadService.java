package app.co.jp.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import app.co.jp.model.DocModel;
import app.co.jp.model.FileModel;
import app.co.jp.service.IF.DocumentDownLoadServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;

@Service
public class DocumentDownLoadService implements DocumentDownLoadServiceIF{

	@Autowired
	DocService docService;

	@Autowired
	PropertiesUtil propertiesUtil;

	public void downLoad(String id,HttpServletResponse response) throws NamingException, SQLException, IOException{

		DocModel docModel = new DocModel();
		FileModel fileModel = new FileModel();
		String repId = id.replace(Const.JTREE_PREFIX, "");

		fileModel = docService.getFileInfo(Integer.parseInt(repId));
		String extension =fileModel.getExtension();
		String fileId = fileModel.getFileid();

		docModel = docService.getDocument(repId);
		String downloadFileName = fileId + extension;

		Resource file = new FileSystemResource(propertiesUtil.getText(Const.UPLOAD_FILE_OUT) + "/" +  downloadFileName);
		response.setContentType("text/plain");
		response.setContentLength((int)FileUtils.sizeOf(file.getFile()));



		response.setHeader("Content-Disposition","attachment;filename=\"" +
		URLEncoder.encode(docModel.getText(),Const.CHA_UTF8) + "\"");


		FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());

	}




}
