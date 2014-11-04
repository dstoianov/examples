package tipsandtricks.chromelogging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebPageTest {

    //https://sites.google.com/a/webpagetest.org/docs/private-instances
    //http://andydavies.me/blog/2012/09/18/how-to-create-an-all-in-one-webpagetest-private-instance/
    //https://www.youtube.com/watch?v=0_kAPWSZNY4
    // example of test result page http://www.webpagetest.org/result/130124_WN_GFX/1/details/

    private final String location;
    private final URL createTestUrl;
    private final URL workDoneUrl;
    private final String mimeBoundary = "-----CorrectBatteryHorseStaple";

    public WebPageTest(URL baseUrl, String location, String testUrl) throws IOException {
        this.location = location;
        this.createTestUrl = new URL(baseUrl, "runtest.php?location=" + location + "&url=" + URLEncoder.encode(testUrl, "UTF-8") + "&fvonly=1&f=json");
        this.workDoneUrl = new URL(baseUrl, "work/workdone.php");
    }

    public String submitResult(JSONArray devToolsLog, byte[] screenshot) throws IOException, JSONException {
        JSONObject testDescriptor = createTest();
        postResult(testDescriptor, devToolsLog, screenshot);
        return testDescriptor.getJSONObject("data").getString("userUrl");
    }

    private void writeResultsZip(OutputStream os, JSONArray devToolsLog, byte[] screenshot) throws IOException, JSONException {
        ZipOutputStream zos = new ZipOutputStream(os);
        if (null != devToolsLog) {
            zos.putNextEntry(new ZipEntry("1_devtools.json"));
            OutputStreamWriter writer = new OutputStreamWriter(zos);
            devToolsLog.write(writer);
            writer.flush();
            zos.closeEntry();
        }
        if (null != screenshot) {
            zos.putNextEntry(new ZipEntry("1_screen.png"));
            zos.write(screenshot);
            zos.closeEntry();
        }
        zos.finish();
    }

    private JSONObject createTest() throws IOException, JSONException {
        HttpURLConnection http = (HttpURLConnection) createTestUrl.openConnection();
        if (HttpURLConnection.HTTP_OK != http.getResponseCode()) {
            throw new IOException("WebPateTest test creation failed for location " + location + ": " +
                    http.getResponseCode() + " " + http.getResponseMessage());
        }
        Reader reader = new InputStreamReader(http.getInputStream(), "UTF-8");
        try {
//            return new JSONObject(new JSONTokener(reader));

            JSONObject result = new JSONObject(new JSONTokener(reader));
            if (!result.has("data")) {
                throw new IOException("WebPageTest test creation failed for location " + location + ": " + result);
            }
            return result;
        } finally {
            reader.close();
        }
    }

    private void postResult(JSONObject testDescriptor, JSONArray devToolsLog, byte[] screenshot) throws IOException, JSONException {
        HttpURLConnection http = (HttpURLConnection) workDoneUrl.openConnection();
        http.setDoOutput(true);
        http.addRequestProperty("Content-Type", "multipart/form-data; boundary=" +
                mimeBoundary);
        http.setChunkedStreamingMode(4096);
        writeMultipartContent(http.getOutputStream(), testDescriptor, devToolsLog, screenshot);
        http.getInputStream().close();
        if (HttpURLConnection.HTTP_OK != http.getResponseCode()) {
            throw new IOException("Result submission failed for " +
                    testDescriptor.getJSONObject("data").getString("userUrl") + " : " +
                    http.getResponseCode() + " " + http.getResponseMessage());
        }
    }

    private void writeMultipartContent(OutputStream os, JSONObject testDescriptor, JSONArray devToolsLog, byte[] screenshot) throws IOException, JSONException {
        try {
            String testId = testDescriptor.getJSONObject("data").getString("testId");
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
            startMimePart(writer, "location", null);
            writer.write("\r\n" + location + "\r\n");
            startMimePart(writer, "id", null);
            writer.write("\r\n" + testId + "\r\n");
            startMimePart(writer, "done", null);
            writer.write("\r\n1\r\n");
            startMimePart(writer, "file", "1_results.zip");
            writer.write("Content-Type: application/zip\r\n");
            writer.write("Content-Transfer-Encoding: binary\r\n\r\n");
            writer.flush();
            writeResultsZip(os, devToolsLog, screenshot);
            writer.write("\r\n--");
            writer.write(mimeBoundary);
            writer.write("--\r\n");
            writer.flush();
        } catch (JSONException e) {
            System.err.println(testDescriptor.get("statusText").toString());
            System.err.println(e.toString());
        }
    }

    private void startMimePart(Writer writer, String name, String filename) throws IOException {
        writer.write("--");
        writer.write(mimeBoundary);
        writer.write("\r\n");
        writer.write("Content-Disposition: form-data; name=\"");
        writer.write(name);
        if (null != filename) {
            writer.write("\"; filename=\"");
            writer.write(filename);
        }
        writer.write("\"\r\n");
    }
}