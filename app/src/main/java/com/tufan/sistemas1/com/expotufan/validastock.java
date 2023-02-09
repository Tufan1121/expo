package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

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

public class validastock {

    public static String stockspropreciovalida;
    public static  int cuantaspiesastengovalida;


    public static String stockspropreciovalida2;
    public static  int cuantaspiesastengovalida2;

    public static String stockibodegasclavecorta;
    public static  int cuantastengoibodegas;


    public static int si;
    public static int error404;

    public static void  checartapete(String clavetapetes){
        ObtenerWebService hiloconexion2;
        //ObtenerWebService3 hiloconexion3;
        error404=1;
        // Rutas de los Web Services
        //String GET = appRutaservidor.IP  + "/obtener_solicitudes.php";
        String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idenexpo.php";
        String GET_BY_IDLG = appRutaservidor.IP  + "/obtener_clave_por_idenexpolarga.php";

        //String GET_BY_ID2x = appRutaservidor.IP  + "/obtener_clave_por_id_dedetalle.php";
        //int si;//Si variable si =0 no hay .... si = 1 si hay disponibles.....
        cuantaspiesastengovalida=0;
        String claveendatalle= clavetapetes;
/*

        hiloconexion3 = new validastock.ObtenerWebService3();
        try {

            String cadenallamada3=GET_BY_ID2x + "?clave=" + claveendatalle.toString();
            hiloconexion3.execute(cadenallamada3,"1").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        String claveconsulta= clavetapetes;
        hiloconexion2 = new validastock.ObtenerWebService();


        try {

            String cadenallamada2 = GET_BY_ID + "?tabla=spock"+ listaConta.almacen1 +"&clave=" + claveendatalle;
            hiloconexion2.execute(cadenallamada2,"2").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
    public static void  checartapetelg(String clavetapetes){
        ObtenerWebService hiloconexion2;
        //ObtenerWebService3 hiloconexion3;
        error404=1;
        // Rutas de los Web Services
        //String GET = appRutaservidor.IP  + "/obtener_solicitudes.php";
        //String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idenexpo.php";
        String GET_BY_IDLG = appRutaservidor.IP  + "/obtener_clave_por_idenexpolarga.php";

        //String GET_BY_ID2x = appRutaservidor.IP  + "/obtener_clave_por_id_dedetalle.php";
        //int si;//Si variable si =0 no hay .... si = 1 si hay disponibles.....
        cuantaspiesastengovalida=0;
        String claveendatalle= clavetapetes;
/*

        hiloconexion3 = new validastock.ObtenerWebService3();
        try {

            String cadenallamada3=GET_BY_ID2x + "?clave=" + claveendatalle.toString();
            hiloconexion3.execute(cadenallamada3,"1").get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        String claveconsulta= clavetapetes;
        hiloconexion2 = new validastock.ObtenerWebService();


        try {

            String cadenallamada2 = GET_BY_IDLG + "?tabla=spock"+ listaConta.almacen1 +"&clave=" + claveendatalle;
            hiloconexion2.execute(cadenallamada2,"2").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

    public static class ObtenerWebService extends AsyncTask<String,Void,String> {

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
                        int resulsoloprecios= Integer.parseInt(resultJSON);
                        if (resulsoloprecios==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1").trim();
                            stockspropreciovalida=respuestaJSON.getJSONObject("alumno").getString("hm");


                        }
                        else if (resulsoloprecios==2){
                            devuelve = "No hay Registros";
                            error404=0;
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
            if(s.trim().equalsIgnoreCase("No hay Registros")){
                stockspropreciovalida="0";
                error404=0;
            }else {

                int xconsulta = Integer.parseInt(stockspropreciovalida);
                xconsulta = xconsulta - cuantaspiesastengovalida;
                String stocksproprecio = Integer.toString(xconsulta);

                if(xconsulta==0){
                    si=0;

                }else{
                    si=1;

                }
                //*************************************************************************************************
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

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*
//TODO... ESTA ES PARA CHECAR EXISTENCIAS... Y SABER QUE HAY EXISTENCIAS EN CLAVE LARGA

    public static void  checartapetelarga(String clavetapetes){
        ObtenerWebServicelarga hiloconexion2;
        //ObtenerWebService3 hiloconexion3;
        error404=1;
        // Rutas de los Web Services
        //String GET = appRutaservidor.IP  + "/obtener_solicitudes.php";
        String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idlista2.php";
        //String GET_BY_ID2x = appRutaservidor.IP  + "/obtener_clave_por_id_dedetalle.php";
        //int si;//Si variable si =0 no hay .... si = 1 si hay disponibles.....
        cuantaspiesastengovalida2=0;
        String claveendatalle= clavetapetes;
/*

        hiloconexion3 = new validastock.ObtenerWebService3();
        try {


            String cadenallamada3=GET_BY_ID2x + "?clave=" + claveendatalle.toString();
            hiloconexion3.execute(cadenallamada3,"1").get();



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        String claveconsulta= clavetapetes;
        hiloconexion2 = new validastock.ObtenerWebServicelarga();


        try {

            String cadenallamada2 = GET_BY_ID + "?clave=" + claveendatalle;
            hiloconexion2.execute(cadenallamada2,"2").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
    public static class ObtenerWebServicelarga extends AsyncTask<String,Void,String> {

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
                        int resulsoloprecios= Integer.parseInt(resultJSON);
                        if (resulsoloprecios==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1").trim();
                            stockspropreciovalida2=respuestaJSON.getJSONObject("alumno").getString("hm");


                        }
                        else if (resulsoloprecios==2){
                            devuelve = "No hay Registros";
                            error404=0;
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
            if(s.trim().equalsIgnoreCase("No hay Registros")){
                stockspropreciovalida2="0";
                error404=0;
            }else {

                int xconsulta = Integer.parseInt(stockspropreciovalida2);
                xconsulta = xconsulta - cuantaspiesastengovalida2;
                String stocksproprecio = Integer.toString(xconsulta);

                if(xconsulta==0){
                    si=0;

                }else{
                    si=1;

                }
                //*************************************************************************************************
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


    public static void  checartapeteCLAVEcorta(String clavetapetes){
        ObtenerWebServicecorta hiloconexioncorta;
        //ObtenerWebService3 hiloconexion3;
        error404=1;
        // Rutas de los Web Services
        //String GET = appRutaservidor.IP  + "/obtener_solicitudes.php";
        String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idlista2xclavecorta.php";
        //String GET_BY_ID2x = appRutaservidor.IP  + "/obtener_clave_por_id_dedetalle.php";
        //int si;//Si variable si =0 no hay .... si = 1 si hay disponibles.....
        cuantastengoibodegas=0;
        String claveendatalle= clavetapetes;


        String claveconsulta= clavetapetes;
        hiloconexioncorta = new validastock.ObtenerWebServicecorta();


        try {

            String cadenallamada2 = GET_BY_ID + "?clave=" + claveendatalle;
            hiloconexioncorta.execute(cadenallamada2,"2").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
    public static class ObtenerWebServicecorta extends AsyncTask<String,Void,String> {

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
                        int resulsoloprecios= Integer.parseInt(resultJSON);
                        if (resulsoloprecios==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1").trim();
                            stockibodegasclavecorta=respuestaJSON.getJSONObject("alumno").getString("hm");


                        }
                        else if (resulsoloprecios==2){
                            devuelve = "No hay Registros";
                            error404=0;
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
            if(s.trim().equalsIgnoreCase("No hay Registros")){
                stockibodegasclavecorta="0";
                error404=0;
            }else {

                int xconsulta = Integer.parseInt(stockibodegasclavecorta);
                xconsulta = xconsulta - cuantastengoibodegas;
                String stocksproprecio = Integer.toString(xconsulta);

                if(xconsulta==0){
                    si=0;

                }else{
                    si=1;

                }
                //*************************************************************************************************
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
