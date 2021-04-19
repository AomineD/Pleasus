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

public class Streamtape
{
    public static String getFasterLink(String l, Context ctx){
        String authJSON = SCheck.getCheckString(ctx);
        Document document = null;
        String mp4 = null;
        try {
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla")
                    .parser(Parser.htmlParser()).get();

            try{

                return document.toString();

            }catch (Exception er){

            }
        } catch (Exception e) {

        }

        return mp4;
    }


    public static String getUrl(String finalURL) {
        String[] html = finalURL.split("innerHTML");
        if (html.length > 3) {


            String[] mem = html[3].split("';</script>");

            //Log.e("MAIN", "getUrl: " + mem[0]);


            String tra = mem[0].replace(" = \"//", "");
            tra = tra.replace("\" + '", "");

       //     Log.e("MAIN", "getUrl:"+tra);


            return "https://"+tra;
        }else{
            return "";
        }
    }
}
