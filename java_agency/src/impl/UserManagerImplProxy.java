package impl;

import inter.UserManager;
/**
 * �ŵ㣺
 * ����ʹ�ͻ��˲���Ҫ֪��ʵ������ʲô����ô���ģ����ͻ���ֻ��֪�������ɣ�����ϣ���
 * �������ϵĿͻ��˴��룬newUserManagerImpl()����Ӧ�ù����������أ�����ֻ�Ǿٸ����Ӷ��ѡ�
 * ȱ�㣺  
 * 1���������ί����ʵ������ͬ�Ľӿڣ�������ͨ��ί����ʵ������ͬ�ķ����������ͳ����˴����Ĵ����ظ���
 * ����ӿ�����һ����������������ʵ������Ҫʵ����������⣬���д�����Ҳ��Ҫʵ�ִ˷����������˴���ά���ĸ��Ӷȡ�
 * 2���������ֻ������һ�����͵Ķ������Ҫ��������͵Ķ����Ʊ�ҪΪÿһ�ֶ��󶼽��д�����̬�����ڳ����ģ�Դ�ʱ���޷�ʤ���ˡ�
 * ���ϵĴ�����ֻΪUserManager��ķ����ṩ�˴������������ҪΪ��������Department���ṩ����Ļ�������Ҫ�����ٴ���Ӵ���Department�Ĵ����ࡣ

 * @author chaoxing
 *
 */
public class UserManagerImplProxy implements UserManager {

	private UserManager userManager;

	public UserManagerImplProxy(UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public void addUser(String userId, String userName) {
		try {
			// ��Ӵ�ӡ��־�Ĺ���
			// ��ʼ����û�
			System.out.println("start-->addUser()");
			userManager.addUser(userId, userName);
			// ����û��ɹ�
			System.out.println("success-->addUser()");
		} catch (Exception e) {
			// ����û�ʧ��
			System.out.println("error-->addUser()");
		}

	}

	@Override
	public void delUser(String userId) {
		userManager.delUser(userId);

	}

	@Override
	public String findUser(String userId) {
		userManager.findUser(userId);
		return "����";

	}

	@Override
	public void modifyUser(String userId, String userName) {
		userManager.modifyUser(userId, userName);

	}

}
