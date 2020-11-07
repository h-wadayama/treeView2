package app.co.jp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import app.co.jp.dao.IF.UserFindDaoIF;
import app.co.jp.dao.IF.UserInsUpdDaoIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;
import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserServiceIF;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.ConstUtil;

@Service
public class UserServiceImpl implements UserServiceIF {

	@Autowired
	UserFindDaoIF userFindDao;

	@Autowired
	UserInsUpdDaoIF UserInsUpdDao;

	@Autowired
	CommonUtil commonUtil;

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);

	/*
	 * 処理名     ：USER_TBL全件取得
	 * メソッド名 ：getUserListAll()
	 * 引数       ：なし
	 * 戻り値     ：List<UserEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<UserModel> getUserListAll() throws NamingException, SQLException {

		logger.warn("UserServiceImpl#getUserListAll Start");

		List<UserModel> userModel = new ArrayList<UserModel>();
		List<UserEntity> userLIst = new ArrayList<UserEntity>();
		userLIst = userFindDao.getUserListAll();

		userModel.addAll(this.setUserListToFormService(userLIst));


		logger.warn("UserServiceImpl#getUserListAll End");
		return  userModel;


	}
	/*
	 * 処理名     ：USER_TBL1件取得
	 * メソッド名 ：getUser()
	 * 引数       ：第一引数：String userId
	 * 戻り値     ：UserEntity
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public UserEntity getUser (String userId) throws NamingException, SQLException {

		logger.warn("UserServiceImpl#getUser(int id) Start");
		UserEntity user = new UserEntity();
		user = userFindDao.getUser(userId);

		logger.warn("UserServiceImpl#getUser(int id) End");
		return user;

	}

	/*
	 * 処理名     ：USER_TBL1件取得
	 * メソッド名 ：getUser()
	 * 引数       ：第一引数：loginFormModel
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public Object[] getUser (LoginFormModel loginFormModel) throws NamingException, SQLException{

		logger.warn("UserServiceImpl#getUser (LoginFormModel loginFormModel) Start");
		Object[] object = new Object[2];
		object=userFindDao.getUser(loginFormModel);

		logger.warn("UserServiceImpl#getUser (LoginFormModel loginFormModel) End");
		return object;
	}

	/*
	 * 処理名     ：USER_TBL1件取得
	 * メソッド名 ：getUser()
	 * 引数       ：第一引数：String userCode
	 *              第二引数：String password
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public Object[] getUser(String userCode,String password) throws NamingException, SQLException{

		logger.warn("UserServiceImpl#getUser(String userCode,String password)  Start");
		Object[] object = new Object[2];
		object=userFindDao.getUser(userCode,password);

		logger.warn("UserServiceImpl#getUser(String userCode,String password)  End");
		return object;

	}

	/* Entity ⇒ Form(UserList) */
	public List<UserModel> setUserListToFormService(List<UserEntity> userLIst) {

		logger.warn("UserServiceImpl#setUserListToFormService(List<UserEntity> userLIst)  Start");

		List<UserModel> userformList = new ArrayList<UserModel>();
		for (int i = 0; i < userLIst.size(); i++) {
			UserModel userModel = new UserModel();
			userModel.setUserid(userLIst.get(i).getUserid());
			userModel.setName(userLIst.get(i).getName());
			userModel.setPassword(userLIst.get(i).getPassword());
			userModel.setMailaddress(userLIst.get(i).getMailaddress());
			userModel.setZipcode(userLIst.get(i).getZipcode());
			userModel.setAddress1(userLIst.get(i).getAddress1());
			userModel.setAddress2(userLIst.get(i).getAddress2());
			userModel.setAddress3(userLIst.get(i).getAddress3());
			userModel.setTelno(userLIst.get(i).getTelno());
			userModel.setAuthority(userLIst.get(i).getAuthority());
			userformList.add(userModel);
		}

		logger.warn("UserServiceImpl#setUserListToFormService(List<UserEntity> userLIst)  End");
		return userformList;
	}

	/* Entity ⇒ Form(User) */
	public UserModel setUserToFormService(UserEntity user) {

		logger.warn("UserServiceImpl#setUserToFormService(UserEntity user)  Start");

		UserModel userModel = new UserModel();

		userModel.setUserid(user.getUserid());
		userModel.setName(user.getName());
		userModel.setPassword(user.getPassword());
		userModel.setMailaddress(user.getMailaddress());
		userModel.setZipcode(user.getZipcode());
		userModel.setAddress1(user.getAddress1());
		userModel.setAddress2(user.getAddress2());
		userModel.setAddress3(user.getAddress3());
		userModel.setTelno(user.getTelno());
		userModel.setAuthority(user.getAuthority());

		logger.warn("UserServiceImpl#setUserToFormService(UserEntity user)  End");
		return userModel;

	}

	/* Form ⇒ Entity(User) */
	public UserEntity setUserFormToEntityService(UserModel user) {

		logger.warn("UserServiceImpl#setUserFormToEntityService(UserModel user)  Start");
		UserEntity userEntity = new UserEntity();

		userEntity.setUserid(user.getUserid());
		userEntity.setName(user.getName());
		userEntity.setMailaddress(user.getMailaddress());
		userEntity.setZipcode(user.getZipcode());
		userEntity.setAddress1(user.getAddress1());
		userEntity.setAddress2(user.getAddress2());
		userEntity.setAddress3(user.getAddress3());
		userEntity.setTelno(user.getTelno());
		userEntity.setAuthority(user.getAuthority());

		if (user.getAuthority().equals("on")){
			userEntity.setAuthority("1");
		}

		logger.warn("UserServiceImpl#setUserFormToEntityService(UserModel user)  End");
		return userEntity;
	}

	/* UserForm ⇒ UserModel */
	public UserModel setUserModel(UserModel user) {

		logger.warn("UserServiceImpl#setUserModel(UserModel user)  Start");
		UserModel userModel = new UserModel();

		userModel.setUserid(user.getUserid());
		userModel.setName(user.getName());
		userModel.setPassword(user.getPassword());
		userModel.setMailaddress(user.getMailaddress());
		userModel.setZipcode(user.getZipcode());
		userModel.setAddress1(user.getAddress1());
		userModel.setAddress2(user.getAddress2());
		userModel.setAddress3(user.getAddress3());
		userModel.setTelno(user.getTelno());

		if (user.getAuthority()==null){
			userModel.setAuthority("");
		}else{
			if (user.getAuthority().equals("on")){
				userModel.setAuthority("1");
			}
		}

		logger.warn("UserServiceImpl#setUserModel(UserModel user)  End");
		return userModel;

	}
	/*
	 * 処理名     ：USER TABLE更新
	 * メソッド名 ：updUserTbl
	 * 引数       ：第一引数　UserEntity userEntity
	 * 戻り値     ：void
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public void updUserTbl(UserEntity userEntity) throws NamingException, SQLException{

		UserInsUpdDao.updUserTbl(userEntity);

	}

	/*
	 * 処理名     ：USER TABLE INSERT
	 * メソッド名 ：insUserTbl
	 * 引数       ：void
	 * 戻り値     ：int
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public void insUserTbl(UserEntity userEntity) throws NamingException, SQLException {

		UserInsUpdDao.insUserTbl(userEntity);
	}

	/*
	 * 処理名     ：USER TABLE DELETE
	 * メソッド名 ：insUserTbl
	 * 引数       ：第一引数　int userId
	 * 戻り値     ：int
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public int delUserTbl(String userId) throws NamingException, SQLException{

		return  UserInsUpdDao.delUserTbl(userId);

	}

	/*
	 * 処理名     ：パスワード変更
	 * メソッド名 ：updPasssword
	 * 引数       ：第一引数　int userId
	 * 戻り値     ：void
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public void updPasssword(String userId, String password) throws NamingException, SQLException{

		UserInsUpdDao.updPasssword(userId,password);

	}

	/*
	 * 処理名     ：UserListページング設定
	 * メソッド名 ：userListPageingSet
	 * 引数       ：なし
	 * 戻り値     ：int
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public PagedListHolder<UserModel> userListPageingSet(int page) throws NamingException, SQLException{

		ConstUtil constUtil = commonUtil.getConstUtil();
		String pageSize = constUtil.getPageSize();;
		List<UserModel> userList = new ArrayList<UserModel>();

		/* ユーザ再取得 */
		//List<UserModel> userList = new ArrayList<UserModel>();
		//userList.addAll(this.getUserListAll());
		userList.addAll(commonUtil.removeUser(this.getUserListAll()));

		/* ページング設定 */
		PagedListHolder<UserModel> pagedListHolder = new PagedListHolder<UserModel>(userList);
		pagedListHolder.setPageSize(Integer.parseInt(pageSize));

		/* ソート定義 */
		pagedListHolder.setSort(new MutableSortDefinition("userid", true, true));

		/* ソート */
		pagedListHolder.resort();
		pagedListHolder.setPage(page);

		return pagedListHolder;

	}





}
