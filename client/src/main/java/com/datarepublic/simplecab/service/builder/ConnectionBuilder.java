package com.datarepublic.simplecab.service.builder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static java.util.Objects.requireNonNull;

/**
 * @author Alex L.
 */
public class ConnectionBuilder {

    private URL url;

    private HttpURLConnection conn;

    private ConnectionBuilder(){
    }

    private ConnectionBuilder withUrl(final String url) throws MalformedURLException {
        this.url = new URL(url);
        return this;
    }

    private ConnectionBuilder openConnection() throws IOException {
        this.conn =  (HttpURLConnection) url.openConnection();
        return this;
    }

    private ConnectionBuilder withMethod(final String method) throws ProtocolException {
        requireNonNull(conn);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");

        return this;
    }

    private HttpURLConnection getConn() {
        requireNonNull(conn);
        return conn;
    }

    public static HttpURLConnection connect(final String url, final String method) throws IOException {
        return new ConnectionBuilder().withUrl(url).openConnection().withMethod(method).getConn();
    }
}
