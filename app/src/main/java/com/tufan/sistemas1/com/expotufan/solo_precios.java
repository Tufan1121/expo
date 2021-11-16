package com.tufan.sistemas1.com.expotufan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.fabiomsr.moneytextview.MoneyTextView;
import org.json.JSONArray;
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
import java.util.List;

public class solo_precios extends AppCompatActivity {
private Button escaner;
private TextView nombretapetes;
private TextView clavetapete;
private TextView medidatapete;
private TextView color;
private MoneyTextView preciolista;
private MoneyTextView precioexpo;
private MoneyTextView preciomayoreo;
private ImageView imagentapete;
private TextView existencias;
private EditText buscarclavemanual;
private Button claveboton;
private ListView lista_mas_t;
private TextView ymas;

String almacen1;
    int milesSP;
    int reciduoSP;
    String miles1SP;
    String reciduo1SP;
    private int total=0;
    private String hpafarSP;
String masconsulta;
    int milesSP_expo;
    int reciduoSP_expo;
    String miles1SP_expo;
    String reciduo1SP_expo;
    private int total_expo=0;
    private String hpafarSP_expo;


    private int total_mayoreo=0;
    private String hpafarSP_mayoreo;

    private ProgressDialog progress;
    public Thread t;

    String av_descripcion;
    String av_medias;
    String av_precio;
    String av_precioe;
    String av_preciom;
    String av_diseno;
    String av_desa;
    String stocksproprecio;
    String subcatedoria;
    String imgprecio;
    int cuantaspiesastengo=0;
    String cadenadeclave;
    ArrayList<String> listItems;

    ObtenerWebService hiloconexion2;
    ObtenerWebService3 hiloconexion3;
    ObtenerWebServiceclave hiloconexion2clave;

    ObtenerWebService3clave hiloconexion3clave;


    // Rutas de los Web Services
    //String GET = appRutaservidor.IP  + "/obtener_solicitudes.php";
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_id.php";

    String GET_BY_IDdosdos = appRutaservidor.IP  + "/obtener_clave_por_idiii.php";


    //todo... ESTA ES LA QUE SACA DETALLE DE
    String GET_BY_IDclave = appRutaservidor.IP  + "/obtener_clave_por_idenexpoybodegas.php";
    String GET_BY_IDclave2 = appRutaservidor.IP  + "/obtener_clave_por_idenexpoybodegas2.php";


    String GET_BY_ID3 = appRutaservidor.IP  + "/obtener_clientes_por_idsubcategoria.php";
    String GET_BY_IDxd = appRutaservidor.IP  + "/obtener_clave_por_idzbodegas.php";



