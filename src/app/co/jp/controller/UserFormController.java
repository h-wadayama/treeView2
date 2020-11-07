/*
 * 処理名     ：ユーザリスト用コントローラー
 * クラス名 ：app.co.jp.controller.UserListController.java
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.controller;

import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.CommonCheckerIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserCheckerIF;
import app.co.jp.service.IF.UserServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;

@Controller
public class UserFormController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserFormController.class);

	@Autowired
	UserServiceIF userService;

	@Autowired
	CommonCheckerIF commonChecker;

	@Autowired
	UserCheckerIF userChecker;

	@Autowired
	PropertiesUtil propertiesUtil;

	@SuppressWarnings("unused")
	@RequestMapping(value = "/UserList/EditUserForm" ,method = RequestMethod.GET)
	public String UserEditForm(Model model,
			@RequestParam(value = "userId" ,defaultValue = "0") String userId,
			@RequestParam(value="page",defaultValue = "0") int page,
			@RequestParam(value="mode",defaultValue = "0") int mode,
			@RequestParam(value="preScreen",defaultValue = "0") int preScreen,
			HttpServletRequest request){

		HttpSession session = request.getSession();
		/* セッション取得 */
		/* ユーザ情報取得 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");

		/* 権限取得 */
		String sesAuthorityLevel =  (String) session.getAttribute("authorityLevel");
		model.addAttribute("authorityLevel",sesAuthorityLevel);

		if (sesUserModel==null){
			return "Login";
		}

		String loginId = sesUserModel.getUserid();

		if(loginId.isEmpty()){
			return "Login";
		}

		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		//遷移元画面
		model.addAttribute("preScreen",preScreen);

		/* ログインユーザの権限をモデルに追加 */
		model.addAttribute("authority",sesUserModel.getAuthority());

		ResultMessages messages = ResultMessages.error();
		ResultMessages messagesInfo = ResultMessages.info();
		UserModel userModel = new UserModel();
		switch (mode){
		case 1:
			/* 追加 */

			model.addAttribute("page",page);
			model.addAttribute("mode","1");
			model.addAttribute("userModel",userModel);


			break;
		case 2:
			/* 編集 */
			try {
				userModel=userService.setUserToFormService(userService.getUser(userId));
			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");

			}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");
			}
			model.addAttribute("page",page);
			model.addAttribute("mode","2");
			model.addAttribute("userModel",userModel);

			break;
		case 3:

			/* 削除 */
			try {
				if (loginId.equals(userId) ){
					messages = ResultMessages.error();
					messages.add("e.te.fw.8003");
					model.addAttribute(messages);
					logger.error("e.te.fw.8003");

				}else{
					/* 1件削除 */
					int rs = userService.delUserTbl(userId);
				}

				/* ユーザ再取得 */
				PagedListHolder<UserModel> pagedListHolder = userService.userListPageingSet(0);

				messagesInfo = ResultMessages.info();
				messagesInfo.add("i.te.fw.0003");
				model.addAttribute(messagesInfo);
				model.addAttribute("UserForm",pagedListHolder);
				return "UserListView";

			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック

				e.printStackTrace();
				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");
				return "UserListView";

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				try {
					PagedListHolder<UserModel> pagedListHolder = userService.userListPageingSet(0);
					model.addAttribute("UserForm",pagedListHolder);
				} catch (NamingException | SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
					messages = ResultMessages.error();
					messages.add("e.te.fw.9001");
					model.addAttribute(messages);
					logger.error("e.te.fw.9001,e");
				}

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");


				return "UserListView";
			}

		}

		model.addAttribute("title","User");
		model.addAttribute("mode",mode);

		return "UserFormView";

	}


	@RequestMapping(value = "/UserForm/Edit" ,produces="text/plan;charset=UTF-8",method = RequestMethod.POST)
	public String Edit(@Valid @ModelAttribute("UserForm") UserModel userModel,
			BindingResult result,
			Model model,
			@RequestParam("mode") int mode,
			@RequestParam("page") int page,
			@RequestParam("preScreen") int preScreen,
			HttpServletRequest request) throws ParseException{

		HttpSession session = request.getSession();
		/* セッションからログインユーザ情報取得 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");
		if (sesUserModel==null){
			return "Login";
		}

		/* 権限取得 */
		String sesAuthorityLevel =  (String) session.getAttribute("authorityLevel");
		model.addAttribute("authorityLevel",sesAuthorityLevel);


		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		/* 遷移元画面をモデルに追加 */
		model.addAttribute("preScreen",preScreen);

		/* ログインユーザの権限をモデルに追加 */
		model.addAttribute("authority",sesUserModel.getAuthority());

		ResultMessages messages = ResultMessages.error();
		ResultMessages messagesInfo = ResultMessages.info();
		userModel = userService.setUserModel(userModel);
		if (result.hasErrors()) {

			//model.addAttribute("title","error");
			if (mode == 1){
				model.addAttribute("mode","1");
			}
			if (mode == 2){
				model.addAttribute("mode","2");
			}

			model.addAttribute("page",page);

			//単項目チェックのメッセージ取得
			Object[] objItemJudge = commonChecker.itemCheck(result);
			messages = (ResultMessages) objItemJudge[2];
			boolean blCk = (boolean) objItemJudge[0];

			if(blCk){
				model.addAttribute(messages);
				model.addAttribute(userModel);
				return "UserFormView";
			}
		}

		// Form → Entity
		UserEntity userEntity = new UserEntity();
		userEntity = userService.setUserFormToEntityService(userModel);
		model.addAttribute(userModel);

		switch (mode){
		case 1:
			//USER TABLEの追加をします。

			// USER TBL UPDATE
			try {

				/* USER FORM入力チェック */
				Object[] obj= userChecker.userCheck(userModel);
				boolean bolCeck = (boolean) obj[0];
				if (bolCeck){
					/* メッセージ取得 */
					messages = (ResultMessages) obj[1];
					model.addAttribute(messages);
					model.addAttribute(messages);
					model.addAttribute("mode",1);
				}else{
					userService.insUserTbl(userEntity);
					messagesInfo = ResultMessages.info();
					messagesInfo.add("i.te.fw.0001");
					model.addAttribute(messagesInfo);
					model.addAttribute("mode",2);
				}



				logger.info("i.te.fw.0001");

			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				model.addAttribute("mode",1);
				logger.error("e.te.fw.9001",e);

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				model.addAttribute("mode",1);
				logger.error("e.te.fw.9001",e);

			}

			model.addAttribute("title","User");
			model.addAttribute("page",page);
			break;

		case 2:

			//USER TABLEの更新を行います。

			// USER TBL UPDATE
			try {
				userService.updUserTbl(userEntity);

				messagesInfo = ResultMessages.info();
				messagesInfo.add("i.te.fw.0002");
				model.addAttribute(messagesInfo);
				logger.info("i.te.fw.0002");

			} catch (NamingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();

				messages = ResultMessages.error();
				messages.add("e.te.fw.9001");
				model.addAttribute(messages);
				logger.error("e.te.fw.9001,e");

			}

			model.addAttribute("title","User");
			model.addAttribute("page",page);
			model.addAttribute("mode","2");
			break;
		}

		return "UserFormView";

	}
}
