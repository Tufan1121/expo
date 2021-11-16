package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
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

public class ver_pedidos_hechosibodegas extends AppCompatActivity implements View.OnClickListener {
Button pedidobuscar;

    ArrayList<String>enpedido= new ArrayList<String>();
    ArrayList<String>enfecha= new ArrayList<String>();
    ArrayList<String>ennombre= new ArrayList<String>();
    ArrayList<String>entotalcompra= new ArrayList<String>();

    String enpedidoc;
    String enfechac;
    String ennombrec;
    String entotalprecioc;

    ObtenerWebService hiloconexion;
    ObtenerWebService hiloconexion2;
    ArrayList<String> listItems;
    ArrayList<String> listapedidosnoserie;
    EditText txtbuscapepdido;
    ListView listatodoslospedidos;
    //EditText txtbuscapepdido;
    Spinner optbuscar;


    String GET = appRutaservidor.IP  + "/obtener_pedidos_allhechosfueraexpo.php";
    String GET_BY_ID = appRutaservidor.IP  + "/obt_pedido.php";
    String GET_BY_ID2 = appRutaservidor.IP  + "/obt_pedidoName.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos_hechosibodegas);
        pedidobuscar=(Button)findViewById(R.id.btn_findpedido);
        txtbuscapepdido=(EditText)findViewById(R.id.txtfindpedido);
        listatodoslospedidos=(ListView)findViewById(R.id.lista_pedidos);
        optbuscar=(Spinner)findViewById(R.id.sp_findP);

        pedidobuscar.setOnClickListener(this);

//+9+9+9+9+9+9+9+9+9+9+9+9++9+9

        txtbuscapepdido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enpedido.clear();
                enfecha.clear();
                ennombre.clear();
                entotalcompra.clear();

                listapedidosnoserie.clear();
                listItems.clear();


