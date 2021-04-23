package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.Plesus;

public class ExtractorCore {
    public static void getFinalURL(String url, Context context, Plesus.PlesusListener plesusListene){
        String finalURL = null;
        if(url.contains("mystream")){
            finalURL = Mystream.getFasterLink(url, context);
        }
        if (url.contains("clipwatching"))
            finalURL = Clipwatching.getFasterLink(url, context);
        else if (url.contains("cloudvideo"))
            finalURL = Cloudvideo.getFasterLink(url, context);
        else if (url.contains("dood"))
            finalURL = Dood.getFasterLink(url, context);
        else if (url.contains("fembed") || url.contains("pelispng.online"))
            finalURL = Fembed.getFasterLink(url, context);
        else if (url.contains("jawcloud"))
            finalURL = Jawcloud.getFasterLink(url, context);
        else if (url.contains("jetload"))
            finalURL = Jetload.getFasterLink(url, context);
        else if (url.contains("mixdrop"))
            finalURL = Mixdrop.getFasterLink(url, context);
        else if (url.contains("streamsb.net"))
            finalURL = StreamSB.getFasterLink(url);
        else if (url.contains("mp4upload"))
            finalURL = Mp4upload.getFasterLink(url, context);
        else if (url.contains("openplay"))
            finalURL = Openplay.getFasterLink(url, context);
        else if (url.contains("prostream"))
            finalURL = Prostream.getFasterLink(url, context);
        else if (url.contains("streamtape")) {
           // Log.e("MAIN", "getFinalURL: si" );
            finalURL = Streamtape.getFasterLink(url, context);


            finalURL = Streamtape.getUrl(finalURL);
        //    Log.e("MAIN", "getFinalURL: si "+finalURL );

        }
        else if (url.contains("supervideo"))
            finalURL = Supervideo.getFasterLink(url, context);
        else if (url.contains("upstream"))
            finalURL = Upstream.getFasterLink(url, context);
        else if (url.contains("uptostream"))
            finalURL = Uptostream.getFasterLink(url, context);
        else if (url.contains("uqload"))
            finalURL = Uqload.getFasterLink(url, context);
        else if( url.contains("vudeo"))
            finalURL = Vudeo.getFasterLink(url, context);
        else if (url.contains("veoh"))
            finalURL = Veoh.getFasterLink(url, context);
        else if (url.contains("vidcloud"))
            finalURL = Vidcloud.getFasterLink(url, context);
        else if (url.contains("videobin"))
            finalURL = Videobin.getFasterLink(url, context);
        else if (url.contains("videomega"))
            finalURL = Videomega.getFasterLink(url, context);
        else if (url.contains("vidfast"))
            finalURL = Vidfast.getFasterLink(url, context);
        else if (url.contains("vidia"))
            finalURL = Vidia.getFasterLink(url, context);
        else if (url.contains("vidlox"))
            finalURL = Vidlox.getFasterLink(url, context);
        else if (url.contains("vidoza"))
            finalURL = Vidoza.getFasterLink(url, context);
        else if (url.contains("vup"))
            finalURL = Vup.getFasterLink(url, context);
        else{
            if(finalURL == null){
                finalURL = url;
            }
        }

    //    Log.e("MAIN", "getFinalURL: final is "+finalURL );


if(!url.contains("mixdrop") && !url.contains("streamsb.net")) {
  //  Log.e("MAIN", "getFinalURL: "+finalURL );
    plesusListene.OnLoad(finalURL);

}
    }
}
