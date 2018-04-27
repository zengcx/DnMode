package 面向接口编程;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {
	public class Test1 {
		@Test
		public void demo01(){
		    //从spring容器获得
		    //1 获得容器
		    String xmlPath = "面向接口编程/applicationContext.xml";
		    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		    //2获得内容 --不需要自己new，都是从spring容器获得
		    XidianSchool bean = (XidianSchool)applicationContext.getBean("XidianSchool");
		    bean.getSchoolInfo();   
		}
}
}
