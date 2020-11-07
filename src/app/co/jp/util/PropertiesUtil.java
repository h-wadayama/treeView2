package app.co.jp.util;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropertiesUtil {

	@Autowired
	private Environment env;
	public String getText(String key){

		Properties prop = new Properties();
		String text ="";
		text = env.getProperty(key);

		return text;

	}


}
