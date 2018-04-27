package test;

import java.lang.reflect.Constructor;

public class Test3 {
public static void main(String[] args) throws ClassNotFoundException {
		Class<?> cls = Class.forName("java.lang.String");
		Constructor<?>[] cons = cls.getConstructors();//取得全部构造
		for(int x = 0;x < cons.length;x++){
			System.out.println(cons[x]);
		}
}
}
