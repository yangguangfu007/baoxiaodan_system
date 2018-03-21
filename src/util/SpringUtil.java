package util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	public static  ClassPathXmlApplicationContext getAC(){
		return new ClassPathXmlApplicationContext("application-context.xml");
	}
}
