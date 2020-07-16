package util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shixiaodong
 * @description:
 * @create 2019-03-28 20:59
 */
public class RSAUtils {

    private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);

    private static final String SIGN_ALGORITHMS = "MD5WithRSA";

    private static final String KEY_ALGORITHMS = "RSA";

    private static final String CHARSET = "UTF-8";

    public static final String PADDING = "RSA/ECB/PKCS1Padding";


    /**
     * 加密
     * @param content 加密内容
     * @param publicKeyString 公钥字符串
     * @return
     */
    public static String encrypt(String content, String publicKeyString) {
        return encrypt(content, getPublicKey(publicKeyString));
    }

    /**
     * 加密
     * @param content 加密内容
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try {
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64Utils.encode(cipher.doFinal(content.getBytes(CHARSET)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 解密
     * @param content 密文
     * @param privateKeyString 私钥字符串
     * @return
     */
    public static String decrypt(String content, String privateKeyString) {
        return decrypt(content, getPrivateKey(privateKeyString));
    }

    /**
     * 解密
     * @param content 密文
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64Utils.decode(content)), CHARSET);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 签名
     * @param content 签名内容
     * @param privateKeyString 私钥字符串
     * @return
     */
    public static String sign(String content, String privateKeyString) {
        return sign(content, getPrivateKey(privateKeyString));
    }

    /**
     * 签名
     * @param content 签名内容
     * @param privateKey 私钥
     * @return
     */
    public static String sign(String content, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey);
            signature.update(content.getBytes(CHARSET));
            return Base64Utils.encode(signature.sign());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 验签
     * @param original 原文
     * @param sign 签名
     * @param publicKeyString 公钥字符串
     * @return
     */
    public static boolean verify(String original, String sign, String publicKeyString) {
        return verify(original, sign, getPublicKey(publicKeyString));
    }

    /**
     * 验签
     * @param original 原文
     * @param sign 签名
     * @param publicKey 公钥
     * @return
     */
    public static boolean verify(String original, String sign, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(original.getBytes(CHARSET));
            return signature.verify(Base64Utils.decode(sign));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }


    public static PublicKey getPublicKey(String key) {
        PublicKey publicKey = null;
        try {
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String key) {
        PrivateKey privateKey = null;
        try {
            byte[] keyByte = (new BASE64Decoder()).decodeBuffer(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return privateKey;
    }

    public static String getPublicKeyString(PublicKey publicKey) {
        return Base64Utils.encode(publicKey.getEncoded());
    }

    public static String getPrivateKeyString(PrivateKey privateKey) {
        return Base64Utils.encode(privateKey.getEncoded());
    }

    public static final String PUBLIC_KEY = "publicKey";

    public static final String PRIVATE_KEY = "privateKey";

    public static Map<String, Object> getKey() {
        Map<String, Object> keyMap = new HashMap(2);
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHMS);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return keyMap;
    }

    public static String getLocalPublicKeyString() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtLb5vpWGtSoFpIkWVAXWPAfre0hO9a/BEOn1A4aY1W9IjrTmRPU1mmSnu5NyX4qD3T3hqnBqiFHB3Tbf6d5KrhpSVuXGxjIo+K+/V2rAkOGLOUbcOoeqRAX1+omrXn5AvWotXzvwHJNWld15pQZKuoVoOg39Ft69caqmlEYhDAQIDAQAB";
    }

    public static String getLocalPrivateKeyString() {
        return "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK0tvm+lYa1KgWkiRZUBdY8B+t7SE71r8EQ6fUDhpjVb0iOtOZE9TWaZKe7k3JfioPdPeGqcGqIUcHdNt/p3kquGlJW5cbGMij4r79XasCQ4Ys5Rtw6h6pEBfX6iatefkC9ai1fO/Ack1aV3XmlBkq6hWg6Df0W3r1xqqaURiEMBAgMBAAECgYBad5cQQ9ju6gkInvqyiHydJG+ljyD4xzZQri1Yg4iMP1dbg9aa5OdWPZUGU1BZl+52w3EoYDwZh4vpBxShJ23ah/KTM6+d5Gt/PQUvEDNWF8s4y/3r5f/3oVCZjlYShJczW9AIK9BAXa72hqnUd7ZbY9ZFU3xHuOofAlBkn1BZAQJBAOieIezmiOcPmErE8SGsp3AEkTilfoy3V4nm07TBAEkeqg4ooJHt9GI2q+ekRkcTpbd8O1jAKcYr1UAEtRGtb3kCQQC+lhasEfOV8gV/ghSg1BAYm17P+mIkPu/g/WMfUR8huqavc8O99s1LbzyjMiRnFBVPTxXiFUGICYaJA9HxKWXJAkBkAEc5XsoP+0ZPLmczQNQidfQwVAUZXWBGqnLsy+Phi0s0FwcgdRmQd0xjot2LhMjDHqcsuj+7L/DXOq7U0KiRAkEAtUZOtEWy2XlEz33rnvl1geIuMUWnGXCfevGn2T6wjMVbC+DhWosnw0s/cr4br8uDZVcEqnCrTvg4dZQjmO1WOQJAPsuWswxwKL/WPmOiH7yF/R5l6mSBkCLoSipzr/hs7j4ylSudFROYYELP2FDjXDqZ7u5tixgUi0tFz5HjT3LtQA==";
    }


    public static void main(String[] args) {
        Map<String, Object> keyMap = getKey();
        PublicKey publicKey = (PublicKey) keyMap.get(PUBLIC_KEY);
        PrivateKey privateKey = (PrivateKey) keyMap.get(PRIVATE_KEY);
        JSONObject json = new JSONObject();
        json.put("1", "1");
        json.put("2", "2");
        json.put("3", "3");

        System.out.println(getPublicKeyString(publicKey));
        System.out.println(getPrivateKeyString(privateKey));

        // 签名
        String signString = sign(json.toString(), privateKey);
        System.out.println(signString);
        // 验签
        System.out.println(verify(json.toString(), signString, publicKey));

        // 加密
        String en = encrypt(json.toString(), publicKey);
        // 解密
        String de = decrypt(en, privateKey);
        System.out.println(json.toString().equals(de));

        System.out.println("K+YSvtTjYhiop2rBD+lxak9NArl3VSJa+mzHm3PUpJKD+2E6dhus5hCaIReJ+bkKcaAkupRJZeRgdxpXaMh/UbPGSVuKydjfcTMSzBh8M0Nwo6yJm71KgVsXd/95cbCvnxd4AKNu47CvSTXJdHGSVRuY+ecXrWQlqxj8aW97ndo=".length());
    }

}
