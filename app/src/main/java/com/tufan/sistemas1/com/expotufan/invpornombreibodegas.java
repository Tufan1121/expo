package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class invpornombreibodegas extends AppCompatActivity {

    String GET_BY_ID3 = appRutaservidor.IP  + "/obtener_calidad_por_id.php";
    ListView lista_x_tapete;
    EditText consulta;
    ObtenerWebService hiloconexion2;
    ObtenerWebServicelike2 hiloconexion2like;
    ObtenerWebServicelike3 hiloconexion3like;
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
    Button _buscar;

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
        setContentView(R.layout.activity_invpornombreibodegas);
        titulo= findViewById(R.id.txt_coleccion);
        lista_x_tapete= findViewById(R.id.lista_x_nombre);
        consulta= findViewById(R.id.editText_compo);
        _buscar= findViewById(R.id.buscari);


        _buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HideKeyboard(v);
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


                String miconsulta="";

                miconsulta =  consulta.getText().toString();

                hiloconexion2 = new invpornombreibodegas.ObtenerWebService();
                String cadenallamada2=GET_BY_ID3 + "?calidad=" + miconsulta;
                try {
                    hiloconexion2.execute(cadenallamada2,"2").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
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

                                listaxclave.add(xclave);
                                listaxnombre.add(xnombre);
                                listaxmedidas.add(xmedida);
                                listaxstok.add(xstok);
                                listaximg.add(imgver);
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
                String miconsulta="";

                miconsulta =  "%"+consulta.getText().toString()+"%";

                hiloconexion2like = new invpornombreibodegas.ObtenerWebServicelike2();
                String cadenallamada2like=GET_BY_ID3 + "?calidad=" + miconsulta;
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
                lista_x_tapete.setAdapter(new Adaptador_x_nombre(invpornombreibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));

                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(invpornombreibodegas.this, img.class);
                        //String a;
                        listaConta.imgver1=listaximg.get(position);
                        listaConta.nombresT=listaxnombre.get(position).trim()+" "+listadiseno.get(position).trim();
                        listaConta.medidasT=listaxmedidas.get(position);
                        //pasainv.putExtra("nombretapete",a);
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

    public class ObtenerWebServicelike2 extends AsyncTask<String,Void,String> {

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

                                listaxclave.add(xclave);
                                listaxnombre.add(xnombre);
                                listaxmedidas.add(xmedida);
                                listaxstok.add(xstok);
                                listaximg.add(imgver);
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
                String miconsulta="";

                miconsulta =  "% "+consulta.getText().toString()+"%";

                hiloconexion3like = new invpornombreibodegas.ObtenerWebServicelike3();
                String cadenallamada3like=GET_BY_ID3 + "?calidad=" + miconsulta;
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
                lista_x_tapete.setAdapter(new Adaptador_x_nombre(invpornombreibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));


                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(invpornombreibodegas.this, img.class);
                        //String a;
                        listaConta.imgver1=listaximg.get(position);
                        //pasainv.putExtra("nombretapete",a);
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

    public class ObtenerWebServicelike3 extends AsyncTask<String,Void,String> {

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

                                listaxclave.add(xclave);
                                listaxnombre.add(xnombre);
                                listaxmedidas.add(xmedida);
                                listaxstok.add(xstok);
                                listaximg.add(imgver);
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



            }else {

                //listaConta.clavetapete.add(s.toString());
                //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayList);
                lista_x_tapete.setAdapter(new Adaptador_x_nombre(invpornombreibodegas.this,listaxclave,listaxnombre,listaxmedidas,listaxstok,listaximg,listadiseno,listabodega1,listabodega2,listabodega3,listabodega4,xlistaprecio1,xlistaprecio2,xlistaprecio3));

                lista_x_tapete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent pasainv = new Intent(invpornombreibodegas.this, img.class);
                        //String a;
                        listaConta.imgver1=listaximg.get(position);
                        //pasainv.putExtra("nombretapete",a);
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

    private void HideKeyboard(View v) {
        InputMethodManager teclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
