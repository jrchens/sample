package wx;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.List;

public class AccessTokenTest {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenTest.class);

    /**
     * <pre>
     *     appID
     *    appsecret
     * </pre>
     *
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            String appid = "wx00979ffb52ef6c4f";
            String appsecret = "219810842bebe6c15351d938eb577953";

            // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            String scheme = "https";
            String host = "api.weixin.qq.com";
            String path = "/cgi-bin/token";
            
            List<NameValuePair> nvps = Lists.newArrayList();
            nvps.add(new BasicNameValuePair("grant_type", "client_credential"));
            nvps.add(new BasicNameValuePair("appid", appid));
            nvps.add(new BasicNameValuePair("secret", appsecret));

            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme).setHost(host).setPath(path);
            builder.addParameters(nvps);
            

            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(builder.build().toString());
            
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();


            // access_token,expires_in
            // errcode,errmsg
            // AccessToken

            String json = EntityUtils.toString(entity, "UTF-8");
            logger.info("get access_token response json : {}",json);
            // {"access_token":"C7D0CQ_jZoG8ZxpUwo5fKhcTh5DgQgeXPzTPu_m5tORYx76T4TzyKSYMGQpgZMHR-EAFw0X00UTxmtFNyiUgrxnXmAyr6MIyVrVmE2fz1hnqA37-o_GOlPhMDGzle2o5ESPaAGAWKH","expires_in":7200}
        } finally {
            response.close();
            httpclient.close();
        }
    }


    @Test
    public void vaild() throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            String appid = "wx8741d5a95ebb85b5";
            String appsecret = "c4e71a7d035b600740fd4cab0053ea3a";

            // https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
            String scheme = "https";
            String host = "api.weixin.qq.com";
            String path = "/cgi-bin/token";

            List<NameValuePair> nvps = Lists.newArrayList();
            nvps.add(new BasicNameValuePair("grant_type", "client_credential"));
            nvps.add(new BasicNameValuePair("appid", appid));
            nvps.add(new BasicNameValuePair("secret", appsecret));

            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme).setHost(host).setPath(path);
            builder.addParameters(nvps);


            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(builder.build().toString());

            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();


            // access_token,expires_in
            // errcode,errmsg
            // AccessToken

            String json = EntityUtils.toString(entity, "UTF-8");
            logger.info("get access_token response json : {}",json);
            // {"access_token":"C7D0CQ_jZoG8ZxpUwo5fKhcTh5DgQgeXPzTPu_m5tORYx76T4TzyKSYMGQpgZMHR-EAFw0X00UTxmtFNyiUgrxnXmAyr6MIyVrVmE2fz1hnqA37-o_GOlPhMDGzle2o5ESPaAGAWKH","expires_in":7200}
        } finally {
            response.close();
            httpclient.close();
        }
    }
}
