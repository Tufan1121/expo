package com.tufan.sistemas1.com.expotufan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import static com.tufan.sistemas1.com.expotufan.LoginActivity.idusuariotufan;

public class generaPedido2 extends AppCompatActivity implements View.OnClickListener{
    String enviopedidocorreo;
    String envioWhatsApp;
    Spinner metodopago;
    Spinner metodopago2;
    Spinner metodopago3;
    Button previo;
    Button guardaP;
    EditText comentarios;
    TextView numeroPedido;
    TextView msg2;
    TextView lbpedido;
    TextView lbtotalp;
    TextView lbdebep;
    TextView lbanti;
    TextView lbmetodo;
    TextView lbbase;

    Spinner estatuspedido;
    MoneyTextView totalpagarpedido;
    MoneyTextView debeporpagar;
    EditText cuanto_anticipo;
    EditText cuanto_anticipo2;
    EditText cuanto_anticipo3;
    Button salirpedido2;
    int finaliza2;
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
    String _numeroped;
    //Variables para campos digitos

    EditText digi1;
    EditText digi2;
    EditText digi3;


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
    private final String []header2={"","","",""};
    private final String[]header={"Cantidad","Clave","Descripcion","Medidas","Precio"};
    private String shorttext="hola";
    private final String longtext="WWW.TAPETESTUFAN.COM";
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

        if(finaliza2==0){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("¿Deseas Regresar a lista de Tapetes?");
            dialogo.setMessage("Quieres agregar algo mas...!");
            dialogo.setCancelable(false);
            dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    //codigo
                    Intent regresaalista= new Intent(generaPedido2.this,listatapetes2.class);
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
        }else{
            finish();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genera_pedido2);
        //llenaarray();

        lista = listaConta.clavetapete;
        kx=listaConta.idclientesp;


        metodopago= findViewById(R.id.spinnerGP_metodo);
        metodopago2= findViewById(R.id.spinnerGP_metodo2);
        metodopago3= findViewById(R.id.spinnerGP_metodo3);

        lbpedido= findViewById(R.id.lb_pedido);
        lbtotalp= findViewById(R.id.lb_totalp);
        lbdebep= findViewById(R.id.lb_debep);
        lbanti= findViewById(R.id.lb_anti);
        lbmetodo= findViewById(R.id.lb_metodo);
        lbbase= findViewById(R.id.lb_obse);

        observaciones= findViewById(R.id.editText_observacion);
        //previo=(Button)findViewById(R.id.btn_GP_previo);
        guardaP= findViewById(R.id.btn_GP_guardar);
        comentarios= findViewById(R.id.editText_observacion);
        numeroPedido= findViewById(R.id.txt_GP_numeropedido);
        //estatuspedido=(Spinner)findViewById(R.id.sp_estatuspedido);
        totalpagarpedido= findViewById(R.id.txt_totalpagarM);
        debeporpagar= findViewById(R.id.txtdebeporpagar);
        cuanto_anticipo= findViewById(R.id.txt_anticipo);
        cuanto_anticipo2= findViewById(R.id.txt_anticipo2);
        cuanto_anticipo3= findViewById(R.id.txt_anticipo3);
        salirpedido2= findViewById(R.id.btnSalirpedido2);
        msg2= findViewById(R.id.txt_msg2);
        pendiente= findViewById(R.id.checkBox4pendiente);
        entregado= findViewById(R.id.checkBox_entregado);
        pagartotalP=Integer.parseInt(listaConta.totalpagartodo);
        cuanto_anticipo.setEnabled(false);
        cuanto_anticipo2.setEnabled(false);
        cuanto_anticipo3.setEnabled(false);



        //Digitos
        digi1= findViewById(R.id.digi1);
        digi2= findViewById(R.id.digi2);
        digi3= findViewById(R.id.digi3);
        digi1.setVisibility(View.INVISIBLE);
        digi2.setVisibility(View.INVISIBLE);
        digi3.setVisibility(View.INVISIBLE);

        finaliza2=0;

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
        salirpedido2.setOnClickListener(this);

