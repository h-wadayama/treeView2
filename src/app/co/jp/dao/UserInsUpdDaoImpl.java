/*
 * 処理名     ：USER_TBL更新用クラス
 * クラス名 ：app.co.jp.dao.UserInsUpdDaoImpl
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import app.co.jp.dao.IF.UserInsUpdDaoIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.DocModel;
import app.co.jp.util.CommonUtil;
import app.co.jp.util.DBConnect;

@Component
public class UserInsUpdDaoImpl implements UserInsUpdDaoIF {

    private static final Logger logger = LoggerFactory
            .getLogger(UserInsUpdDaoImpl.class);

	/* User Tbl UPDATE */
	public void updUserTbl(UserEntity userEntity) throws NamingException, SQLException {

		logger.warn("UserInsUpdDaoImpl#updUserTbl(UserEntity userEntity) Start");

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sdf.format(cl.getTime());

		String sql = "";
		sql = "UPDATE user_tbl SET " ;
		sql = sql + "name = ? ";
		sql = sql + ",userid = ? ";
		sql = sql + ",mailaddress = ? ";
		sql = sql + ",zipcode = ? ";
		sql = sql + ",address1 = ?" ;
		sql = sql + ",address2 = ? " ;
		sql = sql + ",address3 = ? " ;
		sql = sql + ",telno = ? " ;
		sql = sql + ",authority = ? " ;
		sql = sql + ",upddate = ? " ;
		sql = sql + "Where userid = ? " ;

		ps = con.prepareStatement(sql);
		ps.setString(1, userEntity.getName());
		ps.setString(2, userEntity.getUserid());
		ps.setString(3, userEntity.getMailaddress());
		ps.setString(4, userEntity.getZipcode());
		ps.setString(5, userEntity.getAddress1());
		ps.setString(6, userEntity.getAddress2());
		ps.setString(7, userEntity.getAddress3());
		ps.setString(8, userEntity.getTelno());
		ps.setString(9, userEntity.getAuthority());
		ps.setString(10, sysDate);
		ps.setString(11, userEntity.getUserid());

		ps.executeUpdate();
		con.commit();

		logger.warn(sql);

		ps.close();
		con.close();

		logger.warn("UserInsUpdDaoImpl#updUserTbl(UserEntity userEntity) End");

	}

	/* User Tbl Insert*/
	public void insUserTbl(UserEntity userEntity) throws NamingException, SQLException {

		logger.warn("UserInsUpdDaoImpl#insUserTbl(UserEntity userEntity) Start");

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = sdf.format(cl.getTime());

		CommonUtil ommonUtil = new CommonUtil();
		DBConnect DBcon = ommonUtil.getContext();
		Connection con = null;
		con = DBcon.getDataSourceConnection();

		PreparedStatement ps = null;

		String sql = "";
		sql = "INSERT INTO user_tbl  ";
		sql = sql + "( " ;
		sql = sql + "userid ," ;
		sql = sql + "name," ;
		sql = sql + "password," ;
		sql = sql + "mailaddress, " ;
		sql = sql + "zipcode, " ;
		sql = sql + "address1, " ;
		sql = sql + "address2, " ;
		sql = sql + "address3, " ;
		sql = sql + "telno, " ;
		sql = sql + "authority, " ;
		sql = sql + "insdate, " ;
		sql = sql + "upddate " ;
		sql = sql + " ) VALUES (";
		sql = sql + "? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? " ;
		sql = sql + ",? ";
		sql = sql + ")";

		ps = con.prepareStatement(sql);
		ps.setString(1, userEntity.getUserid());
		ps.setString(2, userEntity.getName());
		ps.setString(3, userEntity.getUserid());
		ps.setString(4, userEntity.getMailaddress());
		ps.setString(5, userEntity.getZipcode());
		ps.setString(6, userEntity.getAddress1());
		ps.setString(7, userEntity.getAddress2());
		ps.setString(8, userEntity.getAddress3());
		ps.setString(9, userEntity.getTelno());
		ps.setString(10, userEntity.getAuthority());
		ps.setString(11, sysDate);
		ps.setString(12, sysDate);
		logger.warn(sql);

		ps.executeUpdate();
		con.commit();

		ps.close();
		con.close();

		logger.warn("UserInsUpdDaoImpl#insUserTbl(UserEntity userEntity) End");

	}

	/*
	 * User Detete
	 *
	 *
	 */
	public int delUserTbl(String userId) throws NamingException, SQLException{

		logger.warn("UserInsUpdDaoImpl#delUserTbl(String userId) Start");

		String sql = "";
		sql = "DELETE FROM user_tbl  ";
		sql = sql + "WHERE userid = '" + userId + "'";
		logger.warn(sql);

		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;
		int rs = 0 ;
		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeUpdate();

		con.commit();

		ps.close();
		con.close();

		logger.warn("UserInsUpdDaoImpl#delUserTbl(String userId) End");

		return rs;

	}


	/*
	 * PassWord変更
	 * */
	public void updPasssword(String userId, String password) throws NamingException, SQLException {

		logger.warn("UserInsUpdDaoImpl#updPasssword(String userId, String password) Start");

		String sql = "";
		sql = "UPDATE user_tbl SET password = '" + password + "' " ;
		sql = sql + "Where userid = '" + userId + "' ";
		logger.warn(sql);

		CommonUtil ｃommonUtil = new CommonUtil();
		DBConnect DBcon = ｃommonUtil.getContext();

		Connection con = null;

		PreparedStatement ps = null;

		con = DBcon.getDataSourceConnection();
		ps = con.prepareStatement(sql);
		int rs = ps.executeUpdate();

		con.commit();

		ps.close();
		con.close();

		logger.warn("UserInsUpdDaoImpl#updPasssword(String userId, String password) End");


	}

	@Override
	public List<DocModel> getHierarchy(int lv) throws NamingException, SQLException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


}
