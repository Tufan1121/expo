package com.tufan.sistemas1.com.expotufan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.fabiomsr.moneytextview.MoneyTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class pedidoestatusexpo extends AppCompatActivity {
String pedidoavtivo;
String idpedido;
ArrayList<String>listapedidosnoserie;
ArrayList<String>listItems;
ObtenerWebService hiloconexion;
ObtenerWebService2 hiloconexion2;


//INSTANCIA DE LOS OBJETOS
    TextView tituloexpo;
    TextView seriepedido;
    TextView fechadelpedido;
    TextView datoscliente;
    TextView yaentregado;
     ListView listatapetesPedido;
     Button entregar;
    MoneyTextView pagado;
    Button salir;

    String titulo;
    String fecha;
    String moneystr;
    String av_entregado;
    //String datosclie;
    String datosclientescadena;

    String GET_BY_ID = appRutaservidor.IP  + "/obtener_pedido_idpexpomas.php";
    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_pedido_idpexpomasdetalle.php";
    String UPDATEPedido = appRutaservidor.IP  + "/updatepedidoestatusentregaexpo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoestatusexpo);
        tituloexpo= findViewById(R.id.txtTituloexpo);
        seriepedido= findViewById(R.id.txtpedido);
        fechadelpedido= findViewById(R.id.txt_fechapedido);
        datoscliente= findViewById(R.id.txt_datos_cliente);
        listatapetesPedido= findViewById(R.id.list_detalle_delpedido);
        entregar= findViewById(R.id.btn_entregar);
        pedidoavtivo=(String)getIntent().getSerializableExtra("pedido");
        salir= findViewById(R.id.salirdevista);
        pagado= findViewById(R.id.txt_money);
        yaentregado= findViewById(R.id.txt_yaestaentregado);
        //Toast.makeText(this,"este es tu pedido"+pedidoavtivo.toString(),Toast.LENGTH_LONG).show();
        seriepedido.setText(pedidoavtivo);

        hiloconexion = new ObtenerWebService();
        String cadenallamada=GET_BY_ID + "?pedido=" + pedidoavtivo;
        hiloconexion.execute(cadenallamada,"1");
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        entregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(pedidoestatusexpo.this,"consulta ok",Toast.LENGTH_SHORT).show();

                ObtenerWebService8 hilo = new ObtenerWebService8();
                String ancitipox2="2";
                hilo.execute(UPDATEPedido,"4",idpedido, ancitipox2);

                entregar.setVisibility(View.INVISIBLE);

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
            String mipedidoid;
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
                            listapedidosnoserie= new ArrayList<String>();
                            listItems = new ArrayList<String>();

                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                //i++;
                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("nom_cliente") + "\n ";


                                //tituloexpo.setText();
                                titulo=solicitaJSON.getJSONObject(i).getString("nom_expo");
                                //seriepedido.setText(solicitaJSON.getJSONObject(i).getString("pedidos"));
                               //fechadelpedido.setText(solicitaJSON.getJSONObject(i).getString("fecha"));
                                fecha=solicitaJSON.getJSONObject(i).getString("fecha");
                                moneystr=solicitaJSON.getJSONObject(i).getString("anticipo");
                                //datoscliente.setText(solicitaJSON.getJSONObject(i).getString("pedidos"));
                                datosclientescadena=solicitaJSON.getJSONObject(i).getString("nom_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("dir_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("tel_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("correo_cliente") + " " +
                                solicitaJSON.getJSONObject(i).getString("rfc_cliente") + "\n ";

                                idpedido=solicitaJSON.getJSONObject(i).getString("idpedido");
                                av_entregado=solicitaJSON.getJSONObject(i).getString("entregado");



                                //listapedidosnoserie.add(mipedidoid);
                                //listItems.add (micadenadelista);

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

            if(s =="No hay alumnos"){
                tituloexpo.setText("No disponible");
                seriepedido.setText("No disponible");
                fechadelpedido.setText("No disponible");
                datoscliente.setText("No disponible");
                pagado.setAmount(0);

            }else{


                if(av_entregado.equals("1")){
                    Toast.makeText(pedidoestatusexpo.this,"no se entrego",Toast.LENGTH_SHORT).show();
                    entregar.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(pedidoestatusexpo.this,"ya se entrego",Toast.LENGTH_SHORT).show();
                    yaentregado.setVisibility(View.VISIBLE);
                }
                tituloexpo.setText(titulo);
                //seriepedido.setText(solicitaJSON.getJSONObject(i).getString("pedidos"));
                fechadelpedido.setText(fecha);
                datoscliente.setText(datosclientescadena);
                int mitotalpgado=0;
                mitotalpgado=mitotalpgado+Integer.parseInt(moneystr);
                pagado.setAmount(mitotalpgado);
                hiloconexion2 = new ObtenerWebService2();
                String cadenallamada2=GET_BY_ID2 + "?idpedido=" + idpedido;
                hiloconexion2.execute(cadenallamada2,"1");


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
//*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-***********-*-*-*-*-*--*-*-*-*-*-
    //*-*-*-*-*PARA SACAR LOS TAPETEST QUE ESTAN EN EL PEDIDO
    @SuppressLint("StaticFieldLeak")
    public class ObtenerWebService2 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
            String mipedidoid;
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
                            listapedidosnoserie= new ArrayList<String>();
                            listItems = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                //i++;
                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("clave");


                                micadenadelista=solicitaJSON.getJSONObject(i).getString("clave") + "                             " +
                                        solicitaJSON.getJSONObject(i).getString("cantidad") + "\n \n"+
                                        solicitaJSON.getJSONObject(i).getString("preciovendido") + " ";



                                listItems.add(micadenadelista);


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

            if(s =="No hay alumnos"){
                listItems = new ArrayList<String>();
                listItems.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pedidoestatusexpo.this, android.R.layout.simple_list_item_1,listItems);
                listatapetesPedido.setAdapter(arrayAdapter);

            }else{



                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pedidoestatusexpo.this, android.R.layout.simple_list_item_1,listItems);
                listatapetesPedido.setAdapter(arrayAdapter);

/*
                listatapetesPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(),"Posision "+position,Toast.LENGTH_SHORT).show();
                        //VER PEDIDO CON PDF*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                        Intent pasaaverdetalledepedido = new Intent(entrega_estatus_pedido.this,pedidoestatusexpo.class);
                        pasaaverdetalledepedido.putExtra("pedido",listapedidosnoserie.get(position));
                        startActivity(pasaaverdetalledepedido);
                        //*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**--*-*-**---*

                    }
                });
*/
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

    public class  ObtenerWebService8 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";

            if(params[1].equals("4")){    // update

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();
                    //Creo el Objeto JSON

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("idpedido",params[2]);
                    jsonParam.put("entregado", params[3]);
                    //jsonParam.put("anticipo2", params[4]);

                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();


                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            result.append(line);
                            //response+=line;
                        }

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int generapedidoresul=Integer.parseInt(resultJSON);
                        if (generapedidoresul == 1) {      // hay un alumno que mostrar
                            devuelve = "Alumno actualizado correctamente";
                            yaentregado.setVisibility(View.VISIBLE);

                        } else if (generapedidoresul == 2) {
                            devuelve = "El alumno no pudo actualizarse";
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

            /*
            String datoss = "nuevo registro";
            //Toast.makeText(getApplicationContext(),"dato "+datoss,Toast.LENGTH_SHORT).show();
            Intent intento = new .....................00Intent(clientesnuevos.this,listatapetes.class);
            intento.putExtra("DATO",idclientepasa);
            startActivity(intento);
            finish();
                    */
            Toast.makeText(pedidoestatusexpo.this, s,Toast.LENGTH_SHORT).show();



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
