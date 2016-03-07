package http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Funker on 20.02.2016.
 */
public class HttpImpl implements Http {

    public String get(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            response.close();
        }
    }

    @Override
    public String post(String url) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);

        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setCircularRedirectsAllowed(true);

        RequestConfig requestConfig = builder.build();
        request.setConfig(requestConfig);


//        HttpParams httpParams = new BasicHttpParams();
//        httpParams.setParameter("secretkey", "secret");
//        request.setParams(httpParams);


        CloseableHttpResponse response = client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            response.close();
        }
//        System.out.println(EntityUtils.toString(resEntity));
//        EntityUtils.consume(resEntity);
//        httpClient.close();


//        return null;
    }
}
