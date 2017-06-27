package impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	// Ŀ�����
	private Object targetObject;

	public Object newProxyInstance(Object o) {
		this.targetObject = o;
		// �÷�������Ϊָ����װ������һ��ӿڼ����ô��������ɶ�̬ʵ��
		// ��һ������ָ���������������������Ҫָ��Ϊ��Ŀ�����ͬһ���������
		// �ڶ�������Ҫʵ�ֺ�Ŀ�����һ���Ľӿڣ�����ֻ��Ҫ�õ�Ŀ������ʵ�ֽӿ�
		// ����������������Щ�����صķ����ڱ�����ʱ��Ҫִ���ĸ�invocationhandler��invoke����
		// ���ݴ����Ŀ�귵��һ���������.
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
				this);

	}

	@Override
	// �������ʵ����ķ��������ü�ִ��
	/* InvocationHandler�ӿڵķ�����proxy��ʾ����method��ʾԭ���󱻵��õķ�����args��ʾ�����Ĳ��� */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start--->>");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		Object ret = null;

		try {

			/* ԭ���󷽷�����ǰ������־��Ϣ */
			System.out.println("satrt-->>");
			// ����Ŀ�귽��
			ret = method.invoke(targetObject, args);
			/* ԭ���󷽷����ú�����־��Ϣ */
			System.out.println("success-->>");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("error-->>");
			throw e;

		}
		return ret;
	}

}
