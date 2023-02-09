package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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
import java.util.regex.Pattern;

public class clientesnuevos extends AppCompatActivity implements View.OnClickListener{
Button guardar;
EditText nombre;
EditText apellido;
EditText rfc;
EditText telefono;
EditText direccion;
EditText correo;
Spinner usoCFDIvar;
Spinner rasonsocial;
String idclientepasa;
int clientenum=0;

EditText razon;
EditText cp;
Spinner regimen;
Switch refac;
String requierefac="";

    ObtenerWebService hiloconexion;
    ObtenerWebService2 hiloconexion2;
    //String IP = "http://192.168.1.70/notificaciones";
    String INSERT = appRutaservidor.IP  + "/insertar_cliente.php";
    String GET = appRutaservidor.IP  + "/obtener_clientes.php";

    @Override
    protected void onPause() {
        super.onPause();
        System.out.print("a");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.print("a");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientesnuevos);


        guardar= findViewById(R.id.btn_agregar);
        nombre= findViewById(R.id.txt_nombre_user);
        apellido= findViewById(R.id.txt_apellido);
        rfc= findViewById(R.id.txt_rfc);
        telefono= findViewById(R.id.txt_telefono);
        direccion= findViewById(R.id.txt_direccion2);
        correo= findViewById(R.id.txt_correoelectronico);
        usoCFDIvar= findViewById(R.id.spinnerusoCFDI);
        rasonsocial= findViewById(R.id.spinnerRazonSocial);

        razon= findViewById(R.id.txt_razon);
        cp= findViewById(R.id.txt_cp);
        regimen= findViewById(R.id.spinnerRegimen);
        refac= findViewById(R.id.refac);
        refac.setOnClickListener(this::onClick);

        contadoruser();

