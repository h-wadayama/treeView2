/*
 * 処理名     ：ユーザリスト用コントローラー
 * クラス名 ：app.co.jp.controller.UserListController.java
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.controller;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserServiceIF;
import app.co.jp.util.CommonUtil;

@Controller
public class UserListController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserListController.class);

	@Autowired
	UserServiceIF userService;

	@Autowired
	CommonUtil commonUtil;

	@RequestMapping(value = "/UserList" ,method = RequestMethod.GET)
	public String UserList(Model model,@RequestParam(value = "page" ,defaultValue = "0") int Page,
			HttpServletRequest request){

		HttpSession session = request.getSession();
		/* セッション取得 */
		/* ユーザ情報 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");

		/* 権限取得 */
		String sesAuthorityLevel =  (String) session.getAttribute("authorityLevel");


		/* メッセージ7初期化 */
		ResultMessages messages = ResultMessages.error();

		PagedListHolder<UserModel> pagedListHolder;
		try {
			pagedListHolder = userService.userListPageingSet(Page);
			model.addAttribute("UserForm",pagedListHolder);

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			e.printStackTrace();

			messages = ResultMessages.error();
			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001",e);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			messages = ResultMessages.error();
			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001",e);
		}

		//model.addAttribute("formModel",new testform());
		model.addAttribute("title","User");
		model.addAttribute("authorityLevel",sesAuthorityLevel);

		return "UserListView";

	}

}