    String GET_BY_IDibodegas = appRutaservidor.IP  + "/obtener_clave_por_id.php";
    String GET_BY_IDclaveibodegas = appRutaservidor.IP  + "/obtener_clave_por_idenibodegas.php";



ArrayList <String> pruebalista = new ArrayList<String >();
ArrayList<String> pruebaclave= new ArrayList<String>();

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_precios);
        //intancia de botones con variables
        escaner=(Button)findViewById(R.id.btn_escanear);
        nombretapetes=(TextView)findViewById(R.id.txt_nombre);
        clavetapete=(TextView)findViewById(R.id.txt_clave);
        medidatapete=(TextView)findViewById(R.id.txt_medidas);
        preciolista=(MoneyTextView) findViewById(R.id.txt_precioLista);
        precioexpo=(MoneyTextView) findViewById(R.id.txt_precioExpo);
        preciomayoreo=(MoneyTextView) findViewById(R.id.txt_precioMayoreo);
        imagentapete=(ImageView)findViewById(R.id.img_tapete);
        existencias=(TextView)findViewById(R.id.txtexistencias);
        buscarclavemanual=(EditText)findViewById(R.id.txt_clave_manual);
        claveboton=(Button)findViewById(R.id.btn_buscar);
        lista_mas_t=(ListView)findViewById(R.id.lista_mas);
        ymas=(TextView)findViewById(R.id.txt_ymas);


        final Activity activity = this;

        requestQueue= Volley.newRequestQueue(this);

        try
        {
            BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("almacen1.txt")));
            almacen1 = fin.readLine();
            fin.close();

        }catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
        }

        //***************************************************-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
        //todo... CUANDO SALE EL TECLADO Y PRECIONAMOS ENTER.....

        buscarclavemanual.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                if(buscarclavemanual.getText().toString().trim().equalsIgnoreCase("")){
                    buscarclavemanual.requestFocus();

                }else {
                    //***PROGRES BARRRRR****-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    progress=new ProgressDialog(solo_precios.this);
                    progress.setMessage("Buscando....");
                    progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    // progress.setIndeterminate(true);
                    progress.setProgress(0);
                    progress.show();

                    final int totalProgressTime = 100;
                    t = new Thread() {
                        @Override
                        public void run() {
                            int jumpTime = 0;

                            while(jumpTime < totalProgressTime) {
                                try {
                                    jumpTime += 5;
                                    progress.setProgress(jumpTime);
                                    sleep(100);

                                }
                                catch (InterruptedException e) {
                                    Log.e("error", e.getMessage());
                                }

                            }
                        }


                    };

                    t.start();


                    //*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*---*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
                    stocksproprecio="";
                    cuantaspiesastengo=0;
                    appRutaservidor.IPIMG=appRutaservidor.IPIMG2;
                    String claveendatalle = buscarclavemanual.getText().toString();


                    pruebalista.clear();
                    pruebaclave.clear();

                    nombretapetes.setText("");
                    clavetapete.setText("");
                    medidatapete.setText("");
                    preciolista.setAmount(0);
                    precioexpo.setAmount(0);
                    preciomayoreo.setAmount(0);
                    existencias.setText("");
                    buscarclavemanual.setText("");
                    imagentapete.setImageBitmap(null);

                    //String claveconsulta = clavetapete.getText().toString();
                   /* hiloconexion2 = new solo_precios.ObtenerWebService();
                    String cadenallamada2 = GET_BY_ID + "?clave=" + claveendatalle.toString();
                    hiloconexion2.execute(cadenallamada2, "2");

                    */
                    hiloconexion2clave = new solo_precios.ObtenerWebServiceclave();
                    String cadenallamada2clave = GET_BY_IDclave + "?tabla=spock"+almacen1+"&clave=" + claveendatalle.toString();
                    hiloconexion2clave.execute(cadenallamada2clave,"2");
                    progress.dismiss();
                }
                return false;
            }
        });

