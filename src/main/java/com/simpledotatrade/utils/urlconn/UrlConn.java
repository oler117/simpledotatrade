package com.simpledotatrade.utils.urlconn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by oler on 10.02.2015.
 */
public class UrlConn {

    public static String callURL(String myURL) throws IOException {

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        URL url = new URL(myURL);
        urlConn = url.openConnection();
        if (urlConn != null)
            urlConn.setReadTimeout(60 * 1000);
        if (urlConn != null && urlConn.getInputStream() != null) {
            in = new InputStreamReader(urlConn.getInputStream(),
                    Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(in);
            if (bufferedReader != null) {
                int cp;
                while ((cp = bufferedReader.read()) != -1) {
                    sb.append((char) cp);
                }
                bufferedReader.close();
            }
        }
        in.close();

        return sb.toString();
    }

}
