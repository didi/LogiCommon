package com.didiglobal.logi.log.common.web.http;

import org.apache.http.client.methods.HttpPost;

import java.net.URI;

/**
 * @author jinbinbin
 * @version $Id: TunnelHttpPost.java, v 0.1 2018年03月05日 22:13 jinbinbin Exp $
 */
public class TunnelHttpPost extends HttpPost {

    public TunnelHttpPost() {
        super();
        FlagHeaderUtils.addFlagHeader(this);
    }

    public TunnelHttpPost(final URI uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(uri);
    }

    /**
     * @param uri uri
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public TunnelHttpPost(final String uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(URI.create(uri));
    }
}
