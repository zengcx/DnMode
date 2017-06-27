package impl;

import inter.UserManager;

public class client {
	public static void main(String[] args) {
		//调用静态代理
		// UserManager userManager=new UserManagerImplProxy(new UserManagerImpl());  
		 //userManager.addUser("1111", "张三");  
		 
		//调用动态代理
		 
		 LogHandler logHandler=new LogHandler();
		 UserManager userManager1=(UserManager)logHandler.newProxyInstance(new UserManagerImpl()); 
		 
		 userManager1.addUser("1111", "张三");
	}
}
