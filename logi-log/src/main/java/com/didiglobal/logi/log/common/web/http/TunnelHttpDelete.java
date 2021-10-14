package com.didiglobal.logi.log.common.web.http;

import org.apache.http.client.methods.HttpDelete;

import java.net.URI;

/**
 * @author jinbinbin
 * @version $Id: TunnelHttpDelete.java, v 0.1 2018年03月05日 22:13 jinbinbin Exp $
 */
public class TunnelHttpDelete extends HttpDelete {

    public TunnelHttpDelete() {
        super();
        FlagHeaderUtils.addFlagHeader(this);
    }

    public TunnelHttpDelete(final URI uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(uri);
    }

    /**
     * @param uri uri
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public TunnelHttpDelete(final String uri) {
        super();
        FlagHeaderUtils.addFlagHeader(this);
        setURI(URI.create(uri));
    }
}
