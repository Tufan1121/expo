//Funcion para generar y abrir pedido generado en Expo

package com.tufan.sistemas1.com.expotufan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class verpedidopdf extends AppCompatActivity {
    WebView url;
    String pedidoverurl;
    ObtenerWebService hiloconexion;
    String GET2 = appRutaservidor.IP  + "/pedido.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle=getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpedidopdf);

        pedidoverurl=(String) getIntent().getSerializableExtra("pedido");

        url= findViewById(R.id.urlverpdf3);
        hiloconexion = new ObtenerWebService();
        hiloconexion.execute(GET2+"?pedido="+pedidoverurl,"1");


       Intent intent = new Intent();
       intent.setDataAndType(Uri.parse(appRutaservidor.IP+"/pedido.php?pedido="+pedidoverurl), "application/pdf");
       startActivity(intent);


        finish();




    }
    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            String micadenadelista;
            if(params[1]=="1"){    // Consulta de todos LOS PEDIDOS

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
                        /*
                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        int generapedidoresul = Integer.parseInt(resultJSON);

                        if (generapedidoresul==1){      // hay PEDIDOS A MOSTRAR a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            //listItems = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){
                                micadenadelista="";
                                // cargarlista="";


                            }
                            //numeropedido=solicitaJSON.length()+1;

                        }
                        else if (generapedidoresul==2){
                            devuelve = "No hay alumnos";
                            //numeropedido=1;
                        }
                        */
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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

            Intent verpdfcreado = new Intent();
            verpdfcreado.setDataAndType(Uri.parse(appRutaservidor.IP+"/pedidosexpo/"+pedidoverurl+".pdf"), "application/pdf");
            startActivity(verpdfcreado);


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
