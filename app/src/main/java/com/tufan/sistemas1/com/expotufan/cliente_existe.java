package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class cliente_existe extends AppCompatActivity implements View.OnClickListener{
EditText nombreorfc;
Spinner spinner;
Button buscarcliente;
ListView listaclientes;
    String cargarlista;
    //ListView listaxxx;
    ArrayList<String> listItems;
    ArrayList<String> listItemsidporusuario;

    ArrayList<String> cname= new ArrayList<String>();
    ArrayList<String> crfc= new ArrayList<String>();
    ArrayList<String> ccorreo= new ArrayList<String>();
    ArrayList<String> cdir= new ArrayList<String>();

    String av_cmane;
    String av_crfc;
    String av_ccorreo;
    String av_cdir;

    ObtenerWebService hiloconexion;
    ObtenerWebService hiloconexion2;
    //String IP = "http://192.168.1.70/notificaciones";
    // Rutas de los Web Services
    String GET = appRutaservidor.IP  + "/obtener_clientes.php";
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_clientes_por_id.php";
    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_clientes_por_id_rfc.php";

    int buscarx=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_existe);
        nombreorfc=(EditText)findViewById(R.id.edit_nombreorfc);
        spinner=(Spinner)findViewById(R.id.spinnerBusqueda);
        //spinner.setFocusable(true);
        listaclientes=(ListView)findViewById(R.id.listaconsultaClientes);
        buscarcliente=(Button)findViewById(R.id.btn_buscar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        nombreorfc.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        buscarcliente.setVisibility(View.INVISIBLE);

        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET,"1");

        listaConta.correoenviar.clear();
        listaConta.WhatsAppEnviar.clear();

        buscarcliente.setOnClickListener(this);

        nombreorfc.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(nombreorfc.getText().toString().trim().equalsIgnoreCase("")){
                    nombreorfc.setFocusable(true);
                    Toast.makeText(cliente_existe.this,"Coloca RFC o Nombre..",Toast.LENGTH_SHORT).show();

                }else{
                    if (buscarx==0){
                        listItems.clear();
                        listItemsidporusuario.clear();
                        cname.clear();
                        crfc.clear();
                        ccorreo.clear();
                        cdir.clear();
                        listaConta.correoenviar.clear();
                        listaConta.WhatsAppEnviar.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID + "?nom_cliente=" + nombreorfc.getText();
                        hiloconexion2.execute(cadenallamada2,"1");


                    }else{
                        listItems.clear();
                        listItemsidporusuario.clear();
                        cname.clear();
                        crfc.clear();
                        ccorreo.clear();
                        cdir.clear();
                        listaConta.correoenviar.clear();
                        listaConta.WhatsAppEnviar.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID2 + "?nom_cliente=" + nombreorfc.getText();
                        hiloconexion2.execute(cadenallamada2,"1");


                    }
                }


                return false;
            }
        });

        // buscarcliente.setEnabled(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buscarx=position;
                //buscarcliente.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buscar:

                if(nombreorfc.getText().toString().trim().equalsIgnoreCase("")){
                    nombreorfc.setFocusable(true);
                    Toast.makeText(this,"Coloca RFC o Nombre..",Toast.LENGTH_SHORT).show();

                }else{
                    if (buscarx==0){
                        listItems.clear();
                        listItemsidporusuario.clear();
                        listaConta.correoenviar.clear();
                        listaConta.WhatsAppEnviar.clear();

                        cname.clear();
                        crfc.clear();
                        ccorreo.clear();
                        cdir.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID + "?nom_cliente=" + nombreorfc.getText();
                        hiloconexion2.execute(cadenallamada2,"1");


                    }else{
                        listItems.clear();
                        listItemsidporusuario.clear();
                        listaConta.correoenviar.clear();
                        listaConta.WhatsAppEnviar.clear();

                        cname.clear();
                        crfc.clear();
                        ccorreo.clear();
                        cdir.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID2 + "?nom_cliente=" + nombreorfc.getText();
                        hiloconexion2.execute(cadenallamada2,"1");


                    }
                }


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
                            listItems = new ArrayList<String>();
                            listItemsidporusuario = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                cargarlista="";
                                micadenadelista2="";


                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("id_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("nom_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("dir_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("tel_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("correo_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("rfc_cliente") + "\n";


                                micadenadelista=solicitaJSON.getJSONObject(i).getString("id_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("nom_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("dir_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("tel_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("correo_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("rfc_cliente") + " ";
                                micadenadelista2=solicitaJSON.getJSONObject(i).getString("id_cliente");

                                av_cmane=solicitaJSON.getJSONObject(i).getString("nom_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente");
                                av_crfc=solicitaJSON.getJSONObject(i).getString("rfc_cliente");
                                av_ccorreo=solicitaJSON.getJSONObject(i).getString("correo_cliente");
                                av_cdir=solicitaJSON.getJSONObject(i).getString("dir_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("tel_cliente");

                                cname.add(av_cmane.toString());
                                crfc.add(av_crfc.toString());
                                ccorreo.add(av_ccorreo.toString());
                                cdir.add(av_cdir.toString());


                                listaConta.correoenviar.add(solicitaJSON.getJSONObject(i).getString("correo_cliente"));
                                listaConta.WhatsAppEnviar.add(solicitaJSON.getJSONObject(i).getString("tel_cliente"));

                                listItemsidporusuario.add(micadenadelista2);


                                listItems.add (micadenadelista);


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
                listItems = new ArrayList<String>();
                listItems.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(cliente_existe.this, android.R.layout.simple_list_item_1,listItems);
                listaclientes.setAdapter(arrayAdapter);
                listaclientes.setEnabled(false);

            }else{
                listaclientes.setEnabled(true);

                nombreorfc.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                buscarcliente.setVisibility(View.VISIBLE);

                //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(cliente_existe.this, android.R.layout.simple_list_item_1,listItems);
                listaclientes.setAdapter(new Adaptadorclient(cliente_existe.this,cname,crfc,ccorreo,cdir));



                listaclientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Posicion "+position,Toast.LENGTH_SHORT).show();

                        String datoss = listItems.get(position);
                        String datosreales=listItemsidporusuario.get(position);
                       // Toast.makeText(getApplicationContext(),"dato de lista "+datoss,Toast.LENGTH_SHORT).show();
                       // Toast.makeText(getApplicationContext(),"dato de base de datos"+datosreales,Toast.LENGTH_SHORT).show();

                        listaConta.idclientesp=listItemsidporusuario.get(position);
                        listaConta.correoxcliente=listaConta.correoenviar.get(position);
                        listaConta.WhatsApp=listaConta.WhatsAppEnviar.get(position);


                        //listaConta.correoxcliente=listaConta.correoenviar.get(position);

                        Intent intento = new Intent(cliente_existe.this,prepedido.class);
                        //intento.putExtra("DATO",datosreales);
                        startActivity(intento);
                        finish();



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
