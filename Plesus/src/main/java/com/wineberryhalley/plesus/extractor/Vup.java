package com.wineberryhalley.plesus.extractor;

import android.content.Context;

import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;
import com.wineberryhalley.plesus.util.Utils;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;


public class Vup {
    public static String getFasterLink(String l, Context ctx) {
        String authJSON = SCheck.getCheckString(ctx);
        Document document = null;
        String mp4 = null;
        try {
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla")
                    .parser(Parser.htmlParser()).get();

            try{
                //
                String apiURL = Conses.API_EXTRACTOR + "vup";
                String obj = Jsoup.connect(apiURL)
                        .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                        .data("mode", "local")
                        .data("auth", Utils.encodeMSG(authJSON))
                        .data("source", Utils.encodeMSG(document.toString()))
                        .method(Connection.Method.POST)
                        .ignoreContentType(true)
                        .execute().body();

                if(obj != null && obj.contains("url")){
                    JSONObject json = new JSONObject(obj);

                    if (json.getString("status").equals("ok"))
                        mp4 = json.getString("url");
                }
            }catch (Exception er){
            }
        } catch (Exception e) {

        }

        return mp4;
    }

}
