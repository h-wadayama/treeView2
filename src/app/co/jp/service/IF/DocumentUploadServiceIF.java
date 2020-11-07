package app.co.jp.service.IF;

import org.springframework.web.multipart.MultipartFile;

import app.co.jp.model.DocModel;

public interface DocumentUploadServiceIF {
	DocModel upload(MultipartFile fileUpload,String id,String parentId,String fileId);
}