        numeroLista=lista.size();
        pendiente.setChecked(true);
            for(int i=0;i<numeroLista;i++){
                    //aqui hacemos consulta de los tapetes que van a ir en el pedido
                    claveconsulta= lista.get(i);
                    //String claveconsulta=clavetapete.getText().toString();
                    hiloconexion2 = new ObtenerWebService();
                    String cadenallamada2 = GET_BY_ID + "?clave=" + claveconsulta;
                    hiloconexion2.execute(cadenallamada2,"2");
            }

            pendiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pendiente.setChecked(true);
                    entregado.setChecked(false);
                    entrega="1";
                }
            });

            entregado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    entregado.setChecked(true);
                    pendiente.setChecked(false);
                    entrega="2";
                }
            });

        cuanto_anticipo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(cuanto_anticipo.getText().toString().trim().equalsIgnoreCase("")){
                    cuanto_anticipo.setText("0");
                    int val1=Integer.parseInt(cuanto_anticipo.getText().toString());
                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);

                }else{

                    int val1=Integer.parseInt(cuanto_anticipo.getText().toString());
                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);
                }
            }

        });
        cuanto_anticipo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(cuanto_anticipo2.getText().toString().trim().equalsIgnoreCase("")){
                    cuanto_anticipo2.setText("0");
                    int val1=Integer.parseInt(cuanto_anticipo2.getText().toString())+Integer.parseInt(cuanto_anticipo.getText().toString());

                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);



                }else{

                    int val1=Integer.parseInt(cuanto_anticipo2.getText().toString())+Integer.parseInt(cuanto_anticipo.getText().toString());
                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);
                }
            }
        });
        cuanto_anticipo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(cuanto_anticipo3.getText().toString().trim().equalsIgnoreCase("")){
                    cuanto_anticipo3.setText("0");
                    int val1=Integer.parseInt(cuanto_anticipo3.getText().toString())+Integer.parseInt(cuanto_anticipo.getText().toString())+Integer.parseInt(cuanto_anticipo2.getText().toString());
                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);
                }else{

                    int val1=Integer.parseInt(cuanto_anticipo3.getText().toString())+Integer.parseInt(cuanto_anticipo.getText().toString())+Integer.parseInt(cuanto_anticipo2.getText().toString());
                    int paga=pagartotalP-val1;
                    debeporpagar.setAmount(paga);
                }
            }
        });

        metodopago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(generaPedido.this,"position:"+position,Toast.LENGTH_SHORT).show();
                if(position==0){
                    try{
                        cuanto_anticipo.setText("");

                    }catch (Exception e){
                        e.getMessage();
                    }
                    cuanto_anticipo.setEnabled(false);

                }else{
                    cuanto_anticipo.setEnabled(true);

                }


                //Publica texto para los 4 digitos
                /*
                if(position==4){
                    try{
                        digi1.setVisibility(View.VISIBLE);
                        digi1.requestFocus();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }else{
                    digi1.setVisibility(View.INVISIBLE);
                }
                */

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        metodopago2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(generaPedido.this,"position:"+position,Toast.LENGTH_SHORT).show();
                if(position==0){
                    try{
                        cuanto_anticipo2.setText("");

                    }catch (Exception e){
                        e.getMessage();
                    }
                    cuanto_anticipo2.setEnabled(false);

                }else{
                    cuanto_anticipo2.setEnabled(true);

                }

                //Publica texto para los 4 digitos/*
                /*
                if(position==4){
                    try{
                        digi2.setVisibility(View.VISIBLE);
                        digi2.requestFocus();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }else{
                    digi2.setVisibility(View.INVISIBLE);
                }
                */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        metodopago3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(generaPedido.this,"position:"+position,Toast.LENGTH_SHORT).show();
                if(position==0){

                    try{
                        cuanto_anticipo3.setText("");
                    }catch (Exception e){
                        e.getMessage();
                    }

                    cuanto_anticipo3.setEnabled(false);

                }else{
                    cuanto_anticipo3.setEnabled(true);
                }

                //Publica texto para los 4 digitos
                /*
                if(position==4){
                    try{
                        digi3.setVisibility(View.VISIBLE);
                        digi3.requestFocus();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }else{
                    digi3.setVisibility(View.INVISIBLE);
                }
                */

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        msg2.setText("Generado ya puede cerrar");
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
    public void onClick(View v) {
        switch (v.getId()){

                case R.id.btn_GP_guardar:
                        /*
                        //Valida 4 digitos
                        if((metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
                                String _d1;
                                _d1=digi1.getText().toString();
                                 if(_d1.length()==0) {
                                     digi1.requestFocus();
                                     Toast.makeText(this, "Faltan 4 Digitos ", Toast.LENGTH_LONG).show();
                                     break;
                             }
                        }

                    if((metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
                        String _d2;
                        _d2=digi2.getText().toString();
                        if(_d2.length()==0) {
                            digi2.requestFocus();
                            Toast.makeText(this, "Faltan 4 Digitos", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                    if((metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
                        String _d3;
                        _d3=digi3.getText().toString();
                        if(_d3.length()==0) {
                            digi3.requestFocus();
                            Toast.makeText(this, "Faltan 4 Digitos", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    */


                        if(observaciones.getText().toString().trim().equalsIgnoreCase("")){
                            observaciones.requestFocus();
                            Toast.makeText(this,"Coloca Observaciones del Pedido",Toast.LENGTH_LONG).show();

                        }else if (metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Metodo de Pago")) {
                            Toast.makeText(generaPedido2.this, "Selecciona Metodo de pago",Toast.LENGTH_SHORT).show();
                            metodopago.setFocusable(true);
                            metodopago.setFocusable(true);
                            metodopago.setFocusableInTouchMode(true);
                            metodopago.requestFocus();
                        }else if(cuanto_anticipo.getText().toString().trim().equalsIgnoreCase("")) {
                            cuanto_anticipo.requestFocus();
                            Toast.makeText(generaPedido2.this, "Coloca el pago total o anticipo", Toast.LENGTH_LONG).show();

                        }else if(!metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Metodo de Pago") && cuanto_anticipo2.getText().toString().equalsIgnoreCase("0")){
                            cuanto_anticipo2.requestFocus();
                            Toast.makeText(generaPedido2.this, "Coloca la cantidad del otro metodo de  pago ", Toast.LENGTH_LONG).show();

                        }else if(!metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Metodo de Pago") && cuanto_anticipo3.getText().toString().equalsIgnoreCase("0")){
                            cuanto_anticipo3.requestFocus();
                            Toast.makeText(generaPedido2.this, "Coloca la cantidad del otro metodo de  pago ", Toast.LENGTH_LONG).show();

                        }else{
                            //insert para agregar el pedido
                            insertarpedido();
                            //consulta modificaciones del inventario xd x dx d x d dx

                            //for(int i=0;i<numeroLista;i++){
                          int i=0;

                            while(i<numeroLista){

                                String claveconsultaddd=lista.get(i);
                                String claveinventa=lista.get(i);
                                insertardetallepedido(i,claveinventa);

                                i++;
                                //}

                            }
                            //insert para agregar el detalle de los tapetes que se van a pasar

                            //21.01.2022
                            enviopedidocorreo=appRutaservidor.IP+"/pedidoibodegas.php?pedido="+listaConta.pedidoverpdf;
                            envioWhatsApp="https://api.whatsapp.com/send?phone=52"+listaConta.WhatsApp+"&text=Hola,"+" este es tu pedido: "+appRutaservidor.IP+"/pedidoibodegas.php?pedido="+listaConta.pedidoverpdf+" Gracias por tu compra.";
                            enviawhatsapp(envioWhatsApp);

                            //EnviarCorreo(enviopedidocorreo);
                            //EnviarCorreoUser(enviopedidocorreo);

                            guardaP.setEnabled(false);
                            msg2.setVisibility(View.VISIBLE);

                            guardaP.setVisibility(View.INVISIBLE);

                            lbpedido.setVisibility(View.INVISIBLE);
                            lbtotalp.setVisibility(View.INVISIBLE);
                            lbdebep.setVisibility(View.INVISIBLE);
                            lbanti.setVisibility(View.INVISIBLE);
                            lbmetodo.setVisibility(View.INVISIBLE);
                            lbbase.setVisibility(View.INVISIBLE);
                            metodopago.setVisibility(View.INVISIBLE);
                            metodopago2.setVisibility(View.INVISIBLE);
                            metodopago3.setVisibility(View.INVISIBLE);
                            observaciones.setVisibility(View.INVISIBLE);
                            comentarios.setVisibility(View.INVISIBLE);
                            numeroPedido.setVisibility(View.INVISIBLE);
                            totalpagarpedido.setVisibility(View.INVISIBLE);
                            debeporpagar.setVisibility(View.INVISIBLE);
                            cuanto_anticipo.setVisibility(View.INVISIBLE);
                            cuanto_anticipo2.setVisibility(View.INVISIBLE);
                            cuanto_anticipo3.setVisibility(View.INVISIBLE);
                            pendiente.setVisibility(View.INVISIBLE);
                            entregado.setVisibility(View.INVISIBLE);

                            //Digitos pago con tarjeta
                            digi1.setVisibility(View.INVISIBLE);
                            digi2.setVisibility(View.INVISIBLE);
                            digi3.setVisibility(View.INVISIBLE);


                            finaliza2=1;
                            Intent verpdfterminado= new Intent(this, verpedf2ibodegas.class);
                            startActivity(verpdfterminado);

                            //***************************************************************************************************************
                            //***************************************************************************************************************



                            //finish();

                        }
                    break;
            case R.id.btnSalirpedido2:
                if(finaliza2==1){
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
        String i= numeropedido + listaConta.idusuariotufan;

        maxpedidopasaadetalle=Integer.toString(numeropedido);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(new Date());
        //*************METODO DE PAGO***************************
        String metododepagod="";

        if((metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("01 Efectivo"))){
            metododepagod="1";
        }
        if((metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("02 Cheque Nominativo"))){
            metododepagod="2";
        }
        if((metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("03 Transferencia Electronica"))){

            metododepagod="3";
        }
        if((metodopago.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
            metododepagod="4";
        }
//*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-**-*-*-*-
        String metododepagod2="";

        if(metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Metodo de Pago")){
            metododepagod2="0";
        }else{
            if((metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("01 Efectivo"))){
                metododepagod2="1";
            }
            if((metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("02 Cheque Nominativo"))){
                metododepagod2="2";
            }
            if((metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("03 Transferencia Electronica"))){
                metododepagod2="3";
            }
            if((metodopago2.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
                metododepagod2="4";
            }
        }

        //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-**-*-*-*-
        String metododepagod3="";

        if(metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("Selecciona Metodo de Pago")){
            metododepagod3="0";
        }else{
            if((metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("01 Efectivo"))){
                metododepagod3="1";
            }
            if((metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("02 Cheque Nominativo"))){
                metododepagod3="2";
            }
            if((metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("03 Transferencia Electronica"))){
                metododepagod3="3";
            }
            if((metodopago3.getSelectedItem().toString().trim().equalsIgnoreCase("04 Tarjeta de Credito o Debito"))){
                metododepagod3="4";
            }
        }
//*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

        //******************************************************}
        listaConta.pedidoverpdf=numeroPedido.getText().toString();
        //String entregado=Integer.toString(entrega);
        //hiloconexion4.execute(INSERTPEDIDO,"3",i,kx,fechaComoCadena,metododepagod.toString(),metododepagod2.toString(),metododepagod3.toString(),observaciones.getText().toString(),listaConta.idusuariotufan.toString(),numeroPedido.getText().toString(),listaConta.id_expo.toString(),listaConta.estatusselect,cuanto_anticipo.getText().toString(),cuanto_anticipo2.getText().toString(),cuanto_anticipo3.getText().toString(),listaConta.totalpagartodo.toString(),entrega,_di1,_di2,_di3);   // Parámetros que recibe doInBackground


        hiloconexion4.execute(INSERTPEDIDO,"3",i,kx,fechaComoCadena, metododepagod, metododepagod2, metododepagod3,observaciones.getText().toString(), listaConta.idusuariotufan,numeroPedido.getText().toString(), listaConta.id_expo,listaConta.estatusselect,cuanto_anticipo.getText().toString(),cuanto_anticipo2.getText().toString(),cuanto_anticipo3.getText().toString(), listaConta.totalpagartodo,entrega);   // Parámetros que recibe doInBackgroun

    }

    private void insertardetallepedido(int pordondeesta,String thisclave) {

        _numeroped=(listaConta.encabrzadopedido+"-"+numeropedido+listaConta.idusuariotufan+"B");
        numerodetallepedido++;
        hiloconexion7 = new ObtenerWebService7();
        String idetalle= numerodetallepedido +listaConta.idusuariotufan;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(new Date());
        //hiloconexion7.execute(INSERTdetallePEDIDO,"3",idetalle,maxpedidopasaadetalle+listaConta.idusuariotufan,thisclave, listaConta.clavelarga.get(pordondeesta), listaConta.listacont.get(pordondeesta), listaConta.precioseleccionado.get(pordondeesta),listaConta.estatusselect);   // Parámetros que recibe doInBackground
        hiloconexion7.execute(INSERTdetallePEDIDO,"3",idetalle,maxpedidopasaadetalle+listaConta.idusuariotufan,thisclave, listaConta.clavelarga.get(pordondeesta), listaConta.listacont.get(pordondeesta), listaConta.precioseleccionado.get(pordondeesta),listaConta.estatusselect);   // Parámetros que recibe doInBackground

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

        if (observaciones.getText().toString().equalsIgnoreCase("")){

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
            //numeroPedido.setText(listaConta.encabrzadopedido+numeropedido+listaConta.idusuariotufan+"B");
            numeroPedido.setText(listaConta.encabrzadopedido+"-"+numeropedido+listaConta.idusuariotufan+"B");

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
                    /*

                    jsonParam.put("dig1",params[18]);
                    jsonParam.put("dig2",params[19]);
                    jsonParam.put("dig3",params[20]);
                    */


                    // Envio los parámetros post.
                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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
                            //devuelve = "Pedido insertado correctamente "+numeroPedido.getText().toString();
                            //devuelve = devuelve2+numeroPedido.getText().toString();
                            String devuelve2 ="http://tufanvpn.dyndns-ip.com:444/notificaciones/pedidoibodegas.php?pedido=";
                            devuelve2 = devuelve2+numeroPedido.getText().toString();

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
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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
                            devuelvex = "Pedido insertado correctamente 1";

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
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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
                            devuelvex = "Alumno actualizado correctamente 2";

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
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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
                            new OutputStreamWriter(os, StandardCharsets.UTF_8));
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

        String sCorreo = listaConta.correoxcliente;
        String sAsunto = "Pedido Tapetestufan    su numero de orden es "+numeroPedido.getText().toString();
        String sMensaje = valorenvio;

        clsEnviaCorreo objCorreo = new clsEnviaCorreo(generaPedido2.this, sCorreo, sAsunto, sMensaje);
        objCorreo.execute();
    }

    private void EnviarCorreoUser(String valorenvio) {

        //      String sCorreo = xCorreo.getText().toString().trim();
        //      String sAsunto = xAsunto.getText().toString().trim();
        //      String sMensaje = xMensaje.getText().toString().trim();

        String sCorreo = "expo@tapetestufan.com";
        String sAsunto = "Pedido realizado el numero de orden es: "+numeroPedido.getText().toString();
        String sMensaje = valorenvio;

        clsEnviaCorreo objCorreo = new clsEnviaCorreo(generaPedido2.this, sCorreo, sAsunto, sMensaje);
        objCorreo.execute();
    }

    private void enviawhatsapp(String valorEnvio){
        String url = valorEnvio;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}
