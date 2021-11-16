package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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
import java.time.Year;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class pre_reporte extends AppCompatActivity {
ListView listaxdia;
MoneyTextView totaventa;
int total_x_dia;
int k=0;

    ArrayList<String> listdia=new ArrayList<String>();
    ArrayList<String> ld = new ArrayList<>();
    public static ArrayList<Integer> xdia = new ArrayList<Integer>();
    public static ArrayList year = new ArrayList();
    public static ArrayList NoOfEmp = new ArrayList();
    int ventas_expo=0;
    BarDataSet bardataset;
    BarData data;
    public static BarChart barras;
    String GET = appRutaservidor.IP  + "/obtener_dias.php";
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_fecha_pedidos_por_id.php";
    String GET2 = appRutaservidor.IP  + "/obtener_pagosall.php";

    ObtenerWebService hiloconexion;
    ObtenerWebService2 hiloconexion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_reporte);
        listaxdia=(ListView)findViewById(R.id.lista_dia);
        totaventa=(MoneyTextView)findViewById(R.id.totalventas);
        barras= (BarChart)findViewById(R.id.bar1);


        xdia.clear();
        year.clear();
        NoOfEmp.clear();
        listdia.clear();
        ld.clear();

        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET+"?idexpo="+listaConta.id_expo.toString(),"1");



        hiloconexion2 = new ObtenerWebService2();
        hiloconexion2.execute(GET2+"?idexpo="+listaConta.id_expo.toString(),"1");




    }
    private void llenax() {
        bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        barras.animateY(5000);
        data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barras.setData(data);

    }
    public class ObtenerWebService2 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String devuelve2="";
            String devuelve3="";
            String micadenadelista;
            String micadenadelista2;
            int anticipo1;
            int anticipo2;
            int anticipo3;


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
                                int suma=0;

                                //cargarlista=solicitaJSON.getJSONObject(i).getString("activa");

                                devuelve = solicitaJSON.getJSONObject(i).getString("anticipo");
                                devuelve2 = solicitaJSON.getJSONObject(i).getString("anticipo2");
                                devuelve3=solicitaJSON.getJSONObject(i).getString("anticipo3");
                                anticipo1=Integer.parseInt(devuelve);
                                anticipo2=Integer.parseInt(devuelve2);
                                anticipo3=Integer.parseInt(devuelve3);

                                suma= anticipo1+anticipo2+anticipo3;

                                ventas_expo=ventas_expo+suma;


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

            }else{

                totaventa.setAmount(ventas_expo);




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

    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String suma_tabla="";
            int sumaentablas=0;

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

                                devuelve =  solicitaJSON.getJSONObject(i).getString("fecha");
                                suma_tabla=solicitaJSON.getJSONObject(i).getString("suma");

                                sumaentablas=Integer.parseInt(suma_tabla);


                                    listdia.add(devuelve);
                                    NoOfEmp.add(new BarEntry(sumaentablas, i));

                                    year.add(devuelve);


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
                listdia = new ArrayList<String>();
                listdia.add("No hay Registros");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pre_reporte.this, android.R.layout.simple_list_item_1,listdia);
                listaxdia.setAdapter(arrayAdapter);

            }else{




                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(pre_reporte.this, android.R.layout.simple_list_item_1,listdia);
                listaxdia.setAdapter(arrayAdapter);


                listaxdia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(),"Posision "+position,Toast.LENGTH_SHORT).show();

                        String datoss = listdia.get(position).toString();
                        // Toast.makeText(getApplicationContext(),"dato de lista "+datoss,Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getApplicationContext(),"dato de base de datos"+datosreales,Toast.LENGTH_SHORT).show();


                        Intent intento = new Intent(pre_reporte.this,reporte_ventas.class);
                        intento.putExtra("DATO",datoss);
                        startActivity(intento);



                    }
                });

                llenax();

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
