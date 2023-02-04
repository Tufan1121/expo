package com.tufan.sistemas1.com.expotufan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.fabiomsr.moneytextview.MoneyTextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class listatapetes3 extends AppCompatActivity{
TextView txtpasa;
private Button escanerclaves;
private Button hacerpedido;
private ListView mlista;
public static EditText clavemanual;
private Button btninsert;
private Button invbodegas;
 public static MoneyTextView totalprecioxd;
private  int total=0;

    String xd;
    ArrayList<String> arrayList= new ArrayList<>();
    ArrayList<String> arrayListdes= new ArrayList<>();
    ArrayList<String> arrayListprecio= new ArrayList<>();
    ArrayList<String> arrayListprecioexpo= new ArrayList<>();
    ArrayList<String> arrayListpreciomayoreo= new ArrayList<>();
    ArrayList<String> arratListMedida= new ArrayList<>();

    ArrayList<String> al= new ArrayList<String>();
    ArrayList<String> pesos= new ArrayList<>();


    ObtenerWebService hiloconexion2;
    //String IP = "http://192.168.1.70/notificaciones";
    // Rutas de los Web Services
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idlista2.php";
    String GET_BY_IDib = appRutaservidor.IP  + "/obtener_clave_por_idlista2xclavecorta.php";

    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_clave_por_idlista2CORTA.php";

    String av_descripcion;
    String av_medias;
    String av_precio;
    String av_precioe;
    String av_preciom;
    String av_imagen;
    String av_clavelarga;
    String av_hm;
    String av_porciento;
    int totalprecio;
    String xx;


    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listatapete3);
        final Activity activity=this;
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intento=getIntent();
        Bundle extras = intento.getExtras();
        String dato;

        //pesos.add("0");
        char x;
        txtpasa= findViewById(R.id.txt_pasa);
        escanerclaves= findViewById(R.id.btn_codigoxd);
        hacerpedido= findViewById(R.id.btn_generarpedido);
        mlista= findViewById(R.id.milista);
        totalprecioxd= findViewById(R.id.totalxd);
        btninsert= findViewById(R.id.btn_insertM);
        clavemanual= findViewById(R.id.txtagregarmanual);
        invbodegas= findViewById(R.id.btn_busca_inv_ibodegas);
        //inventarioAll=(Button)findViewById(R.id.btn_inventario);

        if(listaConta.clavetapete==null){


        }else{
            listaConta.totalpago3();
            //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
            mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

            //***************************************************************************************************************************
            SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(mlista, new SwipeListViewTouchListener.OnSwipeCallback() {
                @Override
                public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                    //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                    //arrayList.remove(reverseSortedPositions[0]);
                    //al.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                    listaConta.listacont.remove(reverseSortedPositions[0]);
                    listaConta.opclist.remove(reverseSortedPositions[0]);
                    listaConta.imgserver.remove(reverseSortedPositions[0]);
                    listaConta.clavetapete.remove(reverseSortedPositions[0]);
                    listaConta.medidastapete.remove(reverseSortedPositions[0]);
                    listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                    listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                    listaConta.clavelarga.remove(reverseSortedPositions[0]);
                    listaConta.listahm.remove(reverseSortedPositions[0]);
                    listaConta.porciento.remove(reverseSortedPositions[0]);
                    //adapter.notifyDataSetChanged();
                    pesos.remove(reverseSortedPositions[0]);
                    int i=0;
                    total=0;
                    listaConta.totalpago3();


                    mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                }

                @Override
                public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                    //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                    //arrayList.remove(reverseSortedPositions[0]);
                   // al.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                    listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                    listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                    listaConta.listacont.remove(reverseSortedPositions[0]);
                    listaConta.opclist.remove(reverseSortedPositions[0]);
                    listaConta.imgserver.remove(reverseSortedPositions[0]);
                    listaConta.clavetapete.remove(reverseSortedPositions[0]);
                    listaConta.medidastapete.remove(reverseSortedPositions[0]);
                    listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                    listaConta.clavelarga.remove(reverseSortedPositions[0]);
                    listaConta.listahm.remove(reverseSortedPositions[0]);
                    listaConta.porciento.remove(reverseSortedPositions[0]);
                    //adapter.notifyDataSetChanged();
                    pesos.remove(reverseSortedPositions[0]);
                    int i=0;
                    total=0;
                    listaConta.totalpago3();
  /*                      while(pesos.size()>i){

                            xd=pesos.get(i);
                            int a=Integer.parseInt(xd);
                            total=total + a;
                            i++;
                        }
*/

                    //totalprecioxd.setAmount(total);
                    //totalprecioxd.setText(total);
                    mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                }
            }, true, false);

            //Escuchadores del listView
            mlista.setOnTouchListener(touchListener);
            mlista.setOnScrollListener(touchListener.makeScrollListener());

        }

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*

        clavemanual.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {



                if(clavemanual.toString().trim().equalsIgnoreCase("")){
                    Toast.makeText(listatapetes3.this,"Coloca Clave ",Toast.LENGTH_LONG).show();
                    clavemanual.setFocusable(true);
                }else{
                    int checarlista=arrayList.size();
                    String claveconsulta=clavemanual.getText().toString();
                    validastock.checartapeteCLAVEcorta(claveconsulta);

                    if (validastock.error404==0){
                        Toast.makeText(listatapetes3.this, "No esta se encuantra en tu inventario",Toast.LENGTH_LONG).show();
                    }else{

                        //Toast.makeText(listatapetes.this,"cuantas tengo que quitar "+validastock.cuantaspiesastengovalida,Toast.LENGTH_LONG).show();
                        int stoksss=Integer.parseInt(validastock.stockibodegasclavecorta);
                        // Toast.makeText(listatapetes.this,"cuantas tengo "+stoksss,Toast.LENGTH_LONG).show();

                        int validarsihay=stoksss-validastock.cuantastengoibodegas;


                        if(validarsihay<=0){
                            Toast.makeText(listatapetes3.this,"No tienes existencias... ",Toast.LENGTH_LONG).show();

                        }else{

                            claveconsulta=claveconsulta.toUpperCase();
                            if(checarlista==0){
                                hiloconexion2 = new listatapetes3.ObtenerWebService();
                                String cadenallamada2 = GET_BY_IDib + "?clave=" + claveconsulta;
                                hiloconexion2.execute(cadenallamada2,"2");

                            }else{

                                //for (int i=1;i<checarlista;i++){
                                int i=0;
                                int x=0;
                                while(i<checarlista){
                                    String claveenlista=arrayList.get(i);

                                    if (claveconsulta.equals(claveenlista)){
                                        //listaConta.subircantidadtapete(i);
                                        mlista.requestFocus(i);
                                        int aumentapiesa=0;
                                        int mas1=0;
                                        aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                        mas1=aumentapiesa+1;
                                        String activa=Integer.toString(mas1);
                                        listaConta.listacont.set(i,activa);
                                        listaConta.totalpago3();
                                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));


                                        x=1;
                                    }
                                    i++;
                                }
                                i=0;
                                claveconsulta=clavemanual.getText().toString();

                                if(x!=1){
                                    i=0;
                                    claveconsulta=clavemanual.getText().toString();

                                    while(i<checarlista){
                                        String claveenlista=arrayList.get(i);

                                        if (claveconsulta.equals(claveenlista)){
                                            // listaConta.subircantidadtapete(i);
                                            mlista.requestFocus(i);

                                            int aumentapiesa=0;
                                            int mas1=0;
                                            aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                            mas1=aumentapiesa+1;
                                            String activa=Integer.toString(mas1);
                                            listaConta.listacont.set(i,activa);
                                            listaConta.totalpago3();
                                            mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));



                                            x=1;
                                        }
                                        i++;
                                    }

                                }


                                if(x!=1){
                                    i=0;
                                    claveconsulta=clavemanual.getText().toString();
                                    claveconsulta=claveconsulta.toLowerCase();
                                    while(i<checarlista){
                                        String claveenlista=arrayList.get(i);

                                        if (claveconsulta.equals(claveenlista)){
                                            // listaConta.subircantidadtapete(i);
                                            mlista.requestFocus(i);

                                            int aumentapiesa=0;
                                            int mas1=0;
                                            aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                            mas1=aumentapiesa+1;
                                            String activa=Integer.toString(mas1);
                                            listaConta.listacont.set(i,activa);
                                            listaConta.totalpago3();
                                            mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));


                                            x=1;
                                        }
                                        i++;
                                    }


                                }

                                if (x==1){

                                    Toast.makeText(listatapetes3.this,"ya esta en tu lista... indica cuantas requieres",Toast.LENGTH_LONG).show();

                                }else{

                                    //Toast.makeText(listatapetes.this,""+claveconsulta,Toast.LENGTH_LONG).show();
                                    hiloconexion2 = new listatapetes3.ObtenerWebService();
                                    String cadenallamada2 = GET_BY_IDib + "?clave=" + claveconsulta;
                                    hiloconexion2.execute(cadenallamada2,"2");

                                }
                            }

                        }
                    }


                }
                clavemanual.setText("");
                return false;
            }
        });
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*



