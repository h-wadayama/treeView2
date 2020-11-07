package app.co.jp.service;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.co.jp.dao.IF.IdDaoIF;
import app.co.jp.entity.IdEntity;
import app.co.jp.service.IF.IdServiceIF;

@Service
public class IdService implements IdServiceIF{

	@Autowired
	IdDaoIF idDao;

	public int getMaxId() throws SQLException, NamingException{

		int id = 0;
		IdEntity idEntity  = new IdEntity();

		id = idDao.getMaxId();

		if (id == 0){
			//無いときは、新規で作成
			this.insId(1);

		}else{
			//id +1 で更新
			this.updId(id+1);
		}

		return id;

	}

	public void insId(int id) throws NamingException, SQLException{

		idDao.insId(id);

	}

	public void updId(int id) throws NamingException, SQLException{

		idDao.updId(id);

	}

}
