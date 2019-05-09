package com.august.commons.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;

public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";

    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    public static final String SECRET_KEY = "JkiL#%^L15407895@!*(qShz";

    /**
     * @param content 待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("AES加密异常,异常信息,明文{0}，密匙{1}",content,password),e);
        }
    }

    /**
     * AES 解密操作
     * @param content 解密内容
     * @param password 密码
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("AES解密异常,异常信息,密文{0}，密匙{1}",content,password),e);
        }

    }

    /**
     * 生成加密秘钥
     * @param password 密码
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成加密秘钥异常,异常信息",e);
        }
    }

    public static void main(String[] args) {
        System.out.println(encrypt("15926633889",SECRET_KEY));
        System.out.println(decrypt("wEE+ZP+QUryfOlslxt/6wA==",SECRET_KEY));
    }
}
