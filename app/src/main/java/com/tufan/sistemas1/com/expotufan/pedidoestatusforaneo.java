package com.tufan.sistemas1.com.expotufan;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class pedidoestatusforaneo extends AppCompatActivity {
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
    ListView listatapetesPedido;
    MoneyTextView pagado;
    MoneyTextView pagado2;//ANTICIPO2
    MoneyTextView pagado3;///TOTAL

    Button salir;

    String titulo;
    String fecha;
    String moneystr;
    String moneystr2;
    String moneystr3;

    //String datosclie;
    String datosclientescadena;

    String GET_BY_ID = appRutaservidor.IP  + "/obtener_pedido_idpforaneomas.php";
    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_pedido_idpexpomasdetalle.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoestatusforaneo);
        tituloexpo=(TextView)findViewById(R.id.txtTituloexpo);
        seriepedido=(TextView)findViewById(R.id.txtpedido);
        fechadelpedido=(TextView)findViewById(R.id.txt_fechapedido);
        datoscliente=(TextView)findViewById(R.id.txt_datos_cliente);
        listatapetesPedido=(ListView)findViewById(R.id.list_detalle_delpedido);
        pedidoavtivo=(String)getIntent().getSerializableExtra("pedido");
        salir=(Button)findViewById(R.id.salirdevista);
        pagado=(MoneyTextView)findViewById(R.id.txt_money);
        pagado2=(MoneyTextView)findViewById(R.id.txt_money4);
        pagado3=(MoneyTextView)findViewById(R.id.txt_money5);
        //Toast.makeText(this,"este es tu pedido"+pedidoavtivo.toString(),Toast.LENGTH_LONG).show();
        seriepedido.setText(pedidoavtivo.toString());

        hiloconexion = new ObtenerWebService();
        String cadenallamada=GET_BY_ID + "?pedido=" + pedidoavtivo.toString();
        hiloconexion.execute(cadenallamada,"1");
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                                moneystr2=solicitaJSON.getJSONObject(i).getString("anticipo2");
                                moneystr3=solicitaJSON.getJSONObject(i).getString("total_pagar");
                                //datoscliente.setText(solicitaJSON.getJSONObject(i).getString("pedidos"));
                                datosclientescadena=solicitaJSON.getJSONObject(i).getString("nom_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("dir_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("tel_cliente") + " " +
                                        solicitaJSON.getJSONObject(i).getString("correo_cliente") + " " +
                                solicitaJSON.getJSONObject(i).getString("rfc_cliente") + "\n ";

                                idpedido=solicitaJSON.getJSONObject(i).getString("idpedido");



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

            if(s.toString()=="No hay alumnos"){
                tituloexpo.setText("No disponible");
                seriepedido.setText("No disponible");
                fechadelpedido.setText("No disponible");
                datoscliente.setText("No disponible");
                pagado.setAmount(0);

            }else{
                tituloexpo.setText(titulo.toString());
                //seriepedido.setText(solicitaJSON.getJSONObject(i).getString("pedidos"));
                fechadelpedido.setText(fecha.toString());
                datoscliente.setText(datosclientescadena.toString());
                int mitotalpgado=0;
                mitotalpgado=mitotalpgado+Integer.parseInt(moneystr);

                int mitotalpgado2=0;
                mitotalpgado2=mitotalpgado2+Integer.parseInt(moneystr2);

                int mitotalpgado3=0;
                mitotalpgado3=mitotalpgado3+Integer.parseInt(moneystr3);


                pagado.setAmount(mitotalpgado);
                pagado2.setAmount(mitotalpgado2);
                pagado3.setAmount(mitotalpgado3);

                hiloconexion2 = new ObtenerWebService2();
                String cadenallamada2=GET_BY_ID2 + "?idpedido=" + idpedido.toString();
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

            if(s.toString()=="No hay alumnos"){
                listItems = new ArrayList<String>();
                listItems.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pedidoestatusforaneo.this, android.R.layout.simple_list_item_1,listItems);
                listatapetesPedido.setAdapter(arrayAdapter);

            }else{



                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pedidoestatusforaneo.this, android.R.layout.simple_list_item_1,listItems);
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
}
