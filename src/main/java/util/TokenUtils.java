package util;

import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Shixiaodong
 * @description:
 * @create 2019-09-25 16:48
 */
public class TokenUtils {

    private Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    // 获取Token
    public String getToken(String url, String appId, String timestamp, String nonce) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100000).setSocketTimeout(100000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        List<String> list = new ArrayList<String >();
        list.add(appId);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String content = StringUtils.linkString(StringUtils.LINk_WITH_HYPHEN, list);
        // 签名
        String sign = RSAUtils.sign(SHAUtils.sha(content), RSAUtils.getLocalPrivateKeyString());

        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        NameValuePair pair1 = new BasicNameValuePair("appId", appId);
        NameValuePair pair2 = new BasicNameValuePair("timestamp", timestamp);
        NameValuePair pair3 = new BasicNameValuePair("nonce", nonce);
        NameValuePair pair4 = new BasicNameValuePair("sign", sign);
        pairs.add(pair1);
        pairs.add(pair2);
        pairs.add(pair3);
        pairs.add(pair4);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));

        httpResponse = httpClient.execute(httpPost);
        JSONObject response = null;
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("result = {}", result);
            response = JSONObject.fromObject(result);
        }
        if ("200".equals(response.get("result").toString())) {
            return response.getJSONObject("data").get("token").toString();
        }
        return null;
    }


}
