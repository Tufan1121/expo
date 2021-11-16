package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class estatusPedidosE extends AppCompatActivity implements View.OnClickListener {
    Button botonentregadoexpo;
    Button botonpendientepago;
    Button botonpagadoforaneo;
    Button botonpagadorecogertienda;
    Button botonpedidoseliminados;
    Button botontodoslospedidos;
    Button botoneditpedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatus_pedidos);

        botonentregadoexpo=(Button)findViewById(R.id.btn_entregadoexpo);
        botonpendientepago=(Button)findViewById(R.id.btn_pendiente_pago);
        botonpagadoforaneo=(Button)findViewById(R.id.btn_pagado_foraneo);
        botonpagadorecogertienda=(Button)findViewById(R.id.btn_pagado_tienda);
        botonpedidoseliminados=(Button)findViewById(R.id.btn_eliminados);
        botontodoslospedidos=(Button)findViewById(R.id.btn_pedidostodos);
        botoneditpedidos=(Button)findViewById(R.id.btn_modificarpedidoxd2);

        botonentregadoexpo.setOnClickListener(this);
        botonpendientepago.setOnClickListener(this);
        botonpagadoforaneo.setOnClickListener(this);
        botonpagadorecogertienda.setOnClickListener(this);
        botonpedidoseliminados.setOnClickListener(this);
        botontodoslospedidos.setOnClickListener(this);
        botoneditpedidos.setOnClickListener(this);

        botonentregadoexpo.setEnabled(false);
        botonpendientepago.setEnabled(false);
        botonpagadoforaneo.setEnabled(false);
        botonpagadorecogertienda.setEnabled(false);

        botonentregadoexpo.setVisibility(View.INVISIBLE);
        botonpendientepago.setVisibility(View.INVISIBLE);
        botonpagadoforaneo.setVisibility(View.INVISIBLE);
        botonpagadorecogertienda.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_entregadoexpo:
                //todo Boton Entragado en expo... deshabilutado
                Intent pasapedidoentregaexpo =new Intent(this , entrega_estatus_pedido.class);
                startActivity(pasapedidoentregaexpo);
                break;
            case R.id.btn_pendiente_pago:
                Intent pasapedidoanticipo = new Intent(this,entrega_estatus_pedido_anticipo.class);
                startActivity(pasapedidoanticipo);
                break;
            case R.id.btn_pagado_foraneo:
                Intent pasapedidoforaneo= new Intent(this,entrega_estatus_pedido_foraneo.class);
                startActivity(pasapedidoforaneo);
                break;
            case R.id.btn_pagado_tienda:
                Intent pasapedidotienda = new Intent(this,entrega_estatus_pedido_tienda.class);
                startActivity(pasapedidotienda);
                break;
            case R.id.btn_eliminados:
                //Todo... Pedidos eliminados consulta....
                Toast.makeText(this,"NO DISPONIBLE",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pedidostodos:
                //TODO... Pedidos ver y generar pdf para enviar...
                Intent pasaatodos = new Intent(this,pedidoshechos.class);
                startActivity(pasaatodos);
                break;
            case  R.id.btn_modificarpedidoxd:
                //TODO... Modificacion de pedidos
                Intent pasaamodificarpedido= new Intent(this,ver_pedidos_hechos_modificar.class);
                startActivity(pasaamodificarpedido);
                break;
            default:

                break;


        }
    }
}
