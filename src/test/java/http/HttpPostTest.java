package http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
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

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);

        MultipartEntity reqEntity = new MultipartEntity();

/*        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
        builder.addBinaryBody("file", new File("..."), ContentType.APPLICATION_OCTET_STREAM, "file.ext");
        HttpEntity multipart = builder.build();*/

        uploadFile.setEntity(multipart);

        response = httpClient.execute(uploadFile);
        responseEntity = response.getEntity();

    }


    public static void main(String[] args) throws IOException {

        HttpClient httpclient = new HttpClient();
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

        HttpResponse response = httpclient.execute(httppost);
        System.out.println(response);

        HttpEntity resEntity = response.getEntity();
        System.out.println(resEntity);
        System.out.println(EntityUtils.toString(resEntity));

        EntityUtils.consume(resEntity);
        httpclient.getConnectionManager().shutdown();
    }

}
