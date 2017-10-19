package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.service.builder.ConnectionBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Set;

import static java.lang.String.format;
import static java.net.URLEncoder.encode;
import static java.util.Objects.requireNonNull;

/**
 * @author Alex L.
 */
public class SimpleCabServiceImpl implements SimpleCabService {

    private static final String charset = "UTF-8";

    private static final String DELETE = "DELETE";

    private static final String GET = "GET";

    private final String defaultUrl;

    public SimpleCabServiceImpl(String defaultUrl) {
        requireNonNull(defaultUrl);
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void deleteCache() throws IOException {
        triggerEndpoint(defaultUrl, DELETE);
    }

    @Override
    public void getMedallionsSummary(String pickupDate, Set<String> medallions) throws UnsupportedEncodingException {
        getMedallionsSummary(pickupDate, false, medallions);
    }

    @Override
    public void getMedallionsSummary(String pickupDate, boolean ignoreCache, Set<String> medallions) throws UnsupportedEncodingException {
        StringBuilder query = new StringBuilder(format("pickupDatetime=%s",
            encode(pickupDate, charset)));
        for (String medallion : medallions) {
            query.append("&").append(format("medallion=%s", encode(medallion, charset)));
        }
        query.append("&").append(format("ignoreCache=%s", encode(String.valueOf(ignoreCache), charset)));
        triggerEndpoint(defaultUrl + "?" + query.toString(), GET);
    }


    private void triggerEndpoint(final String url, final String method) {
        try {
            HttpURLConnection conn = ConnectionBuilder.connect(url, method);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