//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*


        btninsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(clavemanual.toString().trim().equalsIgnoreCase("")){
                    Toast.makeText(listatapetes3.this,"Coloca Clave ",Toast.LENGTH_LONG).show();
                    clavemanual.setFocusable(true);
                }else{
                    int checarlista=arrayList.size();
                    String claveconsulta=clavemanual.getText().toString();
                    validastock.checartapeteCLAVEcorta(claveconsulta);

                    if (validastock.error404==0){
                        Toast.makeText(listatapetes3.this, "No esta se encuantra en tu inventario",Toast.LENGTH_LONG).show();
                    }else{


                        //Toast.makeText(listatapetes.this,"cuantas tengo que quitar "+validastock.cuantaspiesastengovalida,Toast.LENGTH_LONG).show();
                        int stoksss=Integer.parseInt(validastock.stockibodegasclavecorta);
                        // Toast.makeText(listatapetes.this,"cuantas tengo "+stoksss,Toast.LENGTH_LONG).show();

                        int validarsihay=stoksss-validastock.cuantastengoibodegas;

                        if(validarsihay<=0){
                            Toast.makeText(listatapetes3.this,"No tienes existencias... ",Toast.LENGTH_LONG).show();

                        }else{

                            claveconsulta=claveconsulta.toUpperCase();
                            if(checarlista==0){
                                hiloconexion2 = new listatapetes3.ObtenerWebService();
                                String cadenallamada2 = GET_BY_IDib + "?clave=" + claveconsulta;
                                hiloconexion2.execute(cadenallamada2,"2");

                            }else{

                                //for (int i=1;i<checarlista;i++){
                                int i=0;
                                int x=0;
                                while(i<checarlista){
                                    String claveenlista=arrayList.get(i);

                                    if (claveconsulta.equals(claveenlista)){
                                        //listaConta.subircantidadtapete(i);
                                        mlista.requestFocus(i);

                                        int aumentapiesa=0;
                                        int mas1=0;
                                        aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                        mas1=aumentapiesa+1;
                                        String activa=Integer.toString(mas1);
                                        listaConta.listacont.set(i,activa);
                                        listaConta.totalpago3();
                                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));


                                        x=1;
                                    }
                                    i++;
                                }

                                if(x!=1){
                                    i=0;
                                    claveconsulta=clavemanual.getText().toString();

                                    while(i<checarlista){
                                        String claveenlista=arrayList.get(i);

                                        if (claveconsulta.equals(claveenlista)){
                                            // listaConta.subircantidadtapete(i);
                                            mlista.requestFocus(i);

                                            int aumentapiesa=0;
                                            int mas1=0;
                                            aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                            mas1=aumentapiesa+1;
                                            String activa=Integer.toString(mas1);
                                            listaConta.listacont.set(i,activa);
                                            listaConta.totalpago3();
                                            mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));



                                            x=1;
                                        }
                                        i++;
                                    }

                                }


                                if(x!=1){
                                    i=0;
                                    claveconsulta=clavemanual.getText().toString();
                                    claveconsulta=claveconsulta.toLowerCase();
                                    while(i<checarlista){
                                        String claveenlista=arrayList.get(i);

                                        if (claveconsulta.equals(claveenlista)){
                                            // listaConta.subircantidadtapete(i);
                                            mlista.requestFocus(i);

                                            int aumentapiesa=0;
                                            int mas1=0;
                                            aumentapiesa=Integer.parseInt(listaConta.listacont.get(i));
                                            mas1=aumentapiesa+1;
                                            String activa=Integer.toString(mas1);
                                            listaConta.listacont.set(i,activa);
                                            listaConta.totalpago3();
                                            mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));


                                            x=1;
                                        }
                                        i++;
                                    }


                                }


                                if (x==1){

                                    Toast.makeText(listatapetes3.this,"ya esta en tu lista... una mas",Toast.LENGTH_LONG).show();

                                }else{

                                    //Toast.makeText(listatapetes.this,""+claveconsulta,Toast.LENGTH_LONG).show();
                                    hiloconexion2 = new listatapetes3.ObtenerWebService();
                                    String cadenallamada2 = GET_BY_IDib + "?clave=" + claveconsulta;
                                    hiloconexion2.execute(cadenallamada2,"2");

                                }
                            }

                        }
                    }



                }


                clavemanual.setText("");
            }

        });
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*





        escanerclaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                IntentIntegrator integrator= new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("escaneo");
                integrator.setOrientationLocked(false);
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        hacerpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaConta.clavetapete.size()==0){
                    Toast.makeText(listatapetes3.this,"no hay tapetes",Toast.LENGTH_LONG).show();

                }else {
                    Intent intent = new Intent(listatapetes3.this, generaPedido3.class);
                    intent.putExtra("al", listaConta.clavetapete);
                    intent.putExtra("casa",xx);
                    startActivity(intent);

                    finish();

                }
            }
        });


        invbodegas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inventariobodegas= new Intent(listatapetes3.this,apedido3ibodegas.class);
                startActivity(inventariobodegas);
                //clavemanual.setText(listaConta.claveseleccionada.toString());

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result.getContents()!=null){
            if(result.getContents()== null){
                Toast.makeText(this,"No escaner",Toast.LENGTH_LONG).show();

            }else{
//******************************************************************************************************Toast.makeText(this,"resultado "+result.getContents(),Toast.LENGTH_LONG).show();
                int checarlista=arrayList.size();
                String claveconsulta= result.getContents();

                validastock.checartapetelarga(claveconsulta);

  //**************************************************************************************************************************************

                if (validastock.error404==0){
                    Toast.makeText(listatapetes3.this, "No esta se encuantra en tu inventario",Toast.LENGTH_LONG).show();
                }else{

                    //Toast.makeText(listatapetes.this,"cuantas tengo que quitar "+validastock.cuantaspiesastengovalida,Toast.LENGTH_LONG).show();
                    int stoksss=Integer.parseInt(validastock.stockspropreciovalida2);
                    // Toast.makeText(listatapetes.this,"cuantas tengo "+stoksss,Toast.LENGTH_LONG).show();

                    int validarsihay=stoksss-validastock.cuantaspiesastengovalida2;
                    //*******************************************************************************************************

                    if(validarsihay<=0){
                        Toast.makeText(listatapetes3.this,"No tienes existencias... ",Toast.LENGTH_LONG).show();

                    }else{

                        claveconsulta=claveconsulta.toUpperCase();
                        if(checarlista==0){
                            hiloconexion2 = new listatapetes3.ObtenerWebService();
                            String cadenallamada2 = GET_BY_ID + "?clave=" + claveconsulta;
                            hiloconexion2.execute(cadenallamada2,"2");

                        }else{

                            //for (int i=1;i<checarlista;i++){
                            int i=0;
                            int x=0;
                            while(i<checarlista){
                                String claveenlista=arrayList.get(i);

                                if (claveconsulta.equals(claveenlista)){

                                    //listaConta.subircantidadtapete(i);
                                    x=1;
                                }
                                i++;
                            }
                            i=0;
                            claveconsulta= claveconsulta;

                            while(i<checarlista){
                                String claveenlista=arrayList.get(i);

                                if (claveconsulta.equals(claveenlista)){
                                    // listaConta.subircantidadtapete(i);
                                    x=1;
                                }
                                i++;
                            }

                            i=0;
                            //claveconsulta=clavemanual.getText().toString();
                            claveconsulta=claveconsulta.toLowerCase();
                            while(i<checarlista){
                                String claveenlista=arrayList.get(i);

                                if (claveconsulta.equals(claveenlista)){
                                    // listaConta.subircantidadtapete(i);
                                    x=1;
                                }
                                i++;
                            }

                            if (x==1){

                                Toast.makeText(listatapetes3.this,"ya esta en tu lista... indica cuantas requieres",Toast.LENGTH_LONG).show();

                            }else{

                                //Toast.makeText(listatapetes.this,""+claveconsulta,Toast.LENGTH_LONG).show();
                                hiloconexion2 = new listatapetes3.ObtenerWebService();
                                String cadenallamada2 = GET_BY_ID + "?clave=" + claveconsulta;
                                hiloconexion2.execute(cadenallamada2,"2");

                            }
                        }

                    }

                }

//***********************************************************************************************************************************************************************************
               /* String claveconsulta=result.getContents().toString();
                hiloconexion2 = new listatapetes.ObtenerWebService();
                String cadenallamada2 = GET_BY_ID + "?clave=" + claveconsulta;
                hiloconexion2.execute(cadenallamada2,"2");*/



            }
        }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;

            if(params[1]=="2"){    // Consulta de todos los alumnos

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){


                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int resultadolistas=Integer.parseInt(resultJSON);

                        if (resultadolistas==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1").trim();
                            av_descripcion=respuestaJSON.getJSONObject("alumno").getString("descripcio");
                            av_medias=respuestaJSON.getJSONObject("alumno").getString("medidas");
                            av_precio=respuestaJSON.getJSONObject("alumno").getString("precio1");
                            av_precioe=respuestaJSON.getJSONObject("alumno").getString("precio2");
                            av_preciom=respuestaJSON.getJSONObject("alumno").getString("precio3");
                            av_imagen=respuestaJSON.getJSONObject("alumno").getString("pathima1");
                            av_clavelarga=respuestaJSON.getJSONObject("alumno").getString("producto");
                            av_hm=respuestaJSON.getJSONObject("alumno").getString("hm");
                            av_porciento=respuestaJSON.getJSONObject("alumno").getString("desa");
                            totalprecio=Integer.parseInt(av_precio);

                        }
                        else if (resultadolistas==2){
                            devuelve = "No";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }

            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {





            if(s=="No"){
                Toast.makeText(listatapetes3.this,"no se escuentra en expo",Toast.LENGTH_LONG).show();

            }else {
                //lista.setAdapter(new Adaptador(this,datos,datosimagenes));



                //clavemanual.setText("");
                arrayList.add(s);//clave del tapete
                arrayListdes.add(av_descripcion);//descripcion del tapete
                arrayListprecio.add(av_precio);//predio 1 del tapete
                arrayListprecioexpo.add(av_precioe);//precio expo del tapete
                arrayListpreciomayoreo.add(av_preciom);
                arratListMedida.add(av_medias);
                listaConta.listacont.add("1");

                //txtlista.setText(arrayList.toString());
                al.add(s);
                pesos.add(av_precio);

                listaConta.precioXtapete.add(av_precio);//ya
                listaConta.precioXtapeteEx.add(av_precioe);
                listaConta.precioXtapeteMa.add(av_preciom);
                listaConta.precioseleccionado.add(av_precio);
                listaConta.opclist.add("1");
                listaConta.imgserver.add(appRutaservidor.IPIMGpedido+ av_imagen);
                listaConta.clavetapete.add(s);
                listaConta.medidastapete.add(av_medias);
                listaConta.descripciontapete.add(av_descripcion);
                listaConta.clavelarga.add(av_clavelarga);
                listaConta.listahm.add(av_hm);
                listaConta.porciento.add(av_porciento);
                int i=0;
                total=0;
                while(pesos.size()>i){

                    xd=pesos.get(i);
                    int a=Integer.parseInt(xd);
                    total=total + a;
                    i++;
                }


                totalprecioxd.setAmount(total);


                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                //***************************************************************************************************************************
                SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(mlista, new SwipeListViewTouchListener.OnSwipeCallback() {
                    @Override
                    public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                        arrayList.remove(reverseSortedPositions[0]);
                        al.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                        listaConta.listacont.remove(reverseSortedPositions[0]);
                        listaConta.opclist.remove(reverseSortedPositions[0]);
                        listaConta.imgserver.remove(reverseSortedPositions[0]);
                        listaConta.clavetapete.remove(reverseSortedPositions[0]);
                        listaConta.medidastapete.remove(reverseSortedPositions[0]);
                        listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                        listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                        listaConta.clavelarga.remove(reverseSortedPositions[0]);
                        listaConta.listahm.remove(reverseSortedPositions[0]);
                        listaConta.porciento.remove(reverseSortedPositions[0]);
                        //adapter.notifyDataSetChanged();
                        pesos.remove(reverseSortedPositions[0]);
                        int i=0;
                        total=0;
                        listaConta.totalpago3();


                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                    }

                    @Override
                    public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                        arrayList.remove(reverseSortedPositions[0]);
                        al.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                        listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                        listaConta.listacont.remove(reverseSortedPositions[0]);
                        listaConta.opclist.remove(reverseSortedPositions[0]);
                        listaConta.imgserver.remove(reverseSortedPositions[0]);
                        listaConta.clavetapete.remove(reverseSortedPositions[0]);
                        listaConta.medidastapete.remove(reverseSortedPositions[0]);
                        listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                        listaConta.clavelarga.remove(reverseSortedPositions[0]);
                        listaConta.listahm.remove(reverseSortedPositions[0]);
                        listaConta.porciento.remove(reverseSortedPositions[0]);
                        //adapter.notifyDataSetChanged();
                        pesos.remove(reverseSortedPositions[0]);
                        int i=0;
                        total=0;
                        listaConta.totalpago3();
  /*                      while(pesos.size()>i){

                            xd=pesos.get(i);
                            int a=Integer.parseInt(xd);
                            total=total + a;
                            i++;
                        }
*/

                        //totalprecioxd.setAmount(total);
                        //totalprecioxd.setText(total);
                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                    }
                }, true, false);

                //Escuchadores del listView
                mlista.setOnTouchListener(touchListener);
                mlista.setOnScrollListener(touchListener.makeScrollListener());

                ///**************************************************************************************************************************



            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ObtenerWebServiceCORTA extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;

            if(params[1]=="2"){    // Consulta de todos los alumnos

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){


                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int resultadolistas=Integer.parseInt(resultJSON);

                        if (resultadolistas==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1").trim();
                            av_descripcion=respuestaJSON.getJSONObject("alumno").getString("descripcio");
                            av_medias=respuestaJSON.getJSONObject("alumno").getString("medidas");
                            av_precio=respuestaJSON.getJSONObject("alumno").getString("precio1");
                            av_precioe=respuestaJSON.getJSONObject("alumno").getString("precio2");
                            av_preciom=respuestaJSON.getJSONObject("alumno").getString("precio3");
                            av_imagen=respuestaJSON.getJSONObject("alumno").getString("pathima1");
                            av_clavelarga=respuestaJSON.getJSONObject("alumno").getString("producto");
                            av_porciento=respuestaJSON.getJSONObject("alumno").getString("desa");
                            totalprecio=Integer.parseInt(av_precio);

                        }
                        else if (resultadolistas==2){
                            devuelve = "No";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }

            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {


            //cadenadeclave=  av_descripcion +" "+av_medias+"  $"+av_precio;
            //nombretapetes.setText(av_descripcion);
            //medidatapete.setText(av_medias);
            //preciolista.setText(av_precio);
            //precioexpo.setText(av_precioe);
            //preciomayoreo.setText(av_preciom);


            if(s=="No"){
                Toast.makeText(listatapetes3.this,"no se escuentra en expo",Toast.LENGTH_LONG).show();

            }else {
                //lista.setAdapter(new Adaptador(this,datos,datosimagenes));



                //clavemanual.setText("");
                arrayList.add(s);//clave del tapete
                arrayListdes.add(av_descripcion);//descripcion del tapete
                arrayListprecio.add(av_precio);//predio 1 del tapete
                arrayListprecioexpo.add(av_precioe);//precio expo del tapete
                arrayListpreciomayoreo.add(av_preciom);
                arratListMedida.add(av_medias);
                listaConta.listacont.add("1");

                //txtlista.setText(arrayList.toString());
                al.add(s);
                pesos.add(av_precio);

                listaConta.precioXtapete.add(av_precio);//ya
                listaConta.precioXtapeteEx.add(av_precioe);
                listaConta.precioXtapeteMa.add(av_preciom);
                listaConta.precioseleccionado.add(av_precio);
                listaConta.opclist.add("1");
                listaConta.imgserver.add(appRutaservidor.IPIMGpedido+ av_imagen);
                listaConta.clavetapete.add(s);
                listaConta.medidastapete.add(av_medias);
                listaConta.descripciontapete.add(av_descripcion);
                listaConta.clavelarga.add(av_clavelarga);
                listaConta.porciento.add(av_porciento);
                int i=0;
                total=0;
                while(pesos.size()>i){

                    xd=pesos.get(i);
                    int a=Integer.parseInt(xd);
                    total=total + a;
                    i++;
                }


                totalprecioxd.setAmount(total);


                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                //***************************************************************************************************************************
                SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(mlista, new SwipeListViewTouchListener.OnSwipeCallback() {
                    @Override
                    public void onSwipeLeft(ListView listView, int[] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                        arrayList.remove(reverseSortedPositions[0]);
                        al.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                        listaConta.listacont.remove(reverseSortedPositions[0]);
                        listaConta.opclist.remove(reverseSortedPositions[0]);
                        listaConta.imgserver.remove(reverseSortedPositions[0]);
                        listaConta.clavetapete.remove(reverseSortedPositions[0]);
                        listaConta.medidastapete.remove(reverseSortedPositions[0]);
                        listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                        listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                        listaConta.clavelarga.remove(reverseSortedPositions[0]);
                        listaConta.porciento.remove(reverseSortedPositions[0]);
                        //adapter.notifyDataSetChanged();
                        pesos.remove(reverseSortedPositions[0]);
                        int i=0;
                        total=0;
                        listaConta.totalpago3();


                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                    }

                    @Override
                    public void onSwipeRight(ListView listView, int[] reverseSortedPositions) {
                        //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                        arrayList.remove(reverseSortedPositions[0]);
                        al.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapete.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteEx.remove(reverseSortedPositions[0]);
                        listaConta.precioXtapeteMa.remove(reverseSortedPositions[0]);
                        listaConta.precioseleccionado.remove(reverseSortedPositions[0]);
                        listaConta.listacont.remove(reverseSortedPositions[0]);
                        listaConta.opclist.remove(reverseSortedPositions[0]);
                        listaConta.imgserver.remove(reverseSortedPositions[0]);
                        listaConta.clavetapete.remove(reverseSortedPositions[0]);
                        listaConta.medidastapete.remove(reverseSortedPositions[0]);
                        listaConta.descripciontapete.remove(reverseSortedPositions[0]);
                        listaConta.clavelarga.remove(reverseSortedPositions[0]);
                        listaConta.porciento.remove(reverseSortedPositions[0]);
                        //adapter.notifyDataSetChanged();
                        pesos.remove(reverseSortedPositions[0]);
                        int i=0;
                        total=0;
                        listaConta.totalpago3();
  /*                      while(pesos.size()>i){

                            xd=pesos.get(i);
                            int a=Integer.parseInt(xd);
                            total=total + a;
                            i++;
                        }
*/

                        //totalprecioxd.setAmount(total);
                        //totalprecioxd.setText(total);
                        mlista.setAdapter(new Adaptador3(listatapetes3.this,listaConta.clavetapete,listaConta.descripciontapete,listaConta.precioXtapete,listaConta.precioXtapeteEx,listaConta.precioXtapeteMa,listaConta.medidastapete,listaConta.porciento));

                    }
                }, true, false);

                //Escuchadores del listView
                mlista.setOnTouchListener(touchListener);
                mlista.setOnScrollListener(touchListener.makeScrollListener());

                ///**************************************************************************************************************************



            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



}
