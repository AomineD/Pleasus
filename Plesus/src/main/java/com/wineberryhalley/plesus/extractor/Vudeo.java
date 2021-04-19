package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Vudeo {
    //
    public static String getFasterLink(String l, Context ctx){
        String authJSON = SCheck.getCheckString(ctx);
        Document document = null;
    //    Log.e("MAIN", "getFasterLink: "+authJSON);
        String mp4 = null;
        try {
        //    Log.e("MAIN", "getFasterLink: "+l );
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla/5.0 (Linux; Android 10; JNY-LX2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Mobile Safari/537.36")
                    .parser(Parser.htmlParser()).get();

            try{

                Elements scriptElements = document.getElementsByTag("script");

                for (Element element :scriptElements ){
                    for (DataNode node : element.dataNodes()) {
                   //     Log.e("MAIN", "getFasterLink: "+node.getWholeData());
                        if(node.getWholeData().contains("Clappr.Player")){

                            String[] ad = node.getWholeData().split("var player = new Clappr.Player\\(");

                            if(ad.length > 1) {
                                String[] ret = ad[1].split("\\);");


                                String[]aba = ret[0].split("sources: \\[\"");

                               // Log.e("MAIN", "s: "+aba[1] );

                                mp4 = aba[1].split("\"\\],")[0];
                            }else{

                            }

break;
                        }
                    }
                //    Log.e("MAIN", "-----------------------" );
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
