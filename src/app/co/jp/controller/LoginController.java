/*
 * 処理名     ：ログイン用コントローラー
 * クラス名 ：app.co.jp.controller.LoginController.java
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

import app.co.jp.business.IF.LoginCheckerIF;
import app.co.jp.entity.UserEntity;
import app.co.jp.model.LoginFormModel;
import app.co.jp.model.UserModel;
import app.co.jp.service.IF.UserServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory
            .getLogger(LoginController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	LoginCheckerIF loginChecker;

	@Autowired
	UserServiceIF userService;

	@Autowired
	PropertiesUtil propertiesUtil;

	/*
	 * 処理名     ：ログイン画面GETメソッド
	 * メソッド名 ：login
	 *
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2019-05-01
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String login(Model model){

		logger.info("Get LoginController.login(Model model)");

		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		return "Login";

	}

/*
 * 処理名     ：ログイン画面POSTメソッド
 * メソッド名 ：login
 *
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
	@RequestMapping(value = "/LoginForm" ,produces="text/plan;charset=UTF-8",method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("LoginForm") LoginFormModel loginForm ,
			BindingResult result ,
			Model model,
			HttpServletRequest request) throws ParseException{

		logger.info("Post LoginController.login(....)");
		UserModel userModel = new UserModel();
		ResultMessages messages = ResultMessages.error();


		String projectName =  propertiesUtil.getText(Const.PROJECT);
		model.addAttribute("project", projectName);

		boolean check = false;
		try {

			// 単項目チェック
			Object[] objItemJudge = loginChecker.loginItemCheck(result);
			check = (boolean) objItemJudge[0];
			if (check){
				messages = (ResultMessages) objItemJudge[2];
				model.addAttribute(messages);
				return "Login";
			}

			// ログインチェック
			Object[] objLoginJudge = loginChecker.loginCheck(loginForm);
			check = (boolean) objLoginJudge[0];

			if (check){
				messages = (ResultMessages) objLoginJudge[2];
				model.addAttribute(messages);
				return "Login";
			}

			UserEntity userEntity = (UserEntity) objLoginJudge[1];
			userModel = userService.setUserToFormService(userEntity);

			/* セッションに登録 */
			HttpSession session = request.getSession(true);
			session.setAttribute("userModel",userModel);
			session.setAttribute("authority",userModel.getAuthority());
			String authorityLevel = "";

			if (userModel.getUserid().equals(Const.SUPER_USER)){
				authorityLevel = Const.AUTHORITY_LEVEL1;
			}else{
				if (StringUtils.isEmpty(userModel.getAuthority())){
					authorityLevel = Const.AUTHORITY_LEVEL3;
				}else{
					authorityLevel = Const.AUTHORITY_LEVEL2;
				}

			}
			session.setAttribute("authorityLevel",authorityLevel);
			model.addAttribute("authorityLevel",authorityLevel);
			//session.setAttribute("userId", userModel.getUserid() );
			//session.setAttribute("userName", userModel.getName());


		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001",e);

			return "Login";

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			messages.add("e.te.fw.9001");
			model.addAttribute(messages);
			logger.error("e.te.fw.9001",e);

			return "Login";
		}

		model.addAttribute("authority",userModel.getAuthority());
		model.addAttribute("loginId",userModel.getUserid());


		return "Menu";

	}

}
