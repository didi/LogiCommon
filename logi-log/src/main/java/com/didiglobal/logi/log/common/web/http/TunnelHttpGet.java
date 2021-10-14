package com.didiglobal.logi.log.common.web.http;

import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * @author jinbinbin
 * @version $Id: TunnelHttpGet.java, v 0.1 2018年03月05日 22:13 jinbinbin Exp $
 */
public class TunnelHttpGet extends HttpGet {

    public TunnelHttpGet() {
        super();
        FlagHeaderUtils.addFlagHeader(this);
    }

    public TunnelHttpGet(final URI uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(uri);
    }

    /**
     * @param uri uri
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public TunnelHttpGet(final String uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(URI.create(uri));
    }
}
