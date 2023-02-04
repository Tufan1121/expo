package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import static com.tufan.sistemas1.com.expotufan.LoginActivity.idusuariotufan;

public class pedidos extends AppCompatActivity implements View.OnClickListener{
Button pasaclintesnuevos;
Button pasaclientesexistentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        pasaclintesnuevos= findViewById(R.id.btn_nuevocliente);
        pasaclientesexistentes= findViewById(R.id.btn_clienteexistente);

        pasaclientesexistentes.setOnClickListener(this);
        pasaclintesnuevos.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_nuevocliente:
                Intent pasaActivityclientesnuevos = new Intent(this,clientesnuevos.class);
                startActivity(pasaActivityclientesnuevos);
                finish();
                break;
            case R.id.btn_clienteexistente:
                Intent pasaexiste = new Intent(this, cliente_existe.class);
                startActivity(pasaexiste);
                finish();
                break;

            default:

                break;

        }
    }
}
