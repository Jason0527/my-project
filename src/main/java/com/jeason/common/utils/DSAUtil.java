package com.jeason.common.utils;


import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class DSAUtil {
	
	public static final String KEY_ALGORITHM = "DSA";   
    public static final String SIGNATURE_ALGORITHM = "DSA"; 
	
    public static final String PUBLIC_KEY = "DSAPublicKey";   
    public static final String PRIVATE_KEY = "DSAPrivateKey"; 
    
    /**  
     * ��˽Կ����Ϣ�������ǩ��  
     *   
     * @param data  
     *            �������  
     * @param privateKey  
     *            ˽Կ  
     * @return  
     * @throws Exception  
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {   
        // ������base64�����˽Կ   
        byte[] keyBytes = Base64Util.base64Decode(privateKey);
  
        // ����PKCS8EncodedKeySpec����   
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);   
  
        // KEY_ALGORITHM ָ���ļ����㷨   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
  
        // ȡ˽Կ�׶���   
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);   
  
        // ��˽Կ����Ϣ�������ǩ��   
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);   
        signature.initSign(priKey);   
        signature.update(data);   
  
        return Base64Util.base64EncodeByByte(signature.sign());
    } 
    /**  
     * У������ǩ��  
     * @param data  
     *            �������  
     * @param publicKey  
     *            ��Կ  
     * @param sign  
     *            ����ǩ��  
     *   
     * @return У��ɹ�����true ʧ�ܷ���false  
     * @throws Exception  
     *   
     */  
    public static boolean verify(byte[] data, String publicKey, String sign)   
            throws Exception {   
  
        // ������base64����Ĺ�Կ   
        byte[] keyBytes = Base64Util.base64Decode(publicKey);
  
        // ����X509EncodedKeySpec����   
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);   
  
        // KEY_ALGORITHM ָ���ļ����㷨   
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);   
  
        // ȡ��Կ�׶���   
        PublicKey pubKey = keyFactory.generatePublic(keySpec);   
  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);   
        signature.initVerify(pubKey);   
        signature.update(data);   
        // ��֤ǩ���Ƿ���   
        return signature.verify(Base64Util.base64Decode(sign)); 
    }  
    /**  
     * �����Կ  
     *   
     * @param seed  
     *            ����  
     * @return ��Կ����  
     * @throws Exception  
     */  
    public static Map<String, Object> initKey(String seed) throws Exception {   
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);   
        // ��ʼ����������   
        SecureRandom secureRandom = new SecureRandom();   
        secureRandom.setSeed(seed.getBytes());   
        keygen.initialize(1024, secureRandom);   
  
        KeyPair keys = keygen.genKeyPair();   
  
        PublicKey publicKey = keys.getPublic();
        PrivateKey privateKey = keys.getPrivate();  
        
//        System.out.println(Base64Util.base64EncodeByByte(publicKey.getEncoded()));
  
        Map<String, Object> map = new HashMap<String, Object>(2);   
        /*map.put(PUBLIC_KEY, publicKey);   
        map.put(PRIVATE_KEY, privateKey);  */ 
        map.put(PUBLIC_KEY, Base64Util.base64EncodeByByte(publicKey.getEncoded()));   
        map.put(PRIVATE_KEY, Base64Util.base64EncodeByByte(privateKey.getEncoded()));
        return map;   
    }  
    /**  
     * ȡ��˽Կ  
     *   
     * @param keyMap  
     * @return  
     * @throws Exception  
     */  
    public static String getPrivateKey(Map<String, Object> keyMap)   
            throws Exception {   
        Key key = (Key) keyMap.get(PRIVATE_KEY);   
  
        return Base64Util.base64EncodeByByte(key.getEncoded());   
    }
    /**  
     * ȡ�ù�Կ  
     *   
     * @param keyMap  
     * @return  
     * @throws Exception  
     */  
    public static String getPublicKey(Map<String, Object> keyMap)   
            throws Exception {   
        Key key = (Key) keyMap.get(PUBLIC_KEY);   
  
        return Base64Util.base64EncodeByByte(key.getEncoded());   
    }

}
