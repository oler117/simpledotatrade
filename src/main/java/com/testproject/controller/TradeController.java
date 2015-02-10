package com.testproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oles on 10.02.15.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    private static final String DOLLAR_SIGN_CODE = "&#36;";

    @RequestMapping(value = "/{tradeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getTradeAnalysis(@PathVariable String tradeId) {
        System.out.println("Got data: " + tradeId);
        tradeId = tradeId.replaceAll("\\s", "%20");
        System.out.println("\nOutput: \n" + callURL("http://steamcommunity.com/market/priceoverview/?country=US&currency=1&appid=570"
                + "&market_hash_name=" + tradeId));
        return "000";
    }

    private String callURL(String myURL) {
        System.out.println("Requeted URL: " + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }

        return sb.toString();
    }

}
