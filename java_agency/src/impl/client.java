package impl;

import inter.UserManager;

public class client {
	public static void main(String[] args) {
		//���þ�̬����
		// UserManager userManager=new UserManagerImplProxy(new UserManagerImpl());  
		 //userManager.addUser("1111", "����");  
		 
		//���ö�̬����
		 
		 LogHandler logHandler=new LogHandler();
		 UserManager userManager1=(UserManager)logHandler.newProxyInstance(new UserManagerImpl()); 
		 
		 userManager1.addUser("1111", "����");
	}
}
