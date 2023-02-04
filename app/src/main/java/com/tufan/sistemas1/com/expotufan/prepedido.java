package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class prepedido extends AppCompatActivity {
Button redireccionarinv;
Spinner selectestatuspedido;
String xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepedido);
        Intent intento=getIntent();
        Bundle extras = intento.getExtras();
        //String dato;
        if(extras != null) {
            xx = extras.getString("DATO");
        }

        redireccionarinv= findViewById(R.id.btn_donde);
        selectestatuspedido= findViewById(R.id.spinner_donde);


        redireccionarinv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Estatus del Pedido Con Inventario en Bodegas")){

                    Toast.makeText(prepedido.this, "Selecciona donde se va a entregar el pedido",Toast.LENGTH_LONG).show();
                    selectestatuspedido.setFocusable(true);
                    selectestatuspedido.setFocusable(true);
                    selectestatuspedido.setFocusableInTouchMode(true);
                    selectestatuspedido.requestFocus();

                }else if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("01 Entregado en EXPO")){
                          listaConta.estatusselect="1";
                    Toast.makeText(prepedido.this,"se va a ir a inventario de expo ",Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(prepedido.this,listatapetes.class);
                    intento.putExtra("DATO",xx);
                    startActivity(intento);
                    finish();
                }else{
                    Toast.makeText(prepedido.this,"se va a ir a inventario de bodegas ",Toast.LENGTH_SHORT).show();

                    if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("02 Pendiente Pago (Anticipo)")){
                        listaConta.estatusselect="2";
                        Intent intento = new Intent(prepedido.this,listatapetes2.class);
                        intento.putExtra("DATO",xx);
                        startActivity(intento);
                        finish();
                    }
                    if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("03 Pagado Foráneo")){
                        listaConta.estatusselect="3";
                        Intent intento = new Intent(prepedido.this,listatapetes2.class);
                        intento.putExtra("DATO",xx);
                        startActivity(intento);
                        finish();
                    }
                    if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("04 Pagado (Recoger en tienda)")){
                        listaConta.estatusselect="4";
                        Intent intento = new Intent(prepedido.this,listatapetes2.class);
                        intento.putExtra("DATO",xx);
                        startActivity(intento);
                        finish();
                    }

                    if(selectestatuspedido.getSelectedItem().toString().trim().equalsIgnoreCase("Cotización")){
                       listaConta.estatusselect="8";
                       Intent intento = new Intent(prepedido.this,listatapetes3.class);
                       intento.putExtra("DATO",xx);
                       startActivity(intento);
                       finish();
                    }

                    /*Intent intento = new Intent(prepedido.this,listatapetes.class);
                    intento.putExtra("DATO",xx);
                    startActivity(intento);
*/
                }

            }
        });

    }
}
