package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class vercotizacion extends AppCompatActivity {

String pedidoverurl;
//String enviarpedidocorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpdfcotiza);
        pedidoverurl=listaConta.pedidoverpdf;

        Intent intent = new Intent();
        intent.setDataAndType(Uri.parse(appRutaservidor.IP+"/cotizacion.php?cotiza="+pedidoverurl), "application/pdf");
        startActivity(intent);


        listaConta.opclist.clear();
        listaConta.listacont.clear();
        listaConta.precioXtapete.clear();
        finish();

    }


}
