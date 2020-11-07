/*
 * 処理名     ：USER_TBL検索用クラス
 * クラス名 ：app.co.jp.dao.UserFindDaoImpl
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.UserFindDaoIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;

@Component
public class UserFindDaoImpl implements UserFindDaoIF  {

    private static final Logger logger = LoggerFactory
            .getLogger(UserFindDaoImpl.class);

	/*
	 * 処理名     ：USER_TBL全件取得
	 * メソッド名 ：getUserListAll()
	 * 引数       ：なし
	 * 戻り値     ：List<UserEntity>
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public List<UserEntity> getUserListAll() throws NamingException, SQLException {

		logger.warn("UserFindDaoImpl#getUserListAll() Start");
		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		List<UserEntity> userLIst = new ArrayList<UserEntity>();

		/* userTblから取得 */

			String sql = "SELECT * FROM user_tbl ORDER BY USERID";
			con = DBcon.getDataSourceConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			logger.warn(sql);

			 while(rs.next()){

				 UserEntity user = new UserEntity();
				 user.setUserid(rs.getString("USERID"));
				 user.setName(rs.getString("NAME"));
				 user.setPassword(rs.getString("PASSWORD"));
				 user.setMailaddress(rs.getString("MAILADDRESS"));
				 user.setZipcode(rs.getString("ZIPCODE"));
				 user.setAddress1(rs.getString("ADDRESS1"));
				 user.setAddress2(rs.getString("ADDRESS2"));
				 user.setAddress3(rs.getString("ADDRESS3"));
				 user.setTelno(rs.getString("TELNO"));
				 user.setAuthority(rs.getString("AUTHORITY"));
				 userLIst.add(user);
			 }

			 rs.close();
			 ps.close();
			 con.close();

			 logger.warn("UserFindDaoImpl#getUserListAll() End");
		return  userLIst;

	}

	/*
	 * 処理名     ：USER_TBL1件取得
	 * メソッド名 ：getUser()
	 * 引数       ：第一引数：int id
	 * 戻り値     ：UserEntity
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public UserEntity getUser (String userId) throws NamingException, SQLException {

		logger.warn("UserFindDaoImpl#getUser (String userId) Start");

		UserEntity user = new UserEntity();

		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		String sql = "SELECT * FROM user_tbl Where userid = '" + userId +"'" ;

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		logger.warn(sql);

		if (rs.next()){
			user.setUserid(rs.getString("USERID"));
			user.setName(rs.getString("NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setMailaddress(rs.getString("MAILADDRESS"));
			user.setZipcode(rs.getString("ZIPCODE"));
			user.setAddress1(rs.getString("ADDRESS1"));
			user.setAddress2(rs.getString("ADDRESS2"));
			user.setAddress3(rs.getString("ADDRESS3"));
			user.setTelno(rs.getString("TELNO"));
			user.setAuthority(rs.getString("AUTHORITY"));
		}
		rs.close();
		ps.close();
		con.close();

		logger.warn("UserFindDaoImpl#getUser (String userId) End");
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
	public Object[] getUser (LoginFormModel loginFormModel) throws NamingException, SQLException {

		logger.warn("UserFindDaoImpl#getUser (LoginFormModel loginFormModel) Start");

		Object[] object = new Object[2];
		UserEntity user = new UserEntity();
		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String login = "0" ;

		String sql = "SELECT * FROM user_tbl Where userid = '" + loginFormModel.getLoginId() + "'";
		sql = sql + "and password = '" + loginFormModel.getPassword() + "'";
		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		logger.warn("sql");

		if (rs.next()){
			login = "1";
			user.setUserid(rs.getString("USERID"));
			user.setName(rs.getString("NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setMailaddress(rs.getString("MAILADDRESS"));
			user.setZipcode(rs.getString("ZIPCODE"));
			user.setAddress1(rs.getString("ADDRESS1"));
			user.setAddress2(rs.getString("ADDRESS2"));
			user.setAddress3(rs.getString("ADDRESS3"));
			user.setTelno(rs.getString("TELNO"));
			user.setAuthority(rs.getString("AUTHORITY"));
		}

		rs.close();
		ps.close();
		con.close();

		object[0] = user;
		object[1]= login ;

		logger.warn("UserFindDaoImpl#getUser (LoginFormModel loginFormModel) End");
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

		logger.warn("UserFindDaoImpl#getUser(String userCode,String password) Start");
		Object[] object = new Object[2];
		UserEntity user = new UserEntity();

		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		String sql = "SELECT * FROM user_tbl Where user_code = '" + userCode  + "' ";
		sql = sql + " and password = '" + password + "'";

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		logger.warn("sql");

		String login = "0";
		if (rs.next()){
			login = "1";
			user.setUserid(rs.getString("USERID"));
			user.setName(rs.getString("NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setMailaddress(rs.getString("MAILADDRESS"));
			user.setZipcode(rs.getString("ZIPCODE"));
			user.setAddress1(rs.getString("ADDRESS1"));
			user.setAddress2(rs.getString("ADDRESS2"));
			user.setAddress3(rs.getString("ADDRESS3"));
			user.setTelno(rs.getString("TELNO"));
			user.setAuthority(rs.getString("AUTHORITY"));
		}

		object[0] = user;
		object[1] = login;

		rs.close();
		ps.close();
		con.close();

		logger.warn("UserFindDaoImpl#getUser(String userCode,String password) End");
		return object;
	}
}