//*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--


        claveboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buscarclavemanual.getText().toString().trim().equalsIgnoreCase("") ){
                    Toast.makeText(solo_precios.this,"Coloca clave",Toast.LENGTH_SHORT).show();
                    buscarclavemanual.setFocusable(true);

                }else {
                    progress=new ProgressDialog(solo_precios.this);
                    progress.setMessage("Buscando....");
                    progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    // progress.setIndeterminate(true);
                    progress.setProgress(0);
                    progress.show();

                    final int totalProgressTime = 100;
                    t = new Thread() {
                        @Override
                        public void run() {
                            int jumpTime = 0;

                            while(jumpTime < totalProgressTime) {
                                try {
                                    jumpTime += 5;
                                    progress.setProgress(jumpTime);
                                    sleep(100);

                                }
                                catch (InterruptedException e) {
                                    Log.e("error", e.getMessage());
                                }

                            }
                        }


                    };

                    t.start();

                    stocksproprecio="";
                    cuantaspiesastengo=0;
                    appRutaservidor.IPIMG=appRutaservidor.IPIMG2;
                    String claveendatalle = buscarclavemanual.getText().toString();
                    /*
                    hiloconexion3 = new solo_precios.ObtenerWebService3();
                    String cadenallamada3 = GET_BY_ID2 + "?clave=" + claveendatalle.toString();
                    hiloconexion3.execute(cadenallamada3, "1");
*/
                    pruebalista.clear();
                    pruebaclave.clear();

                    nombretapetes.setText("");
                    clavetapete.setText("");
                    medidatapete.setText("");
                    preciolista.setAmount(0);
                    precioexpo.setAmount(0);
                    preciomayoreo.setAmount(0);
                    existencias.setText("");
                    buscarclavemanual.setText("");
                    imagentapete.setImageBitmap(null);

  /*
                    //String claveconsulta = clavetapete.getText().toString();
                    hiloconexion2 = new solo_precios.ObtenerWebService();
                    String cadenallamada2 = GET_BY_ID + "?clave=" + claveendatalle.toString();
                    hiloconexion2.execute(cadenallamada2, "2");
*/
                    //String claveconsulta=clavetapete.getText().toString();
                    hiloconexion2clave = new solo_precios.ObtenerWebServiceclave();
                    //String cadenallamada2clave = GET_BY_IDclave + "?clave=" + claveendatalle.toString();
                    String cadenallamada2clave = GET_BY_IDclave + "?tabla=spock"+almacen1+"&clave=" + claveendatalle.toString();
                    hiloconexion2clave.execute(cadenallamada2clave,"2");


                    progress.dismiss();
                }

            }
        });


        escaner.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               appRutaservidor.IPIMG=appRutaservidor.IPIMG2;
               IntentIntegrator integrator= new IntentIntegrator(solo_precios.this);
               integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
               integrator.setPrompt("escaneo");
               integrator.setOrientationLocked(false);
               integrator.setCameraId(0);
               integrator.setBeepEnabled(true);
               integrator.setBarcodeImageEnabled(false);
               integrator.initiateScan();


           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);



        if(result.getContents()!=null){
            if(result.getContents()== null){
                Toast.makeText(this,"you cancelled the scand",Toast.LENGTH_LONG).show();

            }else{
                progress=new ProgressDialog(solo_precios.this);
                progress.setMessage("Buscando....");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // progress.setIndeterminate(true);
                progress.setProgress(0);
                progress.show();

                final int totalProgressTime = 100;
                t = new Thread() {
                    @Override
                    public void run() {
                        int jumpTime = 0;

                        while(jumpTime < totalProgressTime) {
                            try {
                                jumpTime += 5;
                                progress.setProgress(jumpTime);
                                sleep(100);

                            }
                            catch (InterruptedException e) {
                                Log.e("error", e.getMessage());
                            }

                        }
                    }


                };

                t.start();


                stocksproprecio="";
                cuantaspiesastengo=0;

                clavetapete.setText(result.getContents());
                String claveendatalle=clavetapete.getText().toString();

                if(claveendatalle.length()<15){
                    Toast.makeText(this,"escanear corta",Toast.LENGTH_SHORT).show();

                    pruebalista.clear();
                    pruebaclave.clear();
                    nombretapetes.setText("");
                    clavetapete.setText("");
                    medidatapete.setText("");
                    preciolista.setAmount(0);
                    precioexpo.setAmount(0);
                    preciomayoreo.setAmount(0);
                    existencias.setText("");
                    imagentapete.setImageBitmap(null);

                    // Toast.makeText(this,"resultado "+cuantaspiesastengo,Toast.LENGTH_LONG).show();

                    String claveconsulta=clavetapete.getText().toString();
                    hiloconexion2clave = new solo_precios.ObtenerWebServiceclave();
                    String cadenallamada2clave = GET_BY_IDclave + "?tabla=spock"+almacen1+"&clave=" + claveendatalle.toString();
                    //String cadenallamada2clave = GET_BY_IDclave + "?clave=" + claveendatalle.toString();
                    hiloconexion2clave.execute(cadenallamada2clave,"2");
                    progress.dismiss();

                }else{

                    pruebalista.clear();
                    pruebaclave.clear();
                    nombretapetes.setText("");
                    clavetapete.setText("");
                    medidatapete.setText("");
                    preciolista.setAmount(0);
                    precioexpo.setAmount(0);
                    preciomayoreo.setAmount(0);
                    existencias.setText("");
                    imagentapete.setImageBitmap(null);

                    // Toast.makeText(this,"resultado "+cuantaspiesastengo,Toast.LENGTH_LONG).show();
                    masconsulta=claveendatalle.toString();
                    String claveconsulta=clavetapete.getText().toString();
                    hiloconexion2 = new solo_precios.ObtenerWebService();
                    String cadenallamada2 = GET_BY_IDdosdos + "?clave=" + claveendatalle.toString();
                    hiloconexion2.execute(cadenallamada2,"2");
                    progress.dismiss();
                }


            }
        }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }




    private void relacionados(String subcatedoria) {

        hiloconexion3 = new ObtenerWebService3();
        String cadenallamada3=GET_BY_ID3 + "?idsubcategoria=" + subcatedoria.toString();
        hiloconexion3.execute(cadenallamada3,"1");


    }
    private void relacionadosclave(String subcatedoria) {

        hiloconexion3 = new ObtenerWebService3();
        String cadenallamada3=GET_BY_ID3 + "?idsubcategoria=" + subcatedoria.toString();
        hiloconexion3.execute(cadenallamada3,"1");


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
                        int resulsoloprecios= Integer.parseInt(resultJSON);
                        if (resulsoloprecios==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1");
                            av_descripcion=respuestaJSON.getJSONObject("alumno").getString("descripcio");
                            av_medias=respuestaJSON.getJSONObject("alumno").getString("medidas");
                            av_precio=respuestaJSON.getJSONObject("alumno").getString("precio1");
                            av_precioe=respuestaJSON.getJSONObject("alumno").getString("precio2");
                            av_preciom=respuestaJSON.getJSONObject("alumno").getString("precio3");
                            stocksproprecio=respuestaJSON.getJSONObject("alumno").getString("hm");
                            av_diseno=respuestaJSON.getJSONObject("alumno").getString("diseno");
                            //subcatedoria=respuestaJSON.getJSONObject("alumno").getString("idsubcategoria");
                            imgprecio=respuestaJSON.getJSONObject("alumno").getString("pathima1");
                            av_desa=respuestaJSON.getJSONObject("alumno").getString("desa");


                            appRutaservidor.IPIMG=appRutaservidor.IPIMG+imgprecio;

                        }
                        else if (resulsoloprecios==1){
                            devuelve = "No hay Registros";
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
            if(s.toString().trim().equalsIgnoreCase("")){

                Toast.makeText(solo_precios.this,"No se encuentra en tu inventario",Toast.LENGTH_LONG).show();
                pruebalista = new ArrayList<String>();
                pruebalista.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                lista_mas_t.setEnabled(false);
                // String claveconsulta=clavetapete.getText().toString();

                hiloconexion2clave = new solo_precios.ObtenerWebServiceclave();
                String cadenallamada2clave = GET_BY_IDclave2 + "?tabla=spock"+almacen1+"&clave=" + masconsulta.toString();
                //String cadenallamada2clave = GET_BY_IDclave + "?clave=" + claveendatalle.toString();
                hiloconexion2clave.execute(cadenallamada2clave,"2");

            }else {
                //llenar los relacionados
                /*
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                */

                cadenadeclave = av_descripcion + " " + av_medias + "  $" + av_precio;
                nombretapetes.setText(av_descripcion +" "+av_diseno);
                medidatapete.setText(av_medias);
                clavetapete.setText(s);
                if(av_desa.equalsIgnoreCase("0")){
                    ymas.setText("");
                }else{
                    ymas.setText("%"+av_desa+" Adicional");
                }
                //precioexpo.setText(av_precioe);
                //preciomayoreo.setText(av_preciom);
                existencias.setText(stocksproprecio);
                //*****PRECIO LISTA********************************************************************************************
                total = Integer.parseInt(av_precio);
                preciolista.setAmount(total);

                //********************************************************
                //********EXPPO*****************************************************************************************
                total_expo = Integer.parseInt(av_precioe);
                precioexpo.setAmount(total_expo);
                //************************************************************************************************

                //***PRECIO MAYOREO**********************************************************************************************
                total_mayoreo = Integer.parseInt(av_preciom);

                preciomayoreo.setAmount(total_mayoreo);

                //********************************************************

                ImageRequest imageRequest = new ImageRequest(appRutaservidor.IPIMG, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imagentapete.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                requestQueue.add(imageRequest);

                relacionados(av_descripcion);


                //'x_nombre.setText(av_nombre);
                //'x_correo.setText(av_correo);
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
    public class ObtenerWebService3 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
            String micadenadelista2;

            if(params[1]=="1"){    // Consulta de todos los alumnos

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
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

                        int resultadocliente=Integer.parseInt(resultJSON);

                        if (resultadocliente==1){      // hay alumnos a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                micadenadelista2="";


                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("producto1");


                                micadenadelista=solicitaJSON.getJSONObject(i).getString("producto") + " " +
                                        solicitaJSON.getJSONObject(i).getString("descripcio") + " " +
                                        solicitaJSON.getJSONObject(i).getString("medidas");
                                micadenadelista2=solicitaJSON.getJSONObject(i).getString("producto");


                                pruebaclave.add(micadenadelista2);
                                pruebalista.add (micadenadelista);


                            }

                        }
                        else if (resultadocliente==2){
                            devuelve = "No hay alumnos";
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

            //resultado.setText(s);

            if(s.toString()=="No hay alumnos"){

                pruebalista = new ArrayList<String>();
                pruebalista.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                lista_mas_t.setEnabled(false);

            }else{

            lista_mas_t.setEnabled(true);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);


                lista_mas_t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(solo_precios.this,pruebaclave.get(position),Toast.LENGTH_LONG).show();
                        nombretapetes.setText("");
                        clavetapete.setText("");
                        medidatapete.setText("");
                        preciolista.setAmount(0);
                        precioexpo.setAmount(0);
                        preciomayoreo.setAmount(0);
                        existencias.setText("");
                        imagentapete.setImageBitmap(null);
                        appRutaservidor.IPIMG=appRutaservidor.IPIMG2;

                        hiloconexion2 = new solo_precios.ObtenerWebService();
                        String cadenallamada2 = GET_BY_IDxd + "?clave=" + pruebaclave.get(position);
                        pruebalista.clear();
                        pruebaclave.clear();
                        hiloconexion2.execute(cadenallamada2, "2");
                        lista_mas_t.setEnabled(false);


                    }
                });

            }

            //super.onPostExecute(s);

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

//*-*-*-*-*-*-**-*-*-*-*--*-*-*-*------*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
    public class ObtenerWebServiceclave extends AsyncTask<String,Void,String> {

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
                        int resulsoloprecios= Integer.parseInt(resultJSON);
                        if (resulsoloprecios==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1");
                            av_descripcion=respuestaJSON.getJSONObject("alumno").getString("descripcio");
                            av_medias=respuestaJSON.getJSONObject("alumno").getString("medidas");
                            av_precio=respuestaJSON.getJSONObject("alumno").getString("precio1");
                            av_precioe=respuestaJSON.getJSONObject("alumno").getString("precio2");
                            av_preciom=respuestaJSON.getJSONObject("alumno").getString("precio3");
                            stocksproprecio=respuestaJSON.getJSONObject("alumno").getString("hm");
                            //subcatedoria=respuestaJSON.getJSONObject("alumno").getString("idsubcategoria");
                            imgprecio=respuestaJSON.getJSONObject("alumno").getString("pathima1");
                            av_diseno=respuestaJSON.getJSONObject("alumno").getString("diseno");

                            av_desa=respuestaJSON.getJSONObject("alumno").getString("desa");



                            appRutaservidor.IPIMG=appRutaservidor.IPIMG+imgprecio;

                        }
                        else if (resulsoloprecios==1){
                            devuelve = "No hay Registros";
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
            if(s.toString().trim().equalsIgnoreCase("")){

                pruebalista = new ArrayList<String>();
                pruebalista.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                lista_mas_t.setEnabled(false);

                //Toast.makeText(solo_precios.this,"No se encuentra en tu inventario buscnado en bodegas",Toast.LENGTH_LONG).show();
/*
                hiloconexion3clave = new solo_precios.ObtenerWebService3clave();
                String cadenallamada2clave = GET_BY_IDclave + "?clave=" + claveendatalle.toString();
                hiloconexion2clave.execute(cadenallamada2clave,"2");
*/
            }else {
                //llenar los relacionados
                /*
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                */

                cadenadeclave = av_descripcion + " " + av_medias + "  $" + av_precio;
                nombretapetes.setText(av_descripcion +" "+av_diseno);
                medidatapete.setText(av_medias);
                clavetapete.setText(s);
                if(av_desa.equalsIgnoreCase("0")){
                ymas.setText("");
                }else{
                ymas.setText("%"+av_desa+" Adicional");
                }
                //precioexpo.setText(av_precioe);
                //preciomayoreo.setText(av_preciom);
                existencias.setText(stocksproprecio);
                //*****PRECIO LISTA********************************************************************************************
                total = Integer.parseInt(av_precio);
                preciolista.setAmount(total);

                //********************************************************
                //********EXPPO*****************************************************************************************
                total_expo = Integer.parseInt(av_precioe);
                precioexpo.setAmount(total_expo);
                //************************************************************************************************

                //***PRECIO MAYOREO**********************************************************************************************
                total_mayoreo = Integer.parseInt(av_preciom);

                preciomayoreo.setAmount(total_mayoreo);

                //********************************************************

                ImageRequest imageRequest = new ImageRequest(appRutaservidor.IPIMG, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imagentapete.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                requestQueue.add(imageRequest);

                relacionados(av_descripcion);


                //'x_nombre.setText(av_nombre);
                //'x_correo.setText(av_correo);
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
    public class ObtenerWebService3clave extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
            String micadenadelista2;

            if(params[1]=="1"){    // Consulta de todos los alumnos

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
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

                        int resultadocliente=Integer.parseInt(resultJSON);

                        if (resultadocliente==1){      // hay alumnos a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                micadenadelista2="";


                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("producto1");


                                micadenadelista=solicitaJSON.getJSONObject(i).getString("producto") + " " +
                                        solicitaJSON.getJSONObject(i).getString("descripcio") + " " +
                                        solicitaJSON.getJSONObject(i).getString("medidas");
                                micadenadelista2=solicitaJSON.getJSONObject(i).getString("producto");


                                pruebaclave.add(micadenadelista2);
                                pruebalista.add (micadenadelista);


                            }

                        }
                        else if (resultadocliente==2){
                            devuelve = "No hay alumnos";
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

            //resultado.setText(s);

            if(s.toString()=="No hay alumnos"){

                pruebalista = new ArrayList<String>();
                pruebalista.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);
                lista_mas_t.setEnabled(false);

            }else{

                lista_mas_t.setEnabled(true);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(solo_precios.this, android.R.layout.simple_list_item_1,pruebalista);
                lista_mas_t.setAdapter(arrayAdapter);


                lista_mas_t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(solo_precios.this,pruebaclave.get(position),Toast.LENGTH_LONG).show();
                        nombretapetes.setText("");
                        clavetapete.setText("");
                        medidatapete.setText("");
                        preciolista.setAmount(0);
                        precioexpo.setAmount(0);
                        preciomayoreo.setAmount(0);
                        existencias.setText("");
                        imagentapete.setImageBitmap(null);
                        appRutaservidor.IPIMG=appRutaservidor.IPIMG2;

                        hiloconexion2 = new solo_precios.ObtenerWebService();
                        String cadenallamada2 = GET_BY_ID + "?clave=" + pruebaclave.get(position);
                        pruebalista.clear();
                        pruebaclave.clear();
                        hiloconexion2.execute(cadenallamada2, "2");
                        lista_mas_t.setEnabled(false);


                    }
                });

            }

            //super.onPostExecute(s);

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
