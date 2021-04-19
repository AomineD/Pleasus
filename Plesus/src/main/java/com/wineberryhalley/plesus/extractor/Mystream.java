package com.wineberryhalley.plesus.extractor;

import android.content.Context;
import android.util.Log;

import com.wineberryhalley.plesus.data.HighScore;
import com.wineberryhalley.plesus.util.Conses;
import com.wineberryhalley.plesus.util.SCheck;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Mystream {
    //
    public static String getFasterLink(String l, Context ctx){
    //    Log.e("MAIN", "getFasterLink: "+authJSON);
        Document document = null;
        String mp4 = null;
        try {
          //  Log.e("MAIN", "getFasterLink: "+l );
            document = Jsoup.connect(l)
                    .timeout(Conses.TIMEOUT_EXTRACT_MILS)
                    .userAgent("Mozilla/5.0 (Linux; Android 10; JNY-LX2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Mobile Safari/537.36")
                    .parser(Parser.htmlParser()).get();
            try{

      //          Log.e("MAIN", "getFasterLink: "+document.html() );
            }catch (Exception er){
                Log.e("MAIN", "getFasterLink2: "+er.getMessage() );
            }
        } catch (Exception e) {
            Log.e("MAIN", "getFasterLink: "+e.getMessage() );
        }

        return mp4;
    }
}
