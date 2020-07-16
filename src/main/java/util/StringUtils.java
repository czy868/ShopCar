package util;

import java.util.List;

/**
 * @author Shixiaodong
 * @description:
 * @create 2019-03-28 21:42
 */
public class StringUtils {

    public static String LINk_WITH_HYPHEN = "-";

    public static String LINK_WITH_COMMA = ",";

    public static String linkString(String LINK_MODE, List<String> params) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i));
            if (i != params.size() - 1) {
                buffer.append(LINK_MODE);
            }
        }
        return buffer.toString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null ? true : str.length() == 0 ? true : false;
    }

}
