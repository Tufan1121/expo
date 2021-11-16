package com.tufan.sistemas1.com.expotufan;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.widget.ProgressBar;
import android.animation.ObjectAnimator;
import android.widget.Toast;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.view.inputmethod.InputMethodManager;

import android.content.ClipboardManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button soloprecios;
    Button pedidos;
    Button verpedidoshechos;
    Button salir;
    //Button cotiza;
    String iduser;
    Button visita;
    Button inventariotodo;
    Button reporte_ventas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        soloprecios=(Button)findViewById(R.id.btn_precio);
        pedidos=(Button)findViewById(R.id.btn_pedido);
        verpedidoshechos=(Button)findViewById(R.id.btn_ver_pedidos);
        salir=(Button)findViewById(R.id.tbn_salir);
        visita=(Button)findViewById(R.id.visitante);
        inventariotodo=(Button)findViewById(R.id.btn_inventario_todo);
        //cotiza=(Button)findViewById(R.id.btn_cotizar);
        reporte_ventas=(Button)findViewById(R.id.btn_reporte_ventas);


        soloprecios.setOnClickListener(this);
        pedidos.setOnClickListener(this);
        verpedidoshechos.setOnClickListener(this);
        salir.setOnClickListener(this);
        visita.setOnClickListener(this);
        inventariotodo.setOnClickListener(this);
       // cotiza.setOnClickListener(this);
        reporte_ventas.setOnClickListener(this);
        //Toast.makeText(this,"nombre expo"+listaConta.nombre_Expo,Toast.LENGTH_SHORT).show();

        leertxt();

    }

   public void leertxt(){

      String pass = "";
      String expo="";
      String idexpo="";
      String encabezado="";
      String almacen1="";
       String idusuario="";

       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("meminterna1.txt")));

           pass = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }

       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("meminterna2.txt")));

           expo = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }
       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("idexpo.txt")));

           idexpo = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }
       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("encabezadoexpo.txt")));

           encabezado = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }
       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("almacen1.txt")));

           almacen1 = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }
       try
       {
           BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("idusuario.txt")));

            idusuario = fin.readLine();
           fin.close();

       }catch (Exception ex)
       {
           Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
       }

       listaConta.almacen1=almacen1;
       listaConta.idusuariotufan=idusuario;
       listaConta.encabrzadopedido=encabezado;
       listaConta.nombre_Expo=expo;
       listaConta.idusuariotufan=idusuario;


   }

    @Override
    protected void onPause() {
        super.onPause();



    }

    @Override
    protected void onRestart() {
        super.onRestart();

        leertxt();
        //Toast.makeText(this,"estas reiniciando wey....id de la expo"+listaConta.id_expo,Toast.LENGTH_LONG).show();
        //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
        //+-+-+-REINICIAMOS LOS DATOS DEL LOS PEDIDOS
        listaConta.listacont.clear();
        listaConta.precioXtapete.clear();
        listaConta.precioXtapeteEx.clear();
        listaConta.precioXtapeteMa.clear();
        listaConta.descripciontapete.clear();
        listaConta.opclist.clear();
        listaConta.imgserver.clear();
        listaConta.clavetapete.clear();
        listaConta.medidastapete.clear();
        listaConta.precioseleccionado.clear();
        listaConta.clavelarga.clear();
        listaConta.listahm.clear();
        listaConta.porciento.clear();
        listaConta.totalpagartodo="";
        listaConta.idclientesp="";
        listaConta.correoxcliente="";

    }

  //  private void HideKeyboard(View v) {
  //      InputMethodManager teclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  //      teclado.hideSoftInputFromWindow(v.getWindowToken(), 0);
  //  }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pedido:
                //TODO... PARA HACER PEDIDOS
                Intent intentpedido = new Intent(this,pedidos.class);
                //intentpedido.putExtra("IDUSER",iduser);
                startActivity(intentpedido);
                break;
            case R.id.btn_precio:
                //todo para escanear precios...
                Intent intentprecio= new Intent(this,solo_precios.class);
                startActivity(intentprecio);

                break;
            case R.id.btn_ver_pedidos:
                //TODO... PARA VER PEDIDOS REALIZADOS
                Intent intentverpedidos = new Intent(this, estatusPedidos.class);
                startActivity(intentverpedidos);
                break;
            case R.id.visitante:
                //TODO... PARA ESCANEAR DATOS DE CLIENTE DEPENDIENDO DE QR
               // Toast.makeText(this,"No disponible",Toast.LENGTH_SHORT).show();

                Intent visitante1 = new Intent(this,visitante.class);
                startActivity(visitante1);

                break;
            case R.id.btn_inventario_todo:
                //TODO..... PARA VER INVENTARIO DE BODEGAS Y DE EXPO
                Intent pasainvenratio= new Intent(this,inventario.class);
                startActivity(pasainvenratio);
                break;


            case R.id.btn_reporte_ventas:
                Intent pasareporte = new Intent(this,pre_reporte.class);
                startActivity(pasareporte);

                break;
            case R.id.tbn_salir:

                sesion.cerrarsesion(listaConta.usuariopass);

                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("meminterna1.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("meminterna2.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }


                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("idexpo.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("encabezadoexpo.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("almacen1.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                try
                {
                    OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("idusuario.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                finish();
                break;

                default:
                    break;

        }
    }




}
