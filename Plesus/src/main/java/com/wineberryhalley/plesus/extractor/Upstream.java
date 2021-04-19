package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;
import com.wineberryhalley.plesus.util.Utils;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public class Upstream {
    private static String TAG ="MAIN";
    public static String getFasterLink(String l, Context ctx){
        String authJSON = SCheck.getCheckString(ctx);
      //  Log.e(TAG, "getFasterLink: "+authJSON );
        l = l.contains("/embed-") ? l : "https://upstream.to/embed-" + l.split("/")[3] + ".html";
  //      Log.e(TAG, "getFasterLink: "+l );
        Document document = null;
        String mp4 = null;
        try {
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla")
                    .parser(Parser.htmlParser()).get();

            try{
                //
                String apiURL = Conses.API_EXTRACTOR + "upstream";
         //       Log.e(TAG, "getFasterLink: "+apiURL );
                String obj = Jsoup.connect(apiURL)
                        .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                        .data("source", Utils.encodeMSG(document.toString()))
                        .data("auth", Utils.encodeMSG(authJSON))
                        .method(Connection.Method.POST)
                        .ignoreContentType(true)
                        .execute().body();

                if(obj != null && obj.contains("url")){
                    JSONObject json = new JSONObject(obj);

                    if (json.getString("status").equals("ok"))
                        mp4 = json.getString("url");
                }
            }catch (Exception er){
                er.printStackTrace();
             //   Log.e(TAG, "getFasterLink exc2: "+er.getMessage() );
            }
        } catch (Exception e) {
       //     Log.e(TAG, "getFasterLinkexc: "+e.getMessage() );
        }


        return mp4;
    }
}
