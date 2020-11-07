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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.CommonCheckerIF;
import app.co.jp.business.IF.PasswordCheangeCheckerIF;
import app.co.jp.model.PassWordChangeModel;
import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;



@Controller
public class PassWordChangeController {

    private static final Logger logger = LoggerFactory
            .getLogger(PassWordChangeController.class);

	@Autowired
	CommonCheckerIF commonChecker;

	@Autowired
	UserServiceIF userService;

	@Autowired
	PasswordCheangeCheckerIF PasswordCheangeChecker;

	@Autowired
	PropertiesUtil propertiesUtil;

	@RequestMapping(value = "/PassWordChangeForm" ,method = RequestMethod.GET)
	public String passwordChange(Model model,
			HttpServletRequest request){

		HttpSession session = request.getSession();
		/* セッションからログインユーザ情報取得 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");
		if (sesUserModel==null){
			return "Login";
		}
		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		model.addAttribute("loginId", sesUserModel.getUserid());


		return "PasswordChangeView";

	}

	@RequestMapping(value = "/PassWordChangeForm" ,produces="text/plan;charset=UTF-8",method = RequestMethod.POST)
	public String passwordChange(@Valid @ModelAttribute("PassWordChangeForm") PassWordChangeModel passWordChangeModel,
			BindingResult result,
			Model model,
			HttpServletRequest request) throws ParseException{


		HttpSession session = request.getSession();
		/* セッションからログインユーザ情報取得 */
		UserModel sesUserModel =  (UserModel) session.getAttribute("userModel");
		if (sesUserModel==null){
			return "Login";
		}

		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		passWordChangeModel.setLoginId(sesUserModel.getUserid());
		passWordChangeModel.setNowPassword(sesUserModel.getPassword());
		ResultMessages messages = ResultMessages.error();
		if (result.hasErrors()) {

			//単項目チェックのメッセージ取得
			Object[] objItemJudge = commonChecker.itemCheck(result);
			messages = (ResultMessages) objItemJudge[2];
			model.addAttribute(messages);

			return "PasswordChangeView";
		}

		Object[] objPasswordCheangeCheck = PasswordCheangeChecker.
				passwordChangeCheck(passWordChangeModel);

		boolean bolFlg = (boolean) objPasswordCheangeCheck[0];
		if(bolFlg){
			//パスワード変更時のエラーメッセージ取得
			messages = (ResultMessages) objPasswordCheangeCheck[1];
			model.addAttribute(messages);

			return "PasswordChangeView";
		}


		try {
			userService.updPasssword(passWordChangeModel.getLoginId(), passWordChangeModel.getNewPassword());

			return "Menu";

		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			e.printStackTrace();
			messages = ResultMessages.error();
			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001,e");

			return "PasswordChangeView";

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック

			e.printStackTrace();
			messages = ResultMessages.error();
			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001,e");


			return "PasswordChangeView";
		}

	}

}
