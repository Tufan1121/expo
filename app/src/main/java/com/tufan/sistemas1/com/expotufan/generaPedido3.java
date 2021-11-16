package com.tufan.sistemas1.com.expotufan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import org.fabiomsr.moneytextview.MoneyTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import static com.tufan.sistemas1.com.expotufan.LoginActivity.idusuariotufan;

public class generaPedido3 extends AppCompatActivity implements View.OnClickListener{
    String enviopedidocorreo;
    String envioWhatsApp;
    Spinner metodopago;
    Spinner metodopago2;
    Spinner metodopago3;
    Button previo;
    Button guardaP;
    EditText comentarios;
    Button salircotiza;
    TextView numeroPedido;

    TextView lbcotiza;
    TextView lbtotalp;
    TextView lbobse;
    TextView msg3;


    Spinner estatuspedido;
    MoneyTextView totalpagarpedido;
    MoneyTextView debeporpagar;
    EditText cuanto_anticipo;
    EditText cuanto_anticipo2;
    EditText cuanto_anticipo3;
    int finaliza3;
    CheckBox entregado;
    CheckBox pendiente;
    String hilo;
    int numeroaleatorio;
    String sttokhg;
    int update;
    public static String pedidoserie;
    int numeropedido;
    int numerodetallepedido;
    int valorstocklista;
    int stocksentero=0;
    String av_descripcion;
    String av_medias;
    String av_precio;
    String av_precioe;
    String av_preciom;
    String clavexd;
    String sttok;
    String consecutivo_pedido;

    ObtenerWebService3 hiloconexion3;
    ObtenerWebService hiloconexion2;
    ObtenerWebService4 hiloconexion4;
    ObtenerWebService6 hiloconexion6;
    ObtenerWebService7 hiloconexion7;
    String entrega;

int x=0;


    String GET_BY_ID = appRutaservidor.IP  + "/obtener_clave_por_idlista2.php";

    //String GET_BY_IDpara = appRutaservidor.IP  + "/obtener_clave_por_id.php";

    String GET2 = appRutaservidor.IP  + "/obtener_pedidos_para_saber_consecutivo.php";
    String GET6 = appRutaservidor.IP  + "/obtener_detallepedidos_para_saber_consecutivo.php";

    //String UPDATE = appRutaservidor.IP  + "/actualizar_inventario.php";
    String INSERTPEDIDO = appRutaservidor.IP  + "/insertar_pedido.php";
    String INSERTdetallePEDIDO = appRutaservidor.IP  + "/insertar_detalle_pedido.php";

    int year;
    String kx;
    private String []header2={"","","",""};
    private String[]header={"Cantidad","Clave","Descripcion","Medidas","Precio"};
    private String shorttext="hola";
    private String longtext="WWW.TAPETESTUFAN.COM";
    private TemplatePDF templatePDF;
    int numeroLista;
    String claveconsulta="";
    EditText observaciones;
    String maxpedidopasaadetalle;

