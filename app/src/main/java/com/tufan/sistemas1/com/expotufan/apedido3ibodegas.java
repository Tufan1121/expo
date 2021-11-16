package com.tufan.sistemas1.com.expotufan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.concurrent.ExecutionException;

public class apedido3ibodegas extends AppCompatActivity {

    String GET_BY_ID2 = appRutaservidor.IP  + "/obtener_inventario_por_nombreiboegas.php";
    String GET_BY_ID3 = appRutaservidor.IP  + "/obtener_calidad_por_id.php";

    ListView lista_x_tapete;
    EditText consulta;
    ObtenerWebService hiloconexion2;
    ObtenerWebService2 hiloconexion2like;
    ObtenerWebService3 hiloconexion3like;
    TextView titulo;
    String xclave;
    String xnombre;
    String xmedida;
    String xstok;
    String imgver;
    String xdiseno;
    String xbodega1;
    String xbodega2;
    String xbodega3;
    String xbodega4;

    String xprecio1;
    String xprecio2;
    String xprecio3;



    ArrayList<String> listaxclave= new ArrayList<>();
    ArrayList<String> listaxnombre= new ArrayList<>();
    ArrayList<String> listaxmedidas= new ArrayList<>();
    ArrayList<String> listaxstok= new ArrayList<>();
    ArrayList<String> listaximg=new ArrayList<String>();
    ArrayList<String> listadiseno=new ArrayList<String>();
    ArrayList<String> listabodega1= new ArrayList<String>();
    ArrayList<String> listabodega2= new ArrayList<String>();
    ArrayList<String> listabodega3= new ArrayList<String>();
    ArrayList<String> listabodega4= new ArrayList<String>();

    ArrayList<String> xlistaprecio1= new ArrayList<String>();
    ArrayList<String> xlistaprecio3= new ArrayList<String>();
    ArrayList<String> xlistaprecio2= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_pedido3ibodegas);
        titulo=(TextView)findViewById(R.id.txt_coleccion);
        //String valor = getIntent().getExtras().getString("nombretapete");
        lista_x_tapete=(ListView)findViewById(R.id.lista_x_nombre);
        //String valor2x= getIntent().getExtras().getString("nombrextapete");
        //titulo.setText("Colección "+valor2x.toString());
        consulta=(EditText)findViewById(R.id.editText_compo);


