package com.wineberryhalley.plesus.data;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MixDropAp {
    private String as = "https://pelistorrent.icu/epi/lib/test.php";

    public interface OnLoadListener{
        void OnLoad(String response);
        void OnError(String erno);
    }

    private String TAG ="MAIN";
    public void get(String encontro, OnLoadListener listener){
   //     Log.e(TAG, "get: "+encontro);

        RequestQueue queue = Volley.newRequestQueue(WineProvider.context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, as, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
         //       Log.e(TAG, "onResponse: as "+response );
                try {

                    String[] spl = response.split("MDCore.wurl=\"");
                    String[] at = spl[1].split("\";MDCore");

                    String finlUrl = "https:"+at[0];
                    listener.OnLoad(finlUrl);
                    if(Winexecutor.plesusListene != null){
                        Winexecutor.plesusListene.OnLoad(finlUrl);
                    }
                }catch (Exception e){
                    if(Winexecutor.plesusListene != null){
                        Winexecutor.plesusListene.OnError(e.getMessage());
                    }
                    listener.OnError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.OnError(error.getMessage());
                if(Winexecutor.plesusListene != null){
                    Winexecutor.plesusListene.OnError(error.getMessage());
                }
               // Log.e(TAG, "onErrorResponse: "+error.getMessage() );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> das = new HashMap<>();
                das.put("test", encontro);
                return das;
            }
        };

        queue.add(stringRequest);
    }

}
