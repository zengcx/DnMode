package ����ӿڱ��;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test1 {
	public class Test1 {
		@Test
		public void demo01(){
		    //��spring�������
		    //1 �������
		    String xmlPath = "����ӿڱ��/applicationContext.xml";
		    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		    //2������� --����Ҫ�Լ�new�����Ǵ�spring�������
		    XidianSchool bean = (XidianSchool)applicationContext.getBean("XidianSchool");
		    bean.getSchoolInfo();   
		}
}
}
