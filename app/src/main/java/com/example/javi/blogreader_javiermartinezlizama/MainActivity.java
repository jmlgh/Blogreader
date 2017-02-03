package com.example.javi.blogreader_javiermartinezlizama;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String URL_ENTRADA = "UrlEntradaBlog";
    private String treeHouseUrl = "http://blog.teamtreehouse.com/api/get_recent_summary/?count=";
    private String urlFinal;

    RelativeLayout actMainLayout;

    ListView listaEntradas;
    ArrayList<EntradaBlog>entradas;
    EntradaAdapter adapter;

    OkHttpClient httpClient;
    EntradaBlog entradaBlog;

    String numEntradas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEntradas = (ListView)findViewById(R.id.lista_entradas);
        actMainLayout = (RelativeLayout)findViewById(R.id.act_main_layout);
        actMainLayout.setBackgroundColor(Color.parseColor("#51b46d"));

        Intent i = getIntent();
        numEntradas = i.getStringExtra(WelcomeActivity.ENTRADAS);
        urlFinal = treeHouseUrl + numEntradas;

        init();


    }

    private void init() {
        if(internetDisponible()){
            httpClient = new OkHttpClient();
            Request req = new Request.Builder().url(urlFinal).build();
            Call call = httpClient.newCall(req);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        if(response.isSuccessful()){
                            final String jsonData = response.body().string();
                            //Log.d(TAG, jsonData);
                            JSONObject raiz = new JSONObject(jsonData);
                            if(raiz.getString("status").equals("ok")){
                                entradas = parseEntradaData(jsonData);
                                adapter = new EntradaAdapter(MainActivity.this, entradas);
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       listaEntradas.setAdapter(adapter);
                                       listaEntradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                entradaBlog = (EntradaBlog) listaEntradas.getAdapter().getItem(position);
                                                String urlEntradaMostrar = entradaBlog.getUrl();
                                                Intent i = new Intent(MainActivity.this, WebActivity.class);
                                                i.putExtra(URL_ENTRADA, urlEntradaMostrar);
                                                startActivity(i);
                                           }
                                       });
                                   }
                               });
                            }
                        }
                    }catch (IOException e){
                        Log.e(TAG, getString(R.string.tag_error));
                    }catch (JSONException e){
                        Log.e(TAG, getString(R.string.tag_error));
                    }
                }
            });

        } else {
            Toast.makeText(this, getString(R.string.toast_sin_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<EntradaBlog> parseEntradaData(String data) throws JSONException {
        EntradaBlog entradaBlog = new EntradaBlog();
        entradas = new ArrayList<>();

        JSONObject raiz = new JSONObject(data);
        JSONArray arrayPosts = raiz.getJSONArray("posts");
        JSONObject post;
        for(int i = 0; i < arrayPosts.length(); i++){
            post = arrayPosts.getJSONObject(i);
            entradaBlog = new EntradaBlog();
            entradaBlog.setAutor(post.getString("author"));
            entradaBlog.setTitulo(post.getString("title"));
            entradaBlog.setUrl(post.getString("url"));

            entradas.add(entradaBlog);
        }


        return entradas;
    }

    private boolean internetDisponible() {
        boolean disponible = false;
        ConnectivityManager conManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnected()){
            disponible = true;
        }

        return disponible;
    }


}
