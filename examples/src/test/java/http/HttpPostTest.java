package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class HttpPostTest {

//    http://cvu.zanox.com/

    protected String url = "http://cvu.zanox.com/rest/upload/cv";

    @Test
    public void testName() throws Exception {
        HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = new File("D:\\My Documents\\Dropbox\\Resume.docx");
        builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "Resume.docx");

        builder.addTextBody("firstname", "xxxxx");
        builder.addTextBody("lastname", "xxxxx");
        builder.addTextBody("email", "xxxxx@gmail.com");
        builder.addTextBody("jobtitle", "Java Senior Software Engineer in Test (f/m)");
        builder.addTextBody("source", "https://zanox.softgarden.io/vacancies?1");

        HttpEntity reqEntity = builder.build();

        httpPost.setEntity(reqEntity);

        System.out.println("executing request " + httpPost.getRequestLine());

        CloseableHttpResponse response = httpClient.execute(httpPost);

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


    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.constantcontact.com/v2/library/files");

        httppost.addHeader("Authorization", "Bearer 70e8e17d-e1ed-4b7a-8a8a-40383d74d467");
        httppost.addHeader("Accept", "application/json");
        httppost.addHeader("Content-type", "multipart/form-data");

        File fileToUse = new File("/path_to_file/YOLO.jpg"); //e.g. /temp/dinnerplate-special.jpg
        FileBody data = new FileBody(fileToUse);

        String file_type = "JPG";
        String description = "Oppa Gangnam Style";
        String folder_id = "-1";
        String source = "MYCOMPUTER";

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("file_name", new StringBody(fileToUse.getName()));
        reqEntity.addPart("folder_id", new StringBody(folder_id));
        reqEntity.addPart("description", new StringBody(description));
        reqEntity.addPart("source", new StringBody(source));
        reqEntity.addPart("file_type", new StringBody(file_type));
        reqEntity.addPart("data", data);

        httppost.setEntity(reqEntity);

        HttpResponse response = httpClient.execute(httppost);
        System.out.println(response);

        HttpEntity resEntity = response.getEntity();
        System.out.println(resEntity);
        System.out.println(EntityUtils.toString(resEntity));

        EntityUtils.consume(resEntity);
        httpClient.close();
    }

}
