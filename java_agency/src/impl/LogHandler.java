package impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	// 目标对象
	private Object targetObject;

	public Object newProxyInstance(Object o) {
		this.targetObject = o;
		// 该方法用于为指定类装载器，一组接口及调用处理器生成动态实例
		// 第一个参数指定代理对象的类加载器，需要指定为和目标对象同一个类加载器
		// 第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
		// 第三个参数表明这些被拦截的方法在被拦截时需要执行哪个invocationhandler的invoke方法
		// 根据传入的目标返回一个代理对象.
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
				this);

	}

	@Override
	// 关联这个实现类的方法被调用即执行
	/* InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start--->>");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		Object ret = null;

		try {

			/* 原对象方法调用前处理日志信息 */
			System.out.println("satrt-->>");
			// 调用目标方法
			ret = method.invoke(targetObject, args);
			/* 原对象方法调用后处理日志信息 */
			System.out.println("success-->>");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("error-->>");
			throw e;

		}
		return ret;
	}

}
