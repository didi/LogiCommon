package com.didiglobal.logi.log.common.web.http;

import org.apache.http.client.methods.HttpPut;

import java.net.URI;

/**
 * @author jinbinbin
 * @version $Id: TunnelHttpPut.java, v 0.1 2018年03月05日 22:13 jinbinbin Exp $
 */
public class TunnelHttpPut extends HttpPut {

    public TunnelHttpPut() {
        super();
        FlagHeaderUtils.addFlagHeader(this);
    }

    public TunnelHttpPut(final URI uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(uri);
    }

    /**
     * @param uri uri
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public TunnelHttpPut(final String uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(URI.create(uri));
    }
}
