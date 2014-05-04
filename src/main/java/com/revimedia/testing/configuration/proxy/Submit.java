package com.revimedia.testing.configuration.proxy;

import com.revimedia.testing.configuration.response.Response;

/**
 * Created by Funker on 04.05.14.
 */
public class Submit {
    private String request;
    private Response response;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