        guardar.setOnClickListener(this);
        nombre.setText("");
        apellido.setText("");
        rfc.setText("");
        telefono.setText("");
        direccion.setText("");
        correo.setText("");
        razon.setText("");
        cp.setText("");
        refac.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_agregar:
                if(nombre.getText().toString().trim().equalsIgnoreCase("")){
                    nombre.requestFocus();
                    Snackbar.make(v,"Coloca el Nombre del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else
                if (apellido.getText().toString().trim().equalsIgnoreCase("")){
                    apellido.requestFocus();
                    Snackbar.make(v,"Coloca el Apellido del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else
                if (direccion.getText().toString().trim().equalsIgnoreCase("")){
                    direccion.requestFocus();
                    Snackbar.make(v,"Coloca la Direcion del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else
                if(telefono.getText().toString().trim().equalsIgnoreCase("")){
                    telefono.requestFocus();
                    Snackbar.make(v,"Coloca el Telefono del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else
                if(correo.getText().toString().trim().equalsIgnoreCase("")){
                    correo.requestFocus();
                    Snackbar.make(v,"Coloca el Correo del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else if(!validarimail(correo.getText())){
                    correo.requestFocus();
                    Snackbar.make(v,"Correo del Cliente no Válido",Snackbar.LENGTH_LONG).setAction("Accion",null).show();


                }else

                if (rfc.getText().toString().trim().equalsIgnoreCase("")){
                    rfc.requestFocus();
                    Snackbar.make(v,"Coloca RFC del Cliente",Snackbar.LENGTH_LONG).setAction("Accion",null).show();

                }else{

                    insertBase();

                    finish();
                }

                if (refac.isChecked()){
                    if (regimen.getSelectedItem().toString().trim().equalsIgnoreCase("")){
                        regimen.requestFocus();
                        Snackbar.make(v,"Falta Regimen Fiscal",Snackbar.LENGTH_LONG).setAction("Accion",null).show();
                    }
                    if (usoCFDIvar.getSelectedItem().toString().trim().equalsIgnoreCase("")){
                        usoCFDIvar.requestFocus();
                        Snackbar.make(v,"Falta Uso CFDI",Snackbar.LENGTH_LONG).setAction("Accion",null).show();
                    }

                    if (razon.getText().toString().trim().equalsIgnoreCase("")){
                        razon.requestFocus();
                        Snackbar.make(v,"Falta Razon Social Sin Regimen Capital (Nombre SAT)",Snackbar.LENGTH_LONG).setAction("Accion",null).show();
                    }
                    if (cp.getText().toString().trim().equalsIgnoreCase("")){
                        cp.requestFocus();
                        Snackbar.make(v,"Falta Codigo Postal",Snackbar.LENGTH_LONG).setAction("Accion",null).show();
                    }
                }

                break;
                default:
                    break;
            case R.id.refac:
                if(refac.isChecked()){
                    rfc.setText("");
                    break;
                }else{
                    rfc.setText("XAXX010101000");
                    break;
                }

        }
    }


    private boolean validarimail(Editable text) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(text).matches();
    }


    public void contadoruser(){
    hiloconexion2 = new ObtenerWebService2();
    hiloconexion2.execute(GET,"1");

}
    public void insertBase(){
        hiloconexion = new ObtenerWebService();
        String i= listaConta.idusuariotufan + clientenum;
        String textousoCFDI="";
        String textorasonsocial="";
        String _regimen="";
        String _uso="";

        _regimen=regimen.getSelectedItem().toString().trim().substring(0,3);
        _uso=usoCFDIvar.getSelectedItem().toString().trim().substring(0,3);

        //*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-**-*-*-*-*
        if(usoCFDIvar.getSelectedItem().toString().trim().equalsIgnoreCase("P01-Por Definir")){

            textousoCFDI="P01-Por Definir";
        }
        if(usoCFDIvar.getSelectedItem().toString().trim().equalsIgnoreCase("G01-Adquisición de Mercancias")){
            textousoCFDI="G01-Adquisición de Mercancias";
        }
        if(usoCFDIvar.getSelectedItem().toString().trim().equalsIgnoreCase("G03-Gastos en General")){
            textousoCFDI="G03-Gastos en General";
        }

        if(rasonsocial.getSelectedItem().toString().trim().equalsIgnoreCase("Persona Fisica")){
            textorasonsocial="Persona Fisica";

        }
        if(rasonsocial.getSelectedItem().toString().trim().equalsIgnoreCase("Persona Moral")){

            textorasonsocial="Persona Moral";
        }

        if(rasonsocial.getSelectedItem().toString().trim().equalsIgnoreCase("Persona Moral")){
            textorasonsocial="Persona Moral";
        }

        if (refac.isChecked()){
            requierefac="SI";
        }else{
            requierefac="NO";
        }

        //parametros que se pasan al PHP
        hiloconexion.execute(INSERT,"3",i,nombre.getText().toString(),apellido.getText().toString(),direccion.getText().toString(),telefono.getText().toString(),correo.getText().toString(),rfc.getText().toString(),textorasonsocial,textousoCFDI,requierefac.toString(),cp.getText().toString(),razon.getText().toString(),_regimen,_uso);   // Parámetros que recibe doInBackground


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
                //arma la cadena json para enviar a insert
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
                                jsonParam.put("ap_cliente", params[4]);
                                jsonParam.put("dir_cliente", params[5]);
                                jsonParam.put("tel_cliente", params[6]);
                                jsonParam.put("correo_cliente", params[7]);
                                jsonParam.put("rfc_cliente", params[8]);
                                jsonParam.put("tipopersona", params[9]);
                                jsonParam.put("usocfdi", params[10]);
                                jsonParam.put("factura", params[11]);
                                jsonParam.put("cp", params[12]);
                                jsonParam.put("razonsocial", params[13]);
                                jsonParam.put("regimen", params[14]);
                                jsonParam.put("uso", params[15]);


                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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

                    String datoss = "nuevo registro";
                    //Toast.makeText(getApplicationContext(),"dato "+datoss,Toast.LENGTH_SHORT).show();
                    listaConta.correoxcliente=correo.getText().toString();
                    listaConta.WhatsApp=telefono.getText().toString();

                    Intent intento = new Intent(clientesnuevos.this,prepedido.class);



            //intento.putExtra("DATO",idclientepasa);
                    listaConta.idclientesp=idclientepasa;
                    startActivity(intento);

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
