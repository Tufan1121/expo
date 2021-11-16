package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class inventario extends AppCompatActivity implements View.OnClickListener {
ListView listatodo;
EditText claveinventario;
String  av_descripcion;
String idt;
String av_medias;
String existenciasxtapetes;
Button inventarioExpo;
Button inventarioIbodegas;
Button busquedaglobal;


    ArrayList<String> arrayList= new ArrayList<>();
    ArrayList<String> arrayListdes= new ArrayList<>();



    ObtenerWebService hiloconexion2;
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_inventario.php";
    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_inventario_id.php";
    //http://192.168.1.74/notificaciones/obtener_inventario.php

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        //listatodo=(ListView)findViewById(R.id.lista_todo);
        inventarioExpo=(Button)findViewById(R.id.inv_enexpo);
        inventarioIbodegas=(Button)findViewById(R.id.inv_ibodegas);
        busquedaglobal=(Button)findViewById(R.id.inv_global);
        //buscarentodo= (Button)findViewById(R.id.btn_buscainventario);
        //claveinventario= (EditText)findViewById(R.id.caja_clave);

        //buscarentodo.setOnClickListener(this);
/*
        hiloconexion2 = new ObtenerWebService();
        String cadenallamada2 = GET_BY_ID ;
        hiloconexion2.execute(cadenallamada2,"2");
*/
        inventarioIbodegas.setOnClickListener(this);
        inventarioExpo.setOnClickListener(this);
        busquedaglobal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.inv_enexpo:
                Intent pasaainventarioexpo= new Intent(this,invpornombre.class);
                startActivity(pasaainventarioexpo);

                break;
            case R.id.inv_ibodegas:
                Intent pasainventariobodegas= new Intent(this,invpornombreibodegas.class);
                startActivity(pasainventariobodegas);
                break;

            case R.id.inv_global:
                Intent busquedaglobal= new Intent(this,busquedaglobal.class);
                startActivity(busquedaglobal);
                break;

                default:
                    break;


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
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");
                            for(int i=0;i<solicitaJSON.length();i++){

                                devuelve = solicitaJSON.getJSONObject(i).getString("idsubcategoria");
                                av_descripcion=solicitaJSON.getJSONObject(i).getString("subcatname");
                                idt=solicitaJSON.getJSONObject(i).getString("idsubcategoria");
                                arrayList.add(idt);
                                arrayListdes.add(av_descripcion);

                            }

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
                Toast.makeText(inventario.this,"No se escuentra en la explo",Toast.LENGTH_LONG).show();

            }else {

                //listaConta.clavetapete.add(s.toString());
                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                listatodo.setAdapter(new Adaptador_inv(inventario.this,arrayListdes));
                listatodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(inventario.this, invpornombre.class);
                        String a;
                        String b;
                        a=arrayList.get(position);
                        b=arrayListdes.get(position);
                        pasainv.putExtra("nombretapete",a);
                        pasainv.putExtra("nombrextapete",b);
                        startActivity(pasainv);
                    }
                });

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
