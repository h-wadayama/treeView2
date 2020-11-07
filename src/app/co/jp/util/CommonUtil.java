package app.co.jp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import app.co.jp.model.DocModel;
import app.co.jp.model.UserModel;

@Component
public class CommonUtil {
	public DBConnect getContext(){

		ApplicationContext contxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		DBConnect DBcon = (DBConnect)contxt.getBean("jndi");

		return DBcon;
	}

	public int getMaxId(List<DocModel> docModel){

		int intId = 0;

		for(DocModel doc:docModel){
			if (intId <= Integer.parseInt(doc.getId())){
				intId = Integer.parseInt(doc.getId());
			}
		}

		return intId;
	}


	public ConstUtil getConstUtil(){

		@SuppressWarnings("resource")
		ApplicationContext contxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ConstUtil constUtil = (ConstUtil) contxt.getBean("commonUtil");

		return constUtil;

	}

	public List<UserModel> removeUser(List<UserModel> userLIst){

		List<UserModel> userModel = new ArrayList<UserModel>();
		userModel.addAll(userLIst);

		for (int i = 0; i < userLIst.size(); i++) {
			String user = userLIst.get(i).getUserid();
			System.out.print(user);

			if (user.equals(Const.SUPER_USER)){
				userModel.remove(i);
			}
		}


		return userModel;
	}

}
