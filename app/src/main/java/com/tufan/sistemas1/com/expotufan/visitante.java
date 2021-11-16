package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
import java.util.ArrayList;

public class visitante extends AppCompatActivity {
Button escanearcliente;
Button guarda_visita;
    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    EditText txt5;
    EditText txt6;
    EditText txt7;
    EditText txt8;
    EditText txt9;
    EditText txt10;



    String idclientepasa;
    int clientenum=0;

    ObtenerWebService hiloconexion;
    ObtenerWebService2 hiloconexion2;
    //String IP = "http://192.168.1.70/notificaciones";
    String INSERT = appRutaservidor.IP  + "/insertar_clienteqr.php";
    String GET = appRutaservidor.IP  + "/obtener_clientes.php";

    ArrayList<String> al2=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante);
    escanearcliente=(Button)findViewById(R.id.codigo_visita);
        txt1=(EditText)findViewById(R.id.txtV1);
        txt2=(EditText)findViewById(R.id.txtV2);
        txt3=(EditText)findViewById(R.id.txtV3);
        txt4=(EditText)findViewById(R.id.txtV4);
        txt5=(EditText)findViewById(R.id.txtV5);
        txt6=(EditText)findViewById(R.id.txtV6);
        txt7=(EditText)findViewById(R.id.txtV7);
        txt8=(EditText)findViewById(R.id.txtV8);
        txt9=(EditText)findViewById(R.id.txtV9);
        txt10=(EditText)findViewById(R.id.txtV10);




        contadoruser();


        guarda_visita=(Button)findViewById(R.id.btn_guarda_visita);

    escanearcliente.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            appRutaservidor.IPIMG=appRutaservidor.IPIMG2;
            IntentIntegrator integrator= new IntentIntegrator(visitante.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("escaneo");
            integrator.setOrientationLocked(false);
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        }
    });

    guarda_visita.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            insertBase();
            al2.clear();

        }
    });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);



        if(result.getContents()!=null){
            if(result.getContents()== null){
                Toast.makeText(this,"you cancelled the scand",Toast.LENGTH_LONG).show();

            }else{

                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                txt5.setText("");
                txt6.setText("");
                txt7.setText("");
                txt8.setText("");
                txt9.setText("");
                txt10.setText("");

                al2.clear();
                String Str = new String(result.getContents().toString());
                System.out.println("Return Value :" );
                for (String retval: Str.split("\n")) {
                    System.out.println(retval);

                    al2.add(retval);


                }
                int j=0;
                for(j=0;j<al2.size();j++){
                    if(j==1){
                        String nom_app;
                        nom_app=al2.get(j);
                        for(String retvalnom:nom_app.split("Nombre:")){

                            al2.set(j,retvalnom);

                        }
                    }

                    if(j==2){
                        String empresa;
                        empresa=al2.get(j);
                        for(String retvalempresa:empresa.split("ORG:")){

                            al2.set(j,retvalempresa);

                        }
                    }


                    if(j==4){
                        String telcell;
                        telcell=al2.get(j);
                        for(String setvalcel:telcell.split("TEL;CELL:")){
                            al2.set(j,setvalcel);

                        }

                    }

                    if(j==5){
                        String correovt;
                        correovt=al2.get(j);
                        for(String setvalcorreo:correovt.split("EMAIL:")){
                            al2.set(j,setvalcorreo);

                        }

                    }

                }


                int i=0;
                for (i=0;i<al2.size();i++){
                    if (i==1){

                        txt1.setText(al2.get(i));
                    }
                    if (i==4){
                        txt2.setText(al2.get(i));
                    }
                    if (i==5){
                        txt3.setText(al2.get(i));
                    }

                    if (i==2){
                        txt4.setText(al2.get(i));
                    }
                }







            }
        }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void contadoruser(){
        hiloconexion2 = new visitante.ObtenerWebService2();
        hiloconexion2.execute(GET,"1");

    }
    public void insertBase(){
        hiloconexion = new visitante.ObtenerWebService();
        String i=listaConta.idusuariotufan.toString()+Integer.toString(clientenum);

        //*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-**-*-*-*-*

        hiloconexion.execute(INSERT,"3",i,txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString(),txt4.getText().toString());   // Parámetros que recibe doInBackground


    }
    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";



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

                        int clientesnuevos=Integer.parseInt(resultJSON);

                        if (clientesnuevos==1){      // hay alumnos a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            //listItems = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){


                            }

                            clientenum=solicitaJSON.length();

                        }
                        else if (clientesnuevos==2){
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
            else if(params[1]=="2"){    // consulta por id

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
                        int clientesnuevos2=Integer.parseInt(resultJSON);
                        if (clientesnuevos2==1){      // hay un alumno que mostrar
                            devuelve = devuelve + respuestaJSON.getJSONObject("alumno").getString("idAlumno") + " " +
                                    respuestaJSON.getJSONObject("alumno").getString("nombre") + " " +
                                    respuestaJSON.getJSONObject("alumno").getString("direccion");

                        }
                        else if (clientesnuevos2==1){
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
            else if(params[1]=="3"){    // insert a base de dattos

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
                    //int iduserxd=Integer.parseInt(params[2]);
                    // int telefonousuario=Integer.parseInt(params[6]);
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("id_cliente", params[2]);
                    jsonParam.put("nom_cliente", params[3]);
                    jsonParam.put("tel_cliente", params[4]);
                    jsonParam.put("correo_cliente", params[5]);
                    jsonParam.put("empresa", params[6]);


                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();
                    idclientepasa= params[2];
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
                        int clientesnuevos2=Integer.parseInt(resultJSON);

                        if (clientesnuevos2 == 1) {      // hay un alumno que mostrar
                            devuelve = "Alumno insertado correctamente";

                        } else if (clientesnuevos2 == 2 ){
                            devuelve = "El alumno no pudo insertarse";
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
            else if(params[1]=="4"){    // update

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
                    jsonParam.put("idalumno",params[2]);
                    jsonParam.put("nombre", params[3]);
                    jsonParam.put("direccion", params[4]);
                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
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

                        if (resultJSON == "1") {      // hay un alumno que mostrar
                            devuelve = "Alumno actualizado correctamente";

                        } else if (resultJSON == "2") {
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
            else if(params[1]=="5") {    // delete

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
                    jsonParam.put("idalumno", params[2]);
                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
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

                        if (resultJSON == "1") {      // hay un alumno que mostrar
                            devuelve = "Alumno borrado correctamente";

                        } else if (resultJSON == "2") {
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

            Toast.makeText(visitante.this,"Cliente Guardado",Toast.LENGTH_SHORT).show();
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");

al2.clear();
            finish();

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


    public class ObtenerWebService2 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
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


                        int clientesnuevosconsecutivo= Integer.parseInt(resultJSON);
                        if (clientesnuevosconsecutivo==1){      // hay alumnos a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            //listItems = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                // cargarlista="";


                            }
                            clientenum=solicitaJSON.length()+1;

                        }
                        else if (clientesnuevosconsecutivo==1){
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
