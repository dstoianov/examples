package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.File;


public class MultipartPost {

    protected String url = "http://cvu.zanox.com/rest/upload/cv";

    @Test
    public void testName() throws Exception {
        HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();

        HttpPost httpPost = new HttpPost(url);

        MultipartEntity reqEntity = new MultipartEntity();
        File file = new File("D:\\My Documents\\Dropbox\\test.docx");
        FileBody uploadFilePart = new FileBody(file);

        reqEntity.addPart("file", uploadFilePart);
        reqEntity.addPart("firstname", new StringBody("test", ContentType.TEXT_PLAIN));
        reqEntity.addPart("lastname", new StringBody("lastname", ContentType.TEXT_PLAIN));
        reqEntity.addPart("email", new StringBody("email", ContentType.TEXT_PLAIN));
        reqEntity.addPart("jobtitle", new StringBody("jobtitle", ContentType.TEXT_PLAIN));
        reqEntity.addPart("source", new StringBody("source", ContentType.TEXT_PLAIN));

        httpPost.setEntity(reqEntity);

        System.out.println("executing request " + httpPost.getRequestLine());

        HttpResponse response = httpClient.execute(httpPost);

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            System.out.println("Response content length: " + resEntity.getContentLength());
        }
        System.out.println(EntityUtils.toString(resEntity));
        EntityUtils.consume(resEntity);
        httpClient.close();
    }
}
