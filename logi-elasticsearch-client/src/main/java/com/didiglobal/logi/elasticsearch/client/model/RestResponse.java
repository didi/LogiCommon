package com.didiglobal.logi.elasticsearch.client.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;

public class RestResponse {

    private String esVersion;

    private Response response;

    private String content = "";

    public String getEsVersion() {
        return esVersion;
    }

    public void setEsVersion(String esVersion) {
        this.esVersion = esVersion;
    }

    public RestResponse(Response response) {
        this.response = response;
    }

    public RestResponse(String esVersion, Response response) {
        this.esVersion = esVersion;
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public String getResponseContent() {
        String content = null;
        try {
            if (response.getEntity() == null) {
                return null;
            }

            content = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            content = "{}";
        }
        this.content = content;
        return content;
    }

    public InputStream getResponseStream() throws IOException {
        return response.getEntity().getContent();
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getContent() {
        return this.content;
    }

}