    ArrayList<String[]>rows=new ArrayList<>();
    ArrayList<String[]>row2= new ArrayList<>();
    ArrayList<String> lista;
    ArrayList<String>lista_stok=new ArrayList<>();
    ArrayList<String>lista_stokxd= new ArrayList<>();
    int pagartotalP=0;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("¿Deseas Regresar a lista de Tapetes?");
        dialogo.setMessage("Quieres agregar algo mas...!");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //codigo
                Intent regresaalista= new Intent(generaPedido3.this,listatapetes3.class);
                startActivity(regresaalista);
                finish();
            }
        });
        dialogo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //codigo
            }
        });
        dialogo.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_pedido3);
        //llenaarray();

        lista = listaConta.clavetapete;
        kx=listaConta.idclientesp;



        observaciones=(EditText)findViewById(R.id.editText_observacion);
        //previo=(Button)findViewById(R.id.btn_GP_previo);
        guardaP=(Button)findViewById(R.id.btn_GP_guardar);
        comentarios=(EditText)findViewById(R.id.editText_observacion);
        numeroPedido=(TextView)findViewById(R.id.txt_GP_numeropedido);
        //estatuspedido=(Spinner)findViewById(R.id.sp_estatuspedido);
        totalpagarpedido=(MoneyTextView) findViewById(R.id.txt_totalpagarM);
        debeporpagar=(MoneyTextView)findViewById(R.id.txtdebeporpagar);
        salircotiza=(Button)findViewById(R.id.btnSalircotiza);
        pendiente=(CheckBox)findViewById(R.id.checkBox4pendiente);
        entregado=(CheckBox)findViewById(R.id.checkBox_entregado);
        pagartotalP=Integer.parseInt(listaConta.totalpagartodo.toString());
        msg3=(TextView)findViewById(R.id.txt_msg3);
        lbcotiza=(TextView)findViewById(R.id.lb_cotiza);
        lbtotalp=(TextView)findViewById(R.id.lb_totalp);
        lbobse=(TextView)findViewById(R.id.lb_obse);

        finaliza3=0;
        totalpagarpedido.setAmount(pagartotalP);
        entrega="1";
        numeroaleatorio=(int)(Math.random()*1000)+1;
        Calendar fecha = Calendar.getInstance();
         year = fecha.get(Calendar.YEAR);
        //******************************************************************************************
        //METODO PARA SACAR CONSEGUTIVO DE PEDIDO
            sacarconsecutivodepedidobase();
        //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*--*-*-*-*-*-*-*-*-*-
        //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
        //SACAR SONSECUTIVO DE TABLA DETALLE....
            sacarconsecutivodedetallepedido();
        //*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


        guardaP.setOnClickListener(this);
        salircotiza.setOnClickListener(this);
        numeroLista=lista.size();
            for(int i=0;i<numeroLista;i++){
                    //aqui hacemos consulta de los tapetes que van a ir en el pedido
                    claveconsulta=lista.get(i).toString();
                    //String claveconsulta=clavetapete.getText().toString();
                    hiloconexion2 = new ObtenerWebService();
                    String cadenallamada2 = GET_BY_ID + "?clave=" + claveconsulta;
                    hiloconexion2.execute(cadenallamada2,"2");
            }


    }
    //*-*--*--*-*-*-*-*-*-*-*-*-*---*-*-*-*-*--*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    //OBTENER EL CONSECUTIVO DE LA BASE DE DATOS PEDIDO
    private void sacarconsecutivodepedidobase() {

        hiloconexion3 = new ObtenerWebService3();
        hiloconexion3.execute(GET2,"1");

    }
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
//**-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*--*-*-*--*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-
    //SACAR CONSECUTIVO DE TABLA DETALLE PEDIDO.....*-*-*-*-*-*
    private void sacarconsecutivodedetallepedido() {
        hiloconexion6 = new ObtenerWebService6();
        hiloconexion6.execute(GET6,"1");

    }
