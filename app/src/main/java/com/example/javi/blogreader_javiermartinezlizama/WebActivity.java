package com.example.javi.blogreader_javiermartinezlizama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    WebView wvEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wvEntrada = (WebView)findViewById(R.id.wv_entrada);

        Intent i = getIntent();
        String urlEntrada = i.getStringExtra(MainActivity.URL_ENTRADA);

        wvEntrada.loadUrl(urlEntrada);
    }
}
