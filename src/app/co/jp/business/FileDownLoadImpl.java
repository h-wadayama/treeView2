package app.co.jp.business;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import app.co.jp.business.IF.FileDownLoadIF;
import app.co.jp.service.IF.DocServiceIF;

@Component
public class FileDownLoadImpl implements FileDownLoadIF{


	DocServiceIF docService;

	public void download (String id) throws NamingException, SQLException{





	}

}
