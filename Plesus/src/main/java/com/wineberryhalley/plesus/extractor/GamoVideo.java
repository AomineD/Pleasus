package com.wineberryhalley.plesus.extractor;

import android.util.Log;

import com.wineberryhalley.plesus.data.UnPacker;
import com.wineberryhalley.plesus.util.Conses;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GamoVideo {
    public static String getFasterLink(String l){


        Document document = null;

        String mp4 = null;
        Log.e("MAIN", "getFasterLink: "+l );
        try {
            Connection.Response r  = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla")
                    .referrer("gamovideo.com")
                    .timeout(6000)
                    .followRedirects(true)
                    .execute();



//

              //  Log.e("MAIN", "getFasterLink: "+r.url() );
            String htm = "";
            if(1==1)
return "";

            Elements elements = document.body().getElementsByTag("script");
            for (Element e:
                    elements) {
                for (DataNode node:
                        e.dataNodes()) {
                    if(node.getWholeData().contains("eval(function")){
                           Log.e("MAIN", "getFasterLink: ENCONTROOO" );
                        htm = node.getWholeData();
                        break;
                    }
                }


            }

            //  Log.e("MAIN", "getFasterLink: "+htm );
            if(!htm.isEmpty()) {
                UnPacker unPacker = new UnPacker();

                unPacker.executePowVideo(htm, new UnPacker.OnLoadListener() {
                    @Override
                    public void OnLoad(String response) {

                    }

                    @Override
                    public void OnError(String erno) {
                        Log.e("MAIN", "OnError: "+erno );
                    }
                });

            }
        } catch (Exception e) {
            Log.e("MAIN", "er: "+e.getMessage() );
            for (StackTraceElement element:
                 e.getStackTrace()) {
                Log.e("MAIN", "getFasterLink: "+element );
            }
        }

        return mp4;
    }


    private static SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }
}
