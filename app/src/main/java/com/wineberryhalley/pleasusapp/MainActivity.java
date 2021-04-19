package com.wineberryhalley.pleasusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.wineberryhalley.plesus.Plesus;
import com.wineberryhalley.plesus.data.Winexecutor;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 210);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = ((EditText)findViewById(R.id.edit_)).getText().toString();
                set(txt);
            }
        });


webvi = findViewById(R.id.webvi);
    }

    private WebView webvi;
    private void set(String url){
        webvi.getSettings().setJavaScriptEnabled(true);
        webvi.getSettings().setDomStorageEnabled(true);
        Plesus.get().with(url).extract(new Plesus.PlesusListener() {
            @Override
            public void OnLoad(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.e(TAG, "OnLoad: "+url );
                intent.setDataAndType(Uri.parse(url), "video/*");
                startActivity(Intent.createChooser(intent, "select one"));
            }

            @Override
            public void OnError(String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                });

       //         Log.e(TAG, "OnError: "+Winexecutor.html );

            }
        });

    }
}