//*-*-*-*-*-*-*--*--*-*-*-9-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*--*-*-*-*--*-*-*--*-*-*-*--*-*-*-*-*-*-*-*
@Override
protected void onRestart() {
    super.onRestart();
    msg3.setText("Cotizacion Generada ya puede cerrar");
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){

                case R.id.btn_GP_guardar:

                        if(observaciones.getText().toString().trim().equalsIgnoreCase("")){
                            observaciones.requestFocus();
                            Toast.makeText(this,"Coloca Observaciones del Pedido",Toast.LENGTH_LONG).show();

                        }else{
                            //insert para agregar el pedido
                            insertarpedido();
                            //consulta modificaciones del inventario xd x dx d x d dx

                            //for(int i=0;i<numeroLista;i++){
                          int i=0;

                            while(i<numeroLista){

                                //int update=Integer.parseInt( lista_stok.get(i))-Integer.parseInt(listaConta.listacont.get(i));
                                //String actializastok=Integer.toString(update);
                                String claveconsultaddd=lista.get(i);

                                String claveinventa=lista.get(i);
                                insertardetallepedido(i,claveinventa);

                                i++;
                                //}

                            }
                            //insert para agregar el detalle de los tapetes que se van a pasar

                            enviopedidocorreo=appRutaservidor.IP+"/pedidoibodegas.php?pedido="+listaConta.pedidoverpdf;

                            envioWhatsApp="https://api.whatsapp.com/send?phone=52"+listaConta.WhatsApp+"&text=Hola,"+" este es tu cotización : "+appRutaservidor.IP+"/pedido.php?pedido="+listaConta.pedidoverpdf + "Te recuerdo que tiene vigencia solo hasta el final de la Expo.";
                            enviawhatsapp(envioWhatsApp);

                         //EnviarCorreo(enviopedidocorreo);
                         // EnviarCorreoUser(enviopedidocorreo);
                            guardaP.setEnabled(false);
                            msg3.setVisibility(View.VISIBLE);
                            guardaP.setVisibility(View.INVISIBLE);
                            finaliza3=1;
                            lbcotiza.setVisibility(View.INVISIBLE);
                            lbobse.setVisibility(View.INVISIBLE);
                            lbtotalp.setVisibility(View.INVISIBLE);

                            comentarios.setVisibility(View.INVISIBLE);
                            totalpagarpedido.setVisibility(View.INVISIBLE);
                            numeroPedido.setVisibility(View.INVISIBLE);

                            Intent verpdfterminado= new Intent(this, verpedf2ibodegas.class);
                            startActivity(verpdfterminado);

                            //***************************************************************************************************************
                            //***************************************************************************************************************



                            //finish();

                        }
                    break;
            case R.id.btnSalircotiza:
                if(finaliza3==1){
                    finish();
                }else{
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                    dialogo.setTitle("¿Deseas Cancelar el pedido?");
                    dialogo.setMessage("Se borraran los datos capturados ...!");
                    dialogo.setCancelable(false);
                    dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                            finish();
                        }
                    });
                    dialogo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //codigo
                        }
                    });
                    dialogo.show();
                }
                break;

                default:

                    break;


        }
    }
    private void insertarpedido() {

        hiloconexion4 = new ObtenerWebService4();
        String i=Integer.toString(numeropedido)+listaConta.idusuariotufan.toString();
        maxpedidopasaadetalle=Integer.toString(numeropedido);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(new Date());
        //*************METODO DE PAGO***************************
        String metododepagod="0";
        String metododepagod2="0";
        String metododepagod3="0";

//*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

        //******************************************************
        listaConta.pedidoverpdf=numeroPedido.getText().toString();
        //String entregado=Integer.toString(entrega);

        hiloconexion4.execute(INSERTPEDIDO,"3",i,kx,fechaComoCadena,metododepagod.toString(),metododepagod2.toString(),metododepagod3.toString(),observaciones.getText().toString(),listaConta.idusuariotufan.toString(),numeroPedido.getText().toString(),listaConta.id_expo.toString(),listaConta.estatusselect,"0","0","0",listaConta.totalpagartodo.toString(),"0");   // Parámetros que recibe doInBackground


    }

    private void insertardetallepedido(int pordondeesta,String thisclave) {

        numerodetallepedido++;
        hiloconexion7 = new ObtenerWebService7();
        String idetalle=Integer.toString(numerodetallepedido)+listaConta.idusuariotufan;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(new Date());
        hiloconexion7.execute(INSERTdetallePEDIDO,"3",idetalle,maxpedidopasaadetalle+listaConta.idusuariotufan,thisclave,listaConta.clavelarga.get(pordondeesta).toString(),listaConta.listacont.get(pordondeesta).toString(),listaConta.precioseleccionado.get(pordondeesta).toString(),listaConta.estatusselect);   // Parámetros que recibe doInBackground

    }


    private ArrayList<String[]> gettiendas(){
        //llenaarray();

        row2.add(new String[]{"logo \n \n \n \n",
                "PERISUR \n C. Comercial Perisur \n" +
                "Planta Alta, Local 324" +
                "Del. Coyoacan, CDMX \n" +
                "(55) 5424-3717 ",
                "SANTAFE \n C. Comercial Santa Fe \n" +
                "Planta Alta, Local 505" +
                "Del. Cuajimalpa, CDMX \n" +
                "(55) 2167-4145 ",
                "ATIZAPAN \n C. Comercial Galerias Atizapan \n" +
                "Local 341 \n" +
                "Atizapan \n  de Zagaroza \n" +
                "(55) 5084-2286 "});
        row2.add(new String[]{"SUR\n Periferico Sur 2930 \n" +
                "Local 24 y 25 \n" +
                "Del. Alvaro Obregon, CDMX \n" +
                "(55) 5681-2530 \n",
                "LERMA\n C. C. Las Plazas Outlet \n" +
                "Local AB \n" +
                "Lerma, Edomex \n" +
                "(728) 2845100 \n",
                "INTERLOMAS C. Comercial Paseo Interlomas \n" +
                "Planta Baja Local PB 27 \n" +
                "Huixquilucan, EdoMex \n" +
                "(55) 5290-0547 \n",
                "QUERETARO\n C. Comercial Galerias Queretaro \n" +
                "Local 224 \n" +
                "QRO \n" +
                "(44) 2183-0581 \n"});

        return  row2;
    }

    private  ArrayList<String[]> gettitulos(){

        return rows;
    }
    public void verpdf(View view){

        if (observaciones.getText().toString().toString().equalsIgnoreCase("")){

                    observaciones.requestFocus();
                    Toast.makeText(this,"Coloca las Observaciones del Pedido",Toast.LENGTH_LONG).show();

        }else{
        Drawable d = getResources().getDrawable(R.mipmap.blancotufan);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

        shorttext=observaciones.getText().toString();

        templatePDF= new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("clientes","ventas","hugo");
        templatePDF.imgpdf(stream);
        templatePDF.addTitles("TAPETES  TUFAN","IMPORTADORA MUNDIAL DE TAPETES ORIENTALES","Pedidos   E-033112");
        templatePDF.addParagraph("Cliente Hugo Valencia Samaneigo");
        templatePDF.addParagraph("Av Juarez 10201 San Mateo Atenco, Estado de Mexico.                                          Tel:5544889269");
        templatePDF.createTable(header,gettitulos());
        templatePDF.addParagraph(shorttext);
        templatePDF.addParagraph(longtext);
        templatePDF.closeDocument();
        templatePDF.verpdfya();

                }


    }
    public void llenaarray(){
        row2.add(new String[]{"F-21054","ABACO AMARILLO","2.30X1.60","$  7,790.00"});


    }
