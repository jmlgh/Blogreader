package com.example.javi.blogreader_javiermartinezlizama;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public final static String ENTRADAS = "entradas";

    RelativeLayout actWelcomeLayout;
    EditText etEntradas;
    Button btnLeer;

    String numeroEntradas = "";
    int iNumeroEntradas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        actWelcomeLayout = (RelativeLayout)findViewById(R.id.act_welcome_layout);
        etEntradas = (EditText)findViewById(R.id.etEntradas);
        btnLeer = (Button)findViewById(R.id.btnLeer);

        actWelcomeLayout.setBackgroundColor(Color.parseColor("#51b46d"));

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroEntradas = etEntradas.getText().toString();
                try{
                    iNumeroEntradas = Integer.parseInt(numeroEntradas);
                    if(numeroEntradas.equals("")){
                        mostrarToastNumeroValido();
                    }
                    else if(iNumeroEntradas <= 0){
                        mostrarToastNumeroValido();
                    }
                    else{
                        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);

                        i.putExtra(ENTRADAS, String.valueOf(numeroEntradas));
                        startActivity(i);
                    }
                }catch(NumberFormatException e){
                    mostrarToastNumeroValido();
                }
            }
        });
    }

    private void mostrarToastNumeroValido() {
        Toast.makeText(this, getString(R.string.toast_numero_valido), Toast.LENGTH_SHORT).show();
        etEntradas.setText("");
    }
}
