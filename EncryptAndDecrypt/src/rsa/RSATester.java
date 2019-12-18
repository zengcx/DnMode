package rsa;


import java.util.Map;
 
public class RSATester {
 
    static String publicKey;
    static String privateKey;
 
    
    public static void main(String[] args) throws Exception {
    	//获得公钥和私钥
    	try {
            Map<String, Object> keyMap = Rsa.genKeyPair();
            publicKey = Rsa.getPublicKey(keyMap);
            privateKey = Rsa.getPrivateKey(keyMap);
            System.err.println("公钥: \n" + publicKey);
            System.err.println("私钥： \n" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	//yfx：RSA加解密效率低，并且好像是新线程执行的，导致下面的日志输出位置，不是按照代码顺序
        //test();
       testSign();
//        testHttpSign();
        
    }
 
    static void test() throws Exception {
    	System.out.println("==========================================1");
        System.err.println("公钥加密——私钥解密");
        String source = "test()这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        String encodedData = Rsa.encryptByPublicKey(source, publicKey);
        System.out.println("加密后文字：\r\n" + encodedData);
        String decodedData = Rsa.decryptByPrivateKey(encodedData, privateKey);
        System.out.println("解密后文字: \r\n" + decodedData);
    }
 
    static void testSign() throws Exception {
    	System.out.println("==========================================22");
        System.err.println("私钥加密——公钥解密");
        String source = "testSign()这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        String encodedData = Rsa.encryptByPrivateKey(source, privateKey);
        System.out.println("加密后：\r\n" + encodedData);
        String decodedData = Rsa.decryptByPublicKey(encodedData, publicKey);
        System.out.println("解密后: \r\n" + decodedData);
        System.out.println("==========================================333");
        System.err.println("私钥签名——公钥验证签名");
        String sign = Rsa.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = Rsa.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }
    
    static void testHttpSign() throws Exception {
    	System.out.println("==========================================4444");
        String param = "id=1&name=张三";
        String encodedData = Rsa.encryptByPrivateKey(param, privateKey);
        System.out.println("加密后：" + encodedData);
        
        String decodedData = Rsa.decryptByPublicKey(encodedData, publicKey);
        System.out.println("解密后：" + new String(decodedData));
        
        String sign = Rsa.sign(encodedData, privateKey);
        System.err.println("签名：" + sign);
        
        boolean status = Rsa.verify(encodedData, publicKey, sign);
        System.err.println("签名验证结果：" + status);
    }
    
    
}
