/*
 * 処理名     ：メニューコントローラー
 * クラス名 ：app.co.jp.controller.MenuController.java.java
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.co.jp.model.UserModel;

@Controller
public class MenuController {

	@RequestMapping(value = "/Menu" ,method = RequestMethod.GET)
	public String menu(Model model,
			HttpServletRequest request){

		HttpSession session = request.getSession();
		/* セッションからログインユーザ情報取得 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");
		if (sesUserModel==null){
			return "Login";
		}
		/* セッションから権限取得 */
		String sesAuthority =  (String) session.getAttribute("authority");
		String sesAuthorityLevel =  (String) session.getAttribute("authorityLevel");

		model.addAttribute("authority",sesAuthority);
		model.addAttribute("authorityLevel",sesAuthorityLevel);

		/* ログインユーザIDをモデルに追加 */
		model.addAttribute("loginId",sesUserModel.getUserid());
		return "Menu";

	}

}
