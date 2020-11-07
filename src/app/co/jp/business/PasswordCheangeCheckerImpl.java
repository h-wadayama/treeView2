/*
 * 処理名     ：パスワード変更チェック用クラス
 * クラス名 ：app.co.jp.business.PasswordCheangeCheckerImpl
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.PasswordCheangeCheckerIF;
import app.co.jp.model.PassWordChangeModel;

@Component
public class PasswordCheangeCheckerImpl implements PasswordCheangeCheckerIF  {

    private static final Logger logger = LoggerFactory
            .getLogger(PasswordCheangeCheckerImpl.class);


	public Object[] passwordChangeCheck(PassWordChangeModel passWordChangeModel){

		Object[] rtnObject = new Object[2];
		ResultMessages messages = ResultMessages.error();
		boolean errFlg = false;

		if(!passWordChangeModel.getNowPassword().equals(passWordChangeModel.getOldPassword())){
			//旧パスワードとログイン時のパスワードが同じときエラー
			errFlg = true;
			messages.add("e.te.fw.8009");
			logger.error("e.te.fw.8009");
		}

		if(passWordChangeModel.getLoginId().equals(passWordChangeModel.getNewPassword())){
			//ログインIDと新パスワードが同じときエラー
			errFlg = true;
			messages.add("e.te.fw.8006");
			logger.error("e.te.fw.8006");
		}

		if(passWordChangeModel.getOldPassword().equals(passWordChangeModel.getNewPassword())){
			//旧パスワードと新パスワードが同じときエラー
			errFlg = true;
			messages.add("e.te.fw.8007");
			logger.error("e.te.fw.8007");
		}

		if(!passWordChangeModel.getNewPassword().equals(passWordChangeModel.getNewPasswordSeccond())){
			//新パスワードと新パスワード確認用が異なるときエラー
			errFlg = true;
			messages.add("e.te.fw.8008");
			logger.error("e.te.fw.8008");
		}

		rtnObject[0]= errFlg;
		rtnObject[1]= messages;

		return rtnObject;

	}


}
