package com.didiglobal.logi.log.common.web.http;

import com.didiglobal.logi.log.LogFactory;
import com.didiglobal.logi.log.common.web.WebConstants;
import org.apache.http.client.fluent.Request;

import java.net.URI;

/**
 * @author jinbinbin
 * @version $Id: TunnelRequest.java, v 0.1 2018年03月07日 11:17 jinbinbin Exp $
 */
public class TunnelRequest {

    public static Request Get(final URI uri) {
        Request request = Request.Get(uri);
        return addHeader(request);
    }

    public static Request Get(final String uri) {
        Request request = Request.Get(uri);
        return addHeader(request);
    }

    public static Request Head(final URI uri) {
        Request request = Request.Head(uri);
        return addHeader(request);
    }

    public static Request Head(final String uri) {
        Request request = Request.Head(uri);
        return addHeader(request);
    }

    public static Request Post(final URI uri) {
        Request request = Request.Post(uri);
        return addHeader(request);
    }

    public static Request Post(final String uri) {
        Request request = Request.Post(uri);
        return addHeader(request);
    }

    public static Request Patch(final URI uri) {
        Request request = Request.Patch(uri);
        return addHeader(request);
    }

    public static Request Patch(final String uri) {
        Request request = Request.Patch(uri);
        return addHeader(request);
    }

    public static Request Put(final URI uri) {
        Request request = Request.Put(uri);
        return addHeader(request);
    }

    public static Request Put(final String uri) {
        Request request = Request.Put(uri);
        return addHeader(request);
    }

    public static Request Trace(final URI uri) {
        Request request = Request.Trace(uri);
        return addHeader(request);
    }

    public static Request Trace(final String uri) {
        Request request = Request.Trace(uri);
        return addHeader(request);
    }

    public static Request Delete(final URI uri) {
        Request request = Request.Delete(uri);
        return addHeader(request);
    }

    public static Request Delete(final String uri) {
        Request request = Request.Delete(uri);
        return addHeader(request);
    }

    public static Request Options(final URI uri) {
        Request request = Request.Options(uri);
        return addHeader(request);
    }

    public static Request Options(final String uri) {
        Request request = Request.Options(uri);
        return addHeader(request);
    }

    private static Request addHeader(Request request) {
        String flag = LogFactory.getFlag();
        if (flag == null) {
            return request;
        }
        return request.addHeader(WebConstants.FLAG, flag).addHeader(WebConstants.X_REQUEST_ID, flag);
    }

}
