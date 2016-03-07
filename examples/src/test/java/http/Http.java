package http;

import java.io.IOException;

/**
 * Created by Funker on 20.02.2016.
 */
public interface Http {
    String get(String url) throws IOException;

    String post(String url) throws IOException;

}
