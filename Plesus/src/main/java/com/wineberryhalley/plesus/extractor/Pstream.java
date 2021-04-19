package com.wineberryhalley.plesus.extractor;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.htetznaing.lowcostvideo.LowCostVideo;
import com.htetznaing.lowcostvideo.Model.XModel;
import com.htetznaing.lowcostvideo.Utils.Utils;
import com.wineberryhalley.plesus.util.Conses;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pstream {
    public static String getFasterLink(String url) {
        url = url.replaceAll(".net\\/.*\\/", ".net/e/");
        Log.e("MAIN", "fetch: " + url);
        Document document = null;
        String mp4 = null;
        try {
            document = Jsoup.connect(url)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla")
                    .parser(Parser.htmlParser()).get();

            try {

                return document.toString();

            } catch (Exception er) {
                Log.e(TAG, "getFasterLink: " + er.getMessage());
            }
        } catch (Exception e) {
            Log.e(TAG, "getFasterLink: " + e.getMessage());
        }

        return "";

    }


    private static String TAG ="MAIN";

    private static ArrayList<XModel> parseVideo(String string){
        ArrayList<XModel> xModels = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(string);
            Iterator<String> keys = object.keys();
            while (keys.hasNext()){
                String key = keys.next();
                XModel xModel = new XModel();
                xModel.setQuality(key+"p");
                xModel.setUrl(object.getString(key));
                xModels.add(xModel);
            }
            return xModels;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAPI(String html){
        final String regex = "vsuri =(.*)'";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String url = matcher.group(1);
            return url.substring(url.indexOf("http"));
        }
        return null;
    }
}
