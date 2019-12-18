package rsa;


import java.util.Map;
 
public class RSATester {
 
    static String publicKey;
    static String privateKey;
 
    
    public static void main(String[] args) throws Exception {
    	//��ù�Կ��˽Կ
    	try {
            Map<String, Object> keyMap = Rsa.genKeyPair();
            publicKey = Rsa.getPublicKey(keyMap);
            privateKey = Rsa.getPrivateKey(keyMap);
            System.err.println("��Կ: \n" + publicKey);
            System.err.println("˽Կ�� \n" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	//yfx��RSA�ӽ���Ч�ʵͣ����Һ��������߳�ִ�еģ������������־���λ�ã����ǰ��մ���˳��
        //test();
       testSign();
//        testHttpSign();
        
    }
 
    static void test() throws Exception {
    	System.out.println("==========================================1");
        System.err.println("��Կ���ܡ���˽Կ����");
        String source = "test()����һ��û���κ���������֣��㿴���˵���û����������";
        System.out.println("\r����ǰ���֣�\r\n" + source);
        String encodedData = Rsa.encryptByPublicKey(source, publicKey);
        System.out.println("���ܺ����֣�\r\n" + encodedData);
        String decodedData = Rsa.decryptByPrivateKey(encodedData, privateKey);
        System.out.println("���ܺ�����: \r\n" + decodedData);
    }
 
    static void testSign() throws Exception {
    	System.out.println("==========================================22");
        System.err.println("˽Կ���ܡ�����Կ����");
        String source = "testSign()����һ�в���RSA����ǩ��������������";
        System.out.println("ԭ���֣�\r\n" + source);
        String encodedData = Rsa.encryptByPrivateKey(source, privateKey);
        System.out.println("���ܺ�\r\n" + encodedData);
        String decodedData = Rsa.decryptByPublicKey(encodedData, publicKey);
        System.out.println("���ܺ�: \r\n" + decodedData);
        System.out.println("==========================================333");
        System.err.println("˽Կǩ��������Կ��֤ǩ��");
        String sign = Rsa.sign(encodedData, privateKey);
        System.err.println("ǩ��:\r" + sign);
        boolean status = Rsa.verify(encodedData, publicKey, sign);
        System.err.println("��֤���:\r" + status);
    }
    
    static void testHttpSign() throws Exception {
    	System.out.println("==========================================4444");
        String param = "id=1&name=����";
        String encodedData = Rsa.encryptByPrivateKey(param, privateKey);
        System.out.println("���ܺ�" + encodedData);
        
        String decodedData = Rsa.decryptByPublicKey(encodedData, publicKey);
        System.out.println("���ܺ�" + new String(decodedData));
        
        String sign = Rsa.sign(encodedData, privateKey);
        System.err.println("ǩ����" + sign);
        
        boolean status = Rsa.verify(encodedData, publicKey, sign);
        System.err.println("ǩ����֤�����" + status);
    }
    
    
}
