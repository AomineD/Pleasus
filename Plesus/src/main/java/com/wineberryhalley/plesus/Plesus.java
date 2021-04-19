package com.wineberryhalley.plesus;

import com.wineberryhalley.plesus.data.Winexecutor;

public class Plesus {

    protected Plesus(){

    }


    public static Plesus get(){
        return new Plesus();
    }


    String urlX="";
    public Plesus with(String url){
        urlX = url;
        return this;
    }

    public void extract(PlesusListener plesusListener){

        Winexecutor winexecutor = new Winexecutor(urlX, plesusListener);

        winexecutor.execute();
    }



    public interface PlesusListener{
        void OnLoad(String url);
        void OnError(String error);
    }

}
