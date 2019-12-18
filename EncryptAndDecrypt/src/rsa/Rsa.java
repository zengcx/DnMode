package rsa;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
 
/**
 * <p>
 * RSA��Կ/˽Կ/ǩ�����߰�
 * </p>
 * <p>
 * ���ɵ¡���ά˹�أ�Ron [R]ivest�������ϡ���Ī����Adi [S]hamir�������ɵ¡���������Leonard [A]dleman��
 * </p>
 * <p>
 * �ַ�����ʽ����Կ��δ������˵������¶�ΪBASE64�����ʽ<br/>
 * ���ڷǶԳƼ����ٶȼ��仺����һ���ļ���ʹ���������ܶ���ʹ�öԳƼ��ܣ�<br/>
 * �ǶԳƼ����㷨���������ԶԳƼ��ܵ���Կ���ܣ�������֤��Կ�İ�ȫҲ�ͱ�֤�����ݵİ�ȫ
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class Rsa {
 
    /**
     * �����㷨RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    
    /**
     * ǩ���㷨
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
 
    /**
     * ��ȡ��Կ��key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    
    /**
     * ��ȡ˽Կ��key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
    /**
     * RSA���������Ĵ�С
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA���������Ĵ�С
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
 
    /**
     * <p>
     * ������Կ��(��Կ��˽Կ)
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /**
     * <p>
     * ��˽Կ����Ϣ��������ǩ��
     * </p>
     * 
     * @param data �Ѽ�������
     * @param privateKey ˽Կ(BASE64����)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(String msg, String privateKey) throws Exception {
    	byte[] data = msg.getBytes();
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }
 
    /**
     * <p>
     * У������ǩ��
     * </p>
     * 
     * @param data �Ѽ�������
     * @param publicKey ��Կ(BASE64����)
     * @param sign ����ǩ��
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(String msg, String publicKey, String sign)
            throws Exception {
    	byte[] data = msg.getBytes();
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }
 
    /**
     * <P>
     * ˽Կ����
     * </p>
     * 
     * @param encryptedData �Ѽ�������
     * @param privateKey ˽Կ(BASE64����)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedDataStr, String privateKey)
            throws Exception {
    	byte[] encryptedData = Base64.getDecoder().decode(encryptedDataStr);
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // �����ݷֶν���
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }
 
    /**
     * <p>
     * ��Կ����
     * </p>
     * 
     * @param encryptedData �Ѽ�������
     * @param publicKey ��Կ(BASE64����)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptedDataStr, String publicKey)
            throws Exception {
		byte[] encryptedData = Base64.getDecoder().decode(encryptedDataStr);
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // �����ݷֶν���
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }
 
    /**
     * <p>
     * ��Կ����
     * </p>
     * 
     * @param data Դ����
     * @param publicKey ��Կ(BASE64����)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String msg, String publicKey)
            throws Exception {
    	byte[] data =  msg.getBytes();
    	byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // �����ݷֶμ���
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        
        String encryptedDataStr = Base64.getEncoder().encodeToString(encryptedData);
        return encryptedDataStr;
    }
 
    /**
     * <p>
     * ˽Կ����
     * </p>
     * 
     * @param data Դ����
     * @param privateKey ˽Կ(BASE64����)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String msg, String privateKey)
            throws Exception {
    	byte[] data = msg.getBytes();
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // �����ݷֶμ���
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        
        String encryptedDataStr = Base64.getEncoder().encodeToString(encryptedData);
        return encryptedDataStr;
    }
 
    /**
     * <p>
     * ��ȡ˽Կ
     * </p>
     * 
     * @param keyMap ��Կ��
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
 
    /**
     * <p>
     * ��ȡ��Կ
     * </p>
     * 
     * @param keyMap ��Կ��
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
 
}