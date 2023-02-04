package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  pedidoshechos extends AppCompatActivity implements View.OnClickListener {
Button enexpo;
Button fueraexpo;
Button cotizaexpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoshechos);
        enexpo= findViewById(R.id.btn_enexpo);

        //Pone invisible boton pedidos en expo
        //03.02.2022
        //CEB
        enexpo.setVisibility(View.GONE);

        fueraexpo= findViewById(R.id.btn_fueradeexpo);


        cotizaexpo= findViewById(R.id.btn_cotizaciones);

        enexpo.setOnClickListener(this);
        fueraexpo.setOnClickListener(this);
        cotizaexpo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_enexpo:
                Intent pasaenexpos= new Intent(this,ver_pedidos_hechos.class);
                startActivity(pasaenexpos);

                break;
            case R.id.btn_fueradeexpo:
                Intent pasafueraexpo= new Intent(this,ver_pedidos_hechosibodegas.class);
                startActivity(pasafueraexpo);
                break;
            case R.id.btn_cotizaciones:
                Intent pasafueraexpoc= new Intent(this,ver_pedidos_hechosibodegascotiza.class);
                startActivity(pasafueraexpoc);

                break;
                default:
                    break;


        }
    }
}