//*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
//BUSCA CONSECUTIVO DE PEDIDO Y GENERA EL NOMBRE O NUMERO DE PEDIDO....
    public class ObtenerWebService3 extends AsyncTask<String,Void,String> {

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
                            numeropedido=solicitaJSON.length()+1;

                        }
                        else if (generapedidoresul==2){
                            devuelve = "No hay alumnos";
                            numeropedido=1;
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

            //numeroPedido.setText(listaConta.encabrzadopedido +listaConta.idusuariotufan+numeropedido+listaConta.idusuariotufan+kx);
            //numeroPedido.setText(listaConta.encabrzadopedido+numeropedido+listaConta.idusuariotufan+"C");
            numeroPedido.setText(listaConta.encabrzadopedido+"-"+numeropedido+listaConta.idusuariotufan+"C");
            //numeroPedido.setText(listaConta.encabrzadopedido+"-"+numeropedido+"B"+listaConta.idusuariotufan);


            pedidoserie=numeroPedido.getText().toString();
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
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*
//BUSCA CONSECUTIVO DE DETALLE PEDIDO Y ....
    public class ObtenerWebService6 extends AsyncTask<String,Void,String> {

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

                    int generapedidoresul=Integer.parseInt(resultJSON);

                    if (generapedidoresul==1){      // hay alumnos a mostrar
                        JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                        //listItems = new ArrayList<String>();
                        for(int i=0;i<solicitaJSON.length();i++){
                            micadenadelista="";
                            // cargarlista="";


                        }
                        numerodetallepedido=solicitaJSON.length();

                    }
                    else if (generapedidoresul==1){
                        devuelve = "No hay alumnos";
                        numerodetallepedido=1;
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

        ///aqui se ejecuta a la mar  c++
        // incertardetallepedido();
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
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*
//CONSULTA DE TAPETES QUE VAN EN LA LISTA
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
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        int generapedidoresul=Integer.parseInt(resultJSON);
                        if (generapedidoresul==1){      // hay un alumno que mostrar
                            devuelve = respuestaJSON.getJSONObject("alumno").getString("producto1");
                            clavexd = respuestaJSON.getJSONObject("alumno").getString("producto1");

                            av_descripcion=respuestaJSON.getJSONObject("alumno").getString("descripcio");
                            av_medias=respuestaJSON.getJSONObject("alumno").getString("medidas");
                            av_precio=respuestaJSON.getJSONObject("alumno").getString("precio1");
                            av_precioe=respuestaJSON.getJSONObject("alumno").getString("precio2");
                            av_preciom=respuestaJSON.getJSONObject("alumno").getString("precio3");
                            sttok=respuestaJSON.getJSONObject("alumno").getString("hm");
                            sttokhg=respuestaJSON.getJSONObject("alumno").getString("hm");

                            lista_stok.add(sttokhg);
                        }
                        else if (generapedidoresul==1){
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

            for (int j=0;j<listaConta.clavetapete.size();j++){

               /* if(clavexd.equals(listaConta.clavetapete.get(j))){
                    //rows.add(new String[]{ listaConta.listacont.get(j),clavexd,av_descripcion,av_medias,"$ "+listaConta.precioXtapete.get(j)});


                }*/

            }


            if (x==2) {


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
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*
//INSERTAR EL PEDIDO REQUERIDO....
    public class ObtenerWebService4 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";

            if(params[1]=="3"){    // insert a base de dattos

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
                    jsonParam.put("idpedido", params[2]);
                    jsonParam.put("idcliente", params[3]);
                    jsonParam.put("fecha", params[4]);
                    jsonParam.put("idmetodopago", params[5]);
                    jsonParam.put("idmetodopago2", params[6]);
                    jsonParam.put("idmetodopago3", params[7]);
                    jsonParam.put("observaciones", params[8]);
                    jsonParam.put("idusuario", params[9]);
                    jsonParam.put("pedidos", params[10]);
                    jsonParam.put("idexpo", params[11]);
                    jsonParam.put("estatus", params[12]);
                    jsonParam.put("anticipo", params[13]);
                    jsonParam.put("anticipo2", params[14]);
                    jsonParam.put("anticipo3", params[15]);
                    jsonParam.put("total_pagar", params[16]);
                    jsonParam.put("entregado", params[17]);



                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();
                    //idclientepasa= params[2];
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
                            devuelve = "Pedido insertado correctamente";

                        } else if (generapedidoresul == 2) {
                            devuelve = "El Pedido no pudo insertarse";
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
            else

            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {

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
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*
//
    public class ObtenerWebService7 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            String cadenax = params[0];
            URL urlx = null; // Url de donde queremos obtener información
            String devuelvex ="";



            if(params[1]=="1"){    // Consulta de todos los alumnos

                try {
                    urlx = new URL(cadenax);
                    HttpURLConnection connection = (HttpURLConnection) urlx.openConnection(); //Abrir la conexión
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


                        int generapedidoresul = Integer.parseInt(resultJSON);
                        if (generapedidoresul==1){      // hay alumnos a mostrar
                            JSONArray solicitaJSON = respuestaJSON.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            //listItems = new ArrayList<String>();
                            for(int i=0;i<solicitaJSON.length();i++){


                            }

                            ///clientenum=solicitaJSON.length();

                        }
                        else if (generapedidoresul==1){
                            devuelvex = "No hay alumnos";
                        }
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelvex;


            }
            else if(params[1]=="2"){    // consulta por id

                try {
                    urlx = new URL(cadenax);
                    HttpURLConnection connection = (HttpURLConnection) urlx.openConnection(); //Abrir la conexión
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
                        int generapedidoresul= Integer.parseInt(resultJSON);
                        if (generapedidoresul==1){      // hay un alumno que mostrar
                            devuelvex = devuelvex + respuestaJSON.getJSONObject("alumno").getString("idAlumno") + " " +
                                    respuestaJSON.getJSONObject("alumno").getString("nombre") + " " +
                                    respuestaJSON.getJSONObject("alumno").getString("direccion");

                        }
                        else if (generapedidoresul==2){
                            devuelvex = "No hay alumnos";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelvex;


            }
            else if(params[1]=="3"){    // insert a base de dattos

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    urlx = new URL(cadenax);
                    urlConn = (HttpURLConnection) urlx.openConnection();
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
                    jsonParam.put("iddetallepedido", params[2]);
                    jsonParam.put("idpedido", params[3]);
                    jsonParam.put("clave", params[4]);
                    jsonParam.put("clave2", params[5]);
                    jsonParam.put("cantidad", params[6]);
                    jsonParam.put("preciovendido", params[7]);
                    jsonParam.put("estatus", params[8]);


                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();
                    //idclientepasa= params[2];
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
                        int generapedidoresul= Integer.parseInt(resultJSON);
                        if (generapedidoresul == 1) {      // hay un alumno que mostrar
                            devuelvex = "Pedido insertado correctamente";

                        } else if (generapedidoresul == 2) {
                            devuelvex = "El Pedido no pudo insertarse";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelvex;


            }
            else if(params[1]=="4"){    // update

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    urlx = new URL(cadenax);
                    urlConn = (HttpURLConnection) urlx.openConnection();
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
                        int generapedidoresul= Integer.parseInt(resultJSON);
                        if (generapedidoresul == 1) {      // hay un alumno que mostrar
                            devuelvex = "Alumno actualizado correctamente";

                        } else if (generapedidoresul == 2) {
                            devuelvex = "El alumno no pudo actualizarse";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelvex;

            }
            else if(params[1]=="5") {    // delete

                try {
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    urlx = new URL(cadenax);
                    urlConn = (HttpURLConnection) urlx.openConnection();
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
                        int generapedidoresul= Integer.parseInt(resultJSON);
                        if (generapedidoresul == 1) {      // hay un alumno que mostrar
                            devuelvex = "Alumno borrado correctamente";

                        } else if (generapedidoresul == 2) {
                            devuelvex = "No hay alumnos";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelvex;

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
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*

    public class ObtenerWebService8 extends AsyncTask<String,Void,String> {

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
                    jsonParam.put("producto1",params[2]);
                    jsonParam.put("hm", params[3]);

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

            }else if(params[1]=="5"){    // update

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
                    jsonParam.put("clave",params[2]);
                    jsonParam.put("stock", params[3]);

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


    private void EnviarCorreo(String valorenvio) {

        //      String sCorreo = xCorreo.getText().toString().trim();
        //      String sAsunto = xAsunto.getText().toString().trim();
        //      String sMensaje = xMensaje.getText().toString().trim();

        String sCorreo = listaConta.correoxcliente.toString();
        String sAsunto = "Pedido Tapetestufan    su numero de orden es "+numeroPedido.getText().toString();
        String sMensaje = valorenvio.toString();

        clsEnviaCorreo objCorreo = new clsEnviaCorreo(generaPedido3.this, sCorreo, sAsunto, sMensaje);
        objCorreo.execute();
    }

    private void EnviarCorreoUser(String valorenvio) {

        //      String sCorreo = xCorreo.getText().toString().trim();
        //      String sAsunto = xAsunto.getText().toString().trim();
        //      String sMensaje = xMensaje.getText().toString().trim();

        String sCorreo = "expo@tapetestufan.com";
        String sAsunto = "Pedido realizado el numero de orden es: "+numeroPedido.getText().toString();
        String sMensaje = valorenvio.toString();

        clsEnviaCorreo objCorreo = new clsEnviaCorreo(generaPedido3.this, sCorreo, sAsunto, sMensaje);
        objCorreo.execute();
    }

    private void enviawhatsapp(String valorEnvio){
        String url = valorEnvio;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}
