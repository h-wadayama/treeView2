package app.co.jp.business;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.entity.UserEntity;
import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserCheckerIF;
import app.co.jp.service.IF.UserServiceIF;

@Component
public class UserCheckerImpl implements UserCheckerIF {

    private static final Logger logger = LoggerFactory
            .getLogger(UserCheckerImpl.class);

	@Autowired
    UserServiceIF userService;

	/* USER入力チェック
	 * メソッド名 ：userCheck
	 * 引数       ：第一引数　UserModel userModel
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
    public Object[] userCheck(UserModel userModel) throws NamingException, SQLException{

		logger.warn("UserCheckerImpl#userCheck(UserModel userModel) Start");

    	Object[] rtnObj = new Object[2];
    	rtnObj[0] = false;

    	ResultMessages messages = ResultMessages.error();

    	UserEntity userEntity = userService.getUser(userModel.getUserid());

    	if (StringUtils.defaultString(userEntity.getUserid()).equals(userModel.getUserid())){
    		rtnObj[0] = true;
			messages.add("e.te.fw.8005",userModel.getUserid());
			logger.error("e.te.fw.8005");
    	}

    	rtnObj[1] = messages;

		logger.warn("UserCheckerImpl#userCheck(UserModel userModel) End");
    	return rtnObj;
    }


}
