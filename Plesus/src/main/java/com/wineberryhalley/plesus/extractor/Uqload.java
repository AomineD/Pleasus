package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;
import com.wineberryhalley.plesus.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Uqload {
    //
    public static String getFasterLink(String l, Context ctx){
        String authJSON = SCheck.getCheckString(ctx);
        l = l.contains("/embed-") ? l : "https://uqload.com/embed-" + l.split("/")[3];
        Document document = null;
        Log.e("MAIN", "getFasterLink: "+authJSON);
        String mp4 = null;
        try {
            Log.e("MAIN", "getFasterLink: "+l );
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla/5.0 (Linux; Android 10; JNY-LX2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Mobile Safari/537.36")
                    .parser(Parser.htmlParser()).get();

            try{

                Elements scriptElements = document.getElementsByTag("script");

                for (Element element :scriptElements ){
                    for (DataNode node : element.dataNodes()) {
                     //   Log.e("MAIN", "getFasterLink: "+node.getWholeData() );
                        if(node.getWholeData().contains("Clappr.Player")){

                            String[] ad = node.getWholeData().split("var player = new Clappr.Player\\(");

                            if(ad.length > 1) {
                                String[] ret = ad[1].split("\\);");

                                JSONObject object = new JSONObject(ret[0]);

                                Log.e("MAIN", "getFasterLink: " + object.toString());

                                if(object.has("sources")){
                                    JSONArray a = object.getJSONArray("sources");
                                    mp4 = a.getString(0);
                                }
                            }else{

                            }


                        }
                    }
                    Log.e("MAIN", "-----------------------" );
                }

            }catch (Exception er){
                Log.e("MAIN", "getFasterLink2: "+er.getMessage() );
            }
        } catch (Exception e) {
            Log.e("MAIN", "getFasterLink: "+e.getMessage() );
        }

        return mp4;
    }
}
