/*
 * 処理名     ：単項目チェックメッセージ取得
 * クラス名   ：CommonCheckerImpl
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.business;

import static app.co.jp.util.Const.*;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.CommonCheckerIF;

@Component
public class CommonCheckerImpl implements CommonCheckerIF{

    private static final Logger logger = LoggerFactory
            .getLogger(CommonCheckerImpl.class);

    @Autowired
    MessageSource messageSource;

	/*
	 * 処理名     ：単項目チェック
	 * メソッド名 ：loginItemCheck
	 * 引数       ：第一引数　BindingResult result
	 * 説明       ：MODELに付与したアノテーション（@）で入力チェックを行う
	 * 戻り値     ：Object[]
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	public Object[] itemCheck(BindingResult result){

		logger.warn("CommonCheckerImpl#itemCheck(BindingResult result) Start");
		Object[] rtnObj = new Object[3];
		ResultMessages messages = ResultMessages.error();

		rtnObj[0] = false;

		if (result.hasErrors()){

			for (FieldError fieldError : result.getFieldErrors()) {

				String sName = messageSource.getMessage(fieldError.getField(),  null,Locale.JAPAN);
				String sValue = (String) fieldError.getRejectedValue();


				switch (fieldError.getCode()){
				case SIZE:
				case BYTESIZE:
					if(!StringUtils.isEmpty(sValue)){
						rtnObj[0] = true;
						//サイズチェックの時は、空文字はチェックしない
						messages.add(fieldError.getDefaultMessage(),sName,fieldError.getArguments()[2]);
						logger.error(fieldError.getDefaultMessage(),sName,fieldError.getArguments()[2]);
					}
					break;
				case PATTERN:
					if(!StringUtils.isEmpty(sValue)){
						rtnObj[0] = true;
						messages.add(fieldError.getDefaultMessage(),sName);
						logger.error(fieldError.getDefaultMessage(),sName);
					}
					break;
				default:
					rtnObj[0] = true;
					messages.add(fieldError.getDefaultMessage(),sName);
					logger.error(fieldError.getDefaultMessage(),sName);
				}


	/*
				if (fieldError.getCode().equals(SIZE) || fieldError.getCode().equals(BYTESIZE)){
					if(!StringUtils.isEmpty(sValue)){
						//サイズチェックの時は、空文字はチェックしない
						messages.add(fieldError.getDefaultMessage(),sName,fieldError.getArguments()[2]);
						logger.error(fieldError.getDefaultMessage(),sName,fieldError.getArguments()[2]);
					}

				}else{
					messages.add(fieldError.getDefaultMessage(),sName);
					logger.error(fieldError.getDefaultMessage(),sName);
				}

	*/
				}

		}

		logger.warn("CommonCheckerImpl#itemCheck(BindingResult result) End");
		rtnObj[2] = messages;
		return rtnObj;

	}



}
