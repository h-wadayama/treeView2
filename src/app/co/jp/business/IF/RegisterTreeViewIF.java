package app.co.jp.business.IF;

import org.springframework.web.multipart.MultipartFile;

public interface RegisterTreeViewIF {
	String registerInTree(MultipartFile fileUpload,String jsonData,String parentId,String icon);
}
