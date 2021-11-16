package com.tufan.sistemas1.com.expotufan;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class sesion {
    public static String UPDATEsesion = appRutaservidor.IP  + "/sesion.php";
    public sesion() {
    }

    public static void iniciosesion(String pass){
        //String UPDATE = appRutaservidor.IP  + "/actualizar_inventario.php";

        ObtenerWebService8 hilo = new ObtenerWebService8();
        hilo.execute(UPDATEsesion,"4",pass,"1");


    }
     public static void cerrarsesion(String pass){
         ObtenerWebService8 hilo = new ObtenerWebService8();
         hilo.execute(UPDATEsesion,"4",pass,"0");
    }

    public static class  ObtenerWebService8 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";

            if(params[1].equals("4")){    // update

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
                    jsonParam.put("pass",params[2]);
                    jsonParam.put("sesion", params[3]);

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
                        int generapedidoresul=Integer.parseInt(resultJSON);
                        if (generapedidoresul == 1) {      // hay un alumno que mostrar
                            devuelve = "Alumno actualizado correctamente";

                        } else if (generapedidoresul == 2) {
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
            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }



        @Override
        protected void onPostExecute(String s) {

            /*
            String datoss = "nuevo registro";
            //Toast.makeText(getApplicationContext(),"dato "+datoss,Toast.LENGTH_SHORT).show();

            Intent intento = new .....................00Intent(clientesnuevos.this,listatapetes.class);
            intento.putExtra("DATO",idclientepasa);
            startActivity(intento);

            finish();
                    */


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
