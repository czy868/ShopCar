package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author Shixiaodong
 * @description:
 * @create 2019-04-19 11:28
 */
public class SHAUtils {

    private static Logger logger = LoggerFactory.getLogger(SHAUtils.class);

    private static final String SHA_1 = "SHA-1";

    private static final String CHARSET = "UTF-8";

    // 散列
    public static String sha(String text) {
        byte[] shaDigest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_1);
            byte[] plainText = text.getBytes(CHARSET);
            md.update(plainText);
            shaDigest = md.digest();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Base64Utils.encode(shaDigest);
    }
}