/*
        hiloconexion2 = new invpornombreibodegas.ObtenerWebService();
        String cadenallamada2=GET_BY_ID2;
        hiloconexion2.execute(cadenallamada2,"2");
*/
        consulta.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(consulta.getText().toString().toString().equalsIgnoreCase("")){

                    lista_x_tapete.setAdapter(null);

                }else{
                    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                    //aqui la consulta
                    listaxclave.clear();
                    listaxnombre.clear();
                    listaxmedidas.clear();
                    listaxstok.clear();
                    listaximg.clear();
                    listadiseno.clear();
                    listabodega1.clear();
                    listabodega2.clear();
                    listabodega3.clear();
                    listabodega4.clear();

                    xlistaprecio1.clear();
                    xlistaprecio2.clear();
                    xlistaprecio3.clear();


                    String miconsulta;

                    miconsulta =  consulta.getText().toString()+"%";

                    hiloconexion2 = new apedido3ibodegas.ObtenerWebService();
                    String cadenallamada2=GET_BY_ID3 + "?calidad=" + miconsulta.toString();
                    try {
                        hiloconexion2.execute(cadenallamada2,"2").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //*-*-*-*-*-**--*-*-*-*-**--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-

                }
                return false;
            }
        });

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
                        //Accedemos al 88vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int resultadolistas=Integer.parseInt(resultJSON);
                        if (resultadolistas==1){      // hay un alumno que mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");
                            for(int i=0;i<solicitaJSON.length();i++){

                                devuelve = solicitaJSON.getJSONObject(i).getString("producto1");
                                xclave=solicitaJSON.getJSONObject(i).getString("producto1");
                                xnombre=solicitaJSON.getJSONObject(i).getString("descripcio");
                                xmedida=solicitaJSON.getJSONObject(i).getString("medidas");
                                xstok=solicitaJSON.getJSONObject(i).getString("hm");
                                imgver=solicitaJSON.getJSONObject(i).getString("pathima1");
                                xdiseno=solicitaJSON.getJSONObject(i).getString("diseno");
                                xbodega1=solicitaJSON.getJSONObject(i).getString("bodega1");
                                xbodega2=solicitaJSON.getJSONObject(i).getString("bodega2");
                                xbodega3=solicitaJSON.getJSONObject(i).getString("bodega3");
                                xbodega4=solicitaJSON.getJSONObject(i).getString("bodega4");

                                xprecio1=solicitaJSON.getJSONObject(i).getString("precio1");
                                xprecio2=solicitaJSON.getJSONObject(i).getString("precio2");
                                xprecio3=solicitaJSON.getJSONObject(i).getString("precio3");

                                listaxclave.add(xclave.toString());
                                listaxnombre.add(xnombre.toString());
                                listaxmedidas.add(xmedida.toString());
                                listaxstok.add(xstok.toString());
                                listaximg.add(imgver.toString());
                                listadiseno.add(xdiseno);
                                listabodega1.add(xbodega1);
                                listabodega2.add(xbodega2);
                                listabodega3.add(xbodega3);
                                listabodega4.add(xbodega4);

                                xlistaprecio1.add(xprecio1);
                                xlistaprecio2.add(xprecio2);
                                xlistaprecio3.add(xprecio3);

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
                String miconsulta;

                miconsulta =  "%"+consulta.getText().toString()+"%";

                hiloconexion2like = new apedido3ibodegas.ObtenerWebService2();
                String cadenallamada2like=GET_BY_ID3 + "?calidad=" + miconsulta.toString();
                try {
                    hiloconexion2like.execute(cadenallamada2like,"2").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }else {

                //listaConta.clavetapete.add(s.toString());
                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                lista_x_tapete.setAdapter(new Adaptador_x_nombreIbodegas(apedido3ibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));


                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(apedido3ibodegas.this,"estas es la clave "+listaxclave.get(position),Toast.LENGTH_SHORT).show();
                        listatapetes3.clavemanual.setText(listaxclave.get(position));

                        finish();
                    }
                });

                /*
                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(inventario.this, invpornombre.class);
                        String a;
                        a=arrayList.get(position);
                        pasainv.putExtra("nombretapete",a);
                        startActivity(pasainv);
                    }
                });
                */
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

    public class ObtenerWebService2 extends AsyncTask<String,Void,String> {

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
                        //Accedemos al 88vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int resultadolistas=Integer.parseInt(resultJSON);
                        if (resultadolistas==1){      // hay un alumno que mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");
                            for(int i=0;i<solicitaJSON.length();i++){

                                devuelve = solicitaJSON.getJSONObject(i).getString("producto1");
                                xclave=solicitaJSON.getJSONObject(i).getString("producto1");
                                xnombre=solicitaJSON.getJSONObject(i).getString("descripcio");
                                xmedida=solicitaJSON.getJSONObject(i).getString("medidas");
                                xstok=solicitaJSON.getJSONObject(i).getString("hm");
                                imgver=solicitaJSON.getJSONObject(i).getString("pathima1");
                                xdiseno=solicitaJSON.getJSONObject(i).getString("diseno");
                                xbodega1=solicitaJSON.getJSONObject(i).getString("bodega1");
                                xbodega2=solicitaJSON.getJSONObject(i).getString("bodega2");
                                xbodega3=solicitaJSON.getJSONObject(i).getString("bodega3");
                                xbodega4=solicitaJSON.getJSONObject(i).getString("bodega4");

                                xprecio1=solicitaJSON.getJSONObject(i).getString("precio1");
                                xprecio2=solicitaJSON.getJSONObject(i).getString("precio2");
                                xprecio3=solicitaJSON.getJSONObject(i).getString("precio3");

                                listaxclave.add(xclave.toString());
                                listaxnombre.add(xnombre.toString());
                                listaxmedidas.add(xmedida.toString());
                                listaxstok.add(xstok.toString());
                                listaximg.add(imgver.toString());
                                listadiseno.add(xdiseno);
                                listabodega1.add(xbodega1);
                                listabodega2.add(xbodega2);
                                listabodega3.add(xbodega3);
                                listabodega4.add(xbodega4);

                                xlistaprecio1.add(xprecio1);
                                xlistaprecio2.add(xprecio2);
                                xlistaprecio3.add(xprecio3);

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
                //Toast.makeText(invpornombreibodegas.this,"No se escuentra en la explo",Toast.LENGTH_LONG).show();
                String miconsulta;

                miconsulta =  "% "+consulta.getText().toString()+"%";

                hiloconexion3like = new apedido3ibodegas.ObtenerWebService3();
                String cadenallamada3like=GET_BY_ID3 + "?calidad=" + miconsulta.toString();
                try {
                    hiloconexion3like.execute(cadenallamada3like,"2").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }else {

                //listaConta.clavetapete.add(s.toString());
                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                lista_x_tapete.setAdapter(new Adaptador_x_nombreIbodegas(apedido3ibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));


                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(apedido3ibodegas.this,"estas es la clave "+listaxclave.get(position),Toast.LENGTH_SHORT).show();
                        listatapetes3.clavemanual.setText(listaxclave.get(position));

                        finish();
                    }
                });

                /*
                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(inventario.this, invpornombre.class);
                        String a;
                        a=arrayList.get(position);
                        pasainv.putExtra("nombretapete",a);
                        startActivity(pasainv);
                    }
                });
                */
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
                        //Accedemos al 88vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int resultadolistas=Integer.parseInt(resultJSON);
                        if (resultadolistas==1){      // hay un alumno que mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");
                            for(int i=0;i<solicitaJSON.length();i++){

                                devuelve = solicitaJSON.getJSONObject(i).getString("producto1");
                                xclave=solicitaJSON.getJSONObject(i).getString("producto1");
                                xnombre=solicitaJSON.getJSONObject(i).getString("descripcio");
                                xmedida=solicitaJSON.getJSONObject(i).getString("medidas");
                                xstok=solicitaJSON.getJSONObject(i).getString("hm");
                                imgver=solicitaJSON.getJSONObject(i).getString("pathima1");
                                xdiseno=solicitaJSON.getJSONObject(i).getString("diseno");
                                xbodega1=solicitaJSON.getJSONObject(i).getString("bodega1");
                                xbodega2=solicitaJSON.getJSONObject(i).getString("bodega2");
                                xbodega3=solicitaJSON.getJSONObject(i).getString("bodega3");
                                xbodega4=solicitaJSON.getJSONObject(i).getString("bodega4");

                                xprecio1=solicitaJSON.getJSONObject(i).getString("precio1");
                                xprecio2=solicitaJSON.getJSONObject(i).getString("precio2");
                                xprecio3=solicitaJSON.getJSONObject(i).getString("precio3");

                                listaxclave.add(xclave.toString());
                                listaxnombre.add(xnombre.toString());
                                listaxmedidas.add(xmedida.toString());
                                listaxstok.add(xstok.toString());
                                listaximg.add(imgver.toString());
                                listadiseno.add(xdiseno);
                                listabodega1.add(xbodega1);
                                listabodega2.add(xbodega2);
                                listabodega3.add(xbodega3);
                                listabodega4.add(xbodega4);

                                xlistaprecio1.add(xprecio1);
                                xlistaprecio2.add(xprecio2);
                                xlistaprecio3.add(xprecio3);

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
                //Toast.makeText(invpornombreibodegas.this,"No se escuentra en la explo",Toast.LENGTH_LONG).show();
                lista_x_tapete.setAdapter(null);
            }else {

                //listaConta.clavetapete.add(s.toString());
                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                lista_x_tapete.setAdapter(new Adaptador_x_nombreIbodegas(apedido3ibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));


                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(apedido3ibodegas.this,"estas es la clave "+listaxclave.get(position),Toast.LENGTH_SHORT).show();
                        listatapetes3.clavemanual.setText(listaxclave.get(position));

                        finish();
                    }
                });

                /*
                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(inventario.this, invpornombre.class);
                        String a;
                        a=arrayList.get(position);
                        pasainv.putExtra("nombretapete",a);
                        startActivity(pasainv);
                    }
                });
                */
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
