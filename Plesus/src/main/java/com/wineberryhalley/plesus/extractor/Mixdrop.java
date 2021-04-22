package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.Plesus;
import com.wineberryhalley.plesus.data.MixDropAp;
import com.wineberryhalley.plesus.data.Winexecutor;
import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;
import com.wineberryhalley.plesus.util.Utils;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mixdrop {
    public static String getFasterLink(String l, Context ctx){
        String authJSON = SCheck.getCheckString(ctx);
        l = l.replace("/f/", "/e/");
        Document document = null;
        String headers = "Referer@" + l;
        Map<String, String> mapHeaders = new HashMap<>();
        ArrayList<String> hd = new ArrayList<>(Arrays.asList(headers.split("@")));

        for (int i = 0; i < hd.size(); i++){
            if(i % 2 == 0)
                mapHeaders.put(hd.get(i), hd.get(i+1));
        }

        String mp4 = null;
        try {
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .headers(mapHeaders)
                    .parser(Parser.htmlParser()).get();

            if (document == null || !document.toString().contains("eval(")){
                if (document != null){
                    Pattern p = Pattern.compile("window.location\\s*=\\s*\"(.*?)\"", Pattern.DOTALL);
                    Matcher m = p.matcher(document.toString());

                    if(m.find()) {
                        String token = m.group(1);
                        if (token != null && !token.isEmpty()){
                            l = l.split("/e/")[0] + token;
                            document = Jsoup.connect(l)
                                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                                    .headers(mapHeaders)
                                    .parser(Parser.htmlParser()).get();
                        }
                    }
                }
            }

            Elements elements = document.body().getElementsByTag("script");

        //    Log.e("MAIN", "getFasterLink: "+document.body() );
            String htm = "";

            for (Element e:
                 elements) {
                for (DataNode node:
                     e.dataNodes()) {
                    if(node.getWholeData().contains("MDCore.ref")){
                     //   Log.e("MAIN", "getFasterLink: ENCONTROOO" );
                        htm = node.getWholeData();
                        break;
                    }
                }


            }

            if(!htm.isEmpty()) {
                MixDropAp mixDropAp = new MixDropAp();

                mixDropAp.get(htm, new MixDropAp.OnLoadListener() {
                    @Override
                    public void OnLoad(String response) {

                    }

                    @Override
                    public void OnError(String erno) {

                    }
                });
            }
        } catch (Exception e) {}

        return mp4;
    }
}
