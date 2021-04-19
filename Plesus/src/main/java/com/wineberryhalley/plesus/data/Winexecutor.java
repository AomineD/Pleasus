package com.wineberryhalley.plesus.data;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.htetznaing.lowcostvideo.LowCostVideo;
import com.htetznaing.lowcostvideo.Model.XModel;
import com.wineberryhalley.plesus.Plesus;
import com.wineberryhalley.plesus.extractor.ExtractorCore;
import com.wineberryhalley.plesus.extractor.Pstream;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Winexecutor {
    private String url;
    static Plesus.PlesusListener plesusListene;
    public Winexecutor(String url, Plesus.PlesusListener plesusListener){
        this.url = url;
        plesusListene = plesusListener;
    }


    public void execute(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

              String url2 = ExtractorCore.getFinalURL(url, WineProvider.context);



              if(!url.contains("mixdrop"))
              plesusListene.OnLoad(url2);

                } catch (Exception e){
                    plesusListene.OnError(e.getMessage());
                }


            }
        });

        thread.start();
    }




}