                listItems = new ArrayList<String>();
                listItems.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ver_pedidos_hechosibodegas.this, android.R.layout.simple_list_item_1,listItems);
                listatodoslospedidos.setAdapter(arrayAdapter);
                listatodoslospedidos.setEnabled(false);
            }
        });
        txtbuscapepdido.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(txtbuscapepdido.getText().toString().trim().equalsIgnoreCase("")){
                    txtbuscapepdido.requestFocus();
                    Toast.makeText(ver_pedidos_hechosibodegas.this, "Coloca tu pedido",Toast.LENGTH_LONG).show();

                }else if(optbuscar.getSelectedItem().toString().trim().equalsIgnoreCase("Busca Pedido por:")){
                    optbuscar.setFocusable(true);
                    Toast.makeText(ver_pedidos_hechosibodegas.this, "Selecciona como quieres buscar tu pedido",Toast.LENGTH_LONG).show();


                }else{

                    if(optbuscar.getSelectedItem().toString().trim().equalsIgnoreCase("Nombre")){
                        //Toast.makeText(this,"por nombre",Toast.LENGTH_SHORT).show();
                        enpedido.clear();
                        enfecha.clear();
                        ennombre.clear();
                        entotalcompra.clear();

                        listapedidosnoserie.clear();
                        listItems.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID2 + "?name=" + txtbuscapepdido.getText()+"&expo="+listaConta.id_expo.toString();
                        hiloconexion2.execute(cadenallamada2,"1");

                    }else{
                        //Toast.makeText(this,"por ultimos 4 digitos.",Toast.LENGTH_SHORT).show();
                        enpedido.clear();
                        enfecha.clear();
                        ennombre.clear();
                        entotalcompra.clear();

                        listapedidosnoserie.clear();
                        listItems.clear();

                        hiloconexion2 = new ObtenerWebService();
                        String cadenallamada2=GET_BY_ID + "?pedido=" + txtbuscapepdido.getText()+"&expo="+listaConta.id_expo.toString();
                        hiloconexion2.execute(cadenallamada2,"1");


                    }
                    /*

                     */
                }

                return false;
            }
        });

        //+9+9++9+9+9+9+9+9+9+9+9+9

        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET+"?idexpo="+listaConta.id_expo.toString(),"1");




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.btn_findpedido:
                    //Toast.makeText(this, "boton funcionando", Toast.LENGTH_LONG).show();
                    // validomos el texto para busqyeda de usuario:

                    if(txtbuscapepdido.getText().toString().trim().equalsIgnoreCase("")|| optbuscar.getSelectedItem().toString().trim().equalsIgnoreCase("Busca Pedido por:")){
                        txtbuscapepdido.requestFocus();
                        Toast.makeText(this, "Coloca tu pedido",Toast.LENGTH_LONG).show();

                    }else{

                        if(optbuscar.getSelectedItem().toString().trim().equalsIgnoreCase("Nombre")){
                            //Toast.makeText(this,"por nombre",Toast.LENGTH_SHORT).show();
                            enpedido.clear();
                            enfecha.clear();
                            ennombre.clear();
                            entotalcompra.clear();

                            listapedidosnoserie.clear();
                            listItems.clear();

                            hiloconexion2 = new ObtenerWebService();
                            String cadenallamada2=GET_BY_ID2 + "?name=" + txtbuscapepdido.getText();
                                    //+"&expo="+listaConta.id_expo.toString();
                            hiloconexion2.execute(cadenallamada2,"1");

                        }else{
                            //Toast.makeText(this,"por ultimos 4 digitos.",Toast.LENGTH_SHORT).show();
                            enpedido.clear();
                            enfecha.clear();
                            ennombre.clear();
                            entotalcompra.clear();

                            listapedidosnoserie.clear();
                            listItems.clear();

                            hiloconexion2 = new ObtenerWebService();
                            String cadenallamada2=GET_BY_ID + "?pedido=" + txtbuscapepdido.getText()+"&expo="+listaConta.id_expo.toString();
                            hiloconexion2.execute(cadenallamada2,"1");


                        }
                        /*

                         */
                    }


                    break;

            case R.id.btn_salirverpedido:
                //Toast.makeText(this, "nbotondos ya  funconandos",Toast.LENGTH_LONG).show();
                finish();

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

                                devuelve = devuelve + solicitaJSON.getJSONObject(i).getString("pedidos") + " " +
                                        solicitaJSON.getJSONObject(i).getString("nom_cliente") + "\n ";


                                micadenadelista=solicitaJSON.getJSONObject(i).getString("pedidos") + "                             " +
                                        solicitaJSON.getJSONObject(i).getString("fecha") + "\n \n"+

                                        solicitaJSON.getJSONObject(i).getString("nom_cliente") + " "+
                                        solicitaJSON.getJSONObject(i).getString("ap_cliente") + " ";

                                mipedidoid=solicitaJSON.getJSONObject(i).getString("pedidos");

                                enpedidoc=solicitaJSON.getJSONObject(i).getString("pedidos");
                                enfechac=solicitaJSON.getJSONObject(i).getString("fecha");
                                ennombrec=solicitaJSON.getJSONObject(i).getString("nom_cliente")+" "+ solicitaJSON.getJSONObject(i).getString("ap_cliente");
                                entotalprecioc=solicitaJSON.getJSONObject(i).getString("total_pagar");

                                enpedido.add(enpedidoc);
                                enfecha.add(enfechac);
                                ennombre.add(ennombrec);
                                entotalcompra.add(entotalprecioc);

                                listapedidosnoserie.add(mipedidoid);
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
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ver_pedidos_hechosibodegas.this, android.R.layout.simple_list_item_1,listItems);
                listatodoslospedidos.setAdapter(arrayAdapter);
                listatodoslospedidos.setEnabled(false);

            }else{

                listatodoslospedidos.setEnabled(true);

                listatodoslospedidos.setAdapter(new Adaptadorpedidos(ver_pedidos_hechosibodegas.this,enpedido,enfecha,ennombre,entotalcompra));


                listatodoslospedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         //Toast.makeText(getApplicationContext(),"Posision "+position,Toast.LENGTH_SHORT).show();
                         //VER PEDIDO CON PDF*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                        listaConta.pedidoverpdf=listapedidosnoserie.get(position);
                            Intent pasaaverdetalledepedido = new Intent(ver_pedidos_hechosibodegas.this,verpedf2ibodegas.class);
                            pasaaverdetalledepedido.putExtra("pedido",listapedidosnoserie.get(position));
                            startActivity(pasaaverdetalledepedido);
                        //*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**--*-*-**---*

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
