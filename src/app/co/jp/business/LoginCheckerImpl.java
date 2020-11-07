/*
 * 処理名     ：ログインチェック
 * クラス名   ：LoginCheckerImpl
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.business;

import java.sql.SQLException;
import java.util.Locale;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.LoginCheckerIF;
import app.co.jp.dao.IF.UserFindDaoIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;
import app.co.jp.service.IF.UserServiceIF;

@Component
public class LoginCheckerImpl implements LoginCheckerIF {

    private static final Logger logger = LoggerFactory
            .getLogger(LoginCheckerImpl.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserFindDaoIF userFinfDao;

    @Autowired
    UserServiceIF userService;


	/*
	 * 処理名     ：ログインチェック
	 * メソッド名 ：loginCheck
	 * 引数       ：第一引数　LoginFormModel loginForm
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public Object[] loginCheck(LoginFormModel loginForm) throws NamingException, SQLException{

		logger.warn("LoginCheckerImpl#loginCheck(LoginFormModel loginForm) Start");

		Object[] rtnObj = new Object[3];
		rtnObj[0] = false;
		ResultMessages messages = ResultMessages.error();

		Object[] obj = userService.getUser(loginForm);
		//Object[] obj = userFinfDao.getUser(loginForm);
		String login = (String) obj[1];
		UserEntity userEntity = (UserEntity) obj[0];
		rtnObj[1] = userEntity;
		if (login != "1"){
			rtnObj[0] = true;
			//Springの場合のメッセージリソース取得
			//String message1 = messageSource.getMessage("e.te.fw.8001",  null,Locale.JAPAN);
			//TERASOLUNAでは↓これでOK
			messages.add("e.te.fw.8001");
			logger.error("e.te.fw.8001");
		}

		rtnObj[2] = messages;

		logger.warn("LoginCheckerImpl#loginCheck(LoginFormModel loginForm) End");
		return rtnObj;

	}

	/*
	 * 処理名     ：ログイン項目チェック
	 * メソッド名 ：loginItemCheck
	 * 引数       ：第一引数　BindingResult result
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public Object[] loginItemCheck(BindingResult result){

		logger.warn("LoginCheckerImpl#loginItemCheck(BindingResult result) Start");
		Object[] rtnObj = new Object[3];
		ResultMessages messages = ResultMessages.error();

		rtnObj[0] = false;
		if (result.hasErrors()){
			rtnObj[0] = true;

			for (FieldError fieldError : result.getFieldErrors()) {

				String sName = messageSource.getMessage(fieldError.getField(),  null,Locale.JAPAN);
				messages.add(fieldError.getDefaultMessage(),sName);
				logger.error(fieldError.getDefaultMessage(),sName);

			}

		}

		logger.warn("LoginCheckerImpl#loginItemCheck(BindingResult result) End");
		rtnObj[2] = messages;
		return rtnObj;

	}

}
