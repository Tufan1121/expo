package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.github.snowdream.android.widget.SmartImageView;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.util.ArrayList;

public class Adaptador2 extends BaseAdapter{
  private static LayoutInflater inflater= null;

  Context contexto;
ArrayList<String> datos;
    ArrayList<String> datosdes;
    ArrayList<String> datospl;
    ArrayList<String> datospe;
    ArrayList<String> datospm;
    ArrayList<String> datosmedida;
    ArrayList<String> datosporciento;


    SmartImageView smartImageView;
//precio lista
    public int total;
    public int miles;
    public int reciduo;
    public String totalString;
    RequestQueue requestQueue;
    //precio listaexpo
    public int totalexpo;
    public int milesexpo;
    public int reciduoexpo;
    public String totalStringexpo;


    //precio lista mayoreo
    public int totalm;
    public int milesm;
    public int reciduom;
    public String totalStringm;


    public int paradehm;

    //int []dadosimg;

    public Adaptador2(Context contexto, ArrayList<String> datos, ArrayList<String> datosdes, ArrayList<String> datospl, ArrayList<String> datospe, ArrayList<String> datospm, ArrayList<String>datosmedida,ArrayList<String> datosporciento) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosdes = datosdes;
        this.datospl = datospl;
        this.datospe = datospe;
        this.datospm = datospm;
        this.datosmedida= datosmedida;
        this.datosporciento=datosporciento;
        //this.dadosimg = dadosimg;
        inflater=(LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        System.out.print("que pasa aauqi ");
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView titulo = (TextView) vista.findViewById(R.id.txtclient);
        TextView duracion = (TextView) vista.findViewById(R.id.tvduracion);
        final TextView director = (TextView) vista.findViewById(R.id.txtclientRFC);
        TextView mimedidatapete=(TextView)vista.findViewById(R.id.mimedida);
        //final ImageView imagen = (ImageView) vista.findViewById(R.id.imagen);
        smartImageView=(SmartImageView)vista.findViewById(R.id.imgver);
        final Button botonmas = (Button) vista.findViewById(R.id.mas);
        final Button botonmenos = (Button) vista.findViewById(R.id.menos);
        final CheckBox preciol = (CheckBox) vista.findViewById(R.id.checkBoxp);
        final CheckBox precioe = (CheckBox) vista.findViewById(R.id.checkBox2);
        final CheckBox preciom = (CheckBox) vista.findViewById(R.id.checkBox3);
        final TextView porciento=(TextView)vista.findViewById(R.id.txt_porciento);
        final MoneyTextView moneda1=(MoneyTextView)vista.findViewById(R.id.monedalista);
        final MoneyTextView moneda2=(MoneyTextView)vista.findViewById(R.id.monedalista2);
        final MoneyTextView moneda3=(MoneyTextView)vista.findViewById(R.id.monedalista3);
        //appRutaservidor.IPIMGpedido=appRutaservidor.IPIMpedido;
        final EditText masdescuanto=(EditText)vista.findViewById(R.id.masdescuento);
        final Button aplka_descuento = (Button)vista.findViewById(R.id.btn_aplicardes);
        final TextView cuantos = (TextView) vista.findViewById(R.id.txtcuantos);
        RatingBar calificacion = (RatingBar) vista.findViewById(R.id.ratingBarPel);

        cuantos.setText(listaConta.listacont.get(position));


        //listaConta.listacont.add("1");
        titulo.setText(datosdes.get(position));
        director.setText(datos.get(position));
        mimedidatapete.setText(datosmedida.get(position));
        //duracion.setText(""+ datos.get(position));
        int botonmenosenable = Integer.parseInt(listaConta.listacont.get(position));

        if (botonmenosenable == 1) {

            botonmenos.setEnabled(false);
        } else {
            botonmenos.setEnabled(true);

        }

        if(datosporciento.get(position).equalsIgnoreCase("0")){
            porciento.setText("");
        }else{
            porciento.setText("%"+datosporciento.get(position)+" Adicional");
        }

//*****************************************************************************************************************************
//************checar imagen por escaneo de tapete solo vista

        String urlfinal=listaConta.imgserver.get(position);
        Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());

        smartImageView.setImageUrl(urlfinal,rect);

//******************************************************************************************************************************
        total=Integer.parseInt(datospl.get(position));
        miles=total/1000;
        reciduo=total%1000;
        totalString = miles+","+reciduo+".00";

        //preciol.setText("$ "+totalString.toString());
        moneda1.setAmount(total);
        //-----------------------------------------------------------------------------------------------------------------------------------------
        totalexpo=Integer.parseInt(datospe.get(position));
        milesexpo=totalexpo/1000;
        reciduoexpo=totalexpo%1000;
        totalStringexpo = milesexpo+","+reciduoexpo+".00";


        //precioe.setText("$ "+totalStringexpo.toString());
        moneda2.setAmount(totalexpo);
        //------------------------------------------------------------------------------------------------------------------------------------

        totalm=Integer.parseInt(datospm.get(position));
        milesm=totalm/1000;
        reciduom=totalm%1000;
        totalStringm = milesm+","+reciduom+".00";
        moneda3.setAmount(totalm);
        masdescuanto.setText(Integer.toString(totalm));

        //preciom.setText("$ "+totalStringm.toString());

        cuantos.setText(listaConta.listacont.get(position));

        //imagen.setImageResource(dadosimg[position]);
        //calificacion.setProgress(Integer.valueOf(datos.get(position)));

        String opcx= listaConta.opclist.get(position);
        paradehm=0;
        paradehm=Integer.parseInt(listaConta.listahm.get(position));


        if(opcx.equals("1")){
            ///ESTE ES PRECIO LISTA
            preciol.setChecked(true);
            precioe.setChecked(false);
            preciom.setChecked(false);
            masdescuanto.setVisibility(View.INVISIBLE);
            aplka_descuento.setVisibility(View.INVISIBLE);

        }
        if(opcx.equals("2")){
            ///ESTE ES PRECIO EXPO
            preciol.setChecked(false);
            precioe.setChecked(true);
            preciom.setChecked(false);
            masdescuanto.setVisibility(View.INVISIBLE);
            aplka_descuento.setVisibility(View.INVISIBLE);

        }

        if(opcx.equals("3")){
            ///ESTE ES PRECO MAYOREO
            preciol.setChecked(false);
            precioe.setChecked(false);
            preciom.setChecked(true);
            masdescuanto.setVisibility(View.VISIBLE);
            aplka_descuento.setVisibility(View.VISIBLE);

        }

        aplka_descuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masdesdes= masdescuanto.getText().toString();
                int nuevoprecio= Integer.parseInt(masdesdes);
                listaConta.precioseleccionado.set(position,masdesdes);
                moneda3.setAmount(nuevoprecio);
                datospm.set(position,masdesdes);
                listaConta.totalpago2();
            }
        });

        preciol.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View v) {
                preciol.setChecked(true);
                precioe.setChecked(false);
                preciom.setChecked(false);
              masdescuanto.setVisibility(View.INVISIBLE);
              aplka_descuento.setVisibility(View.INVISIBLE);
                listaConta.precioseleccionado.set(position,datospl.get(position));
                listaConta.totalpago2();
                listaConta.opclist.set(position,"1");
              }


        });

        precioe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preciol.setChecked(false);
                precioe.setChecked(true);
                preciom.setChecked(false);
                masdescuanto.setVisibility(View.INVISIBLE);
                aplka_descuento.setVisibility(View.INVISIBLE);
                    listaConta.precioseleccionado.set(position, datospe.get(position));
                    listaConta.totalpago2();
                    listaConta.opclist.set(position, "2");


            }
        });
        preciom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preciol.setChecked(false);
                precioe.setChecked(false);
                preciom.setChecked(true);
                masdescuanto.setVisibility(View.VISIBLE);
                aplka_descuento.setVisibility(View.VISIBLE);
                listaConta.precioseleccionado.set(position,datospm.get(position));
                listaConta.totalpago2();
                listaConta.opclist.set(position,"3");
            }
        });



        botonmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int subir1= Integer.parseInt(cuantos.getText().toString());

                if(paradehm<=subir1){
                    botonmas.setEnabled(false);
                }else{
                    botonmas.setEnabled(true);
                    int sumar = subir1+1;
                    String sumarstring=Integer.toString(sumar);

                    listaConta.listacont.set(position,sumarstring);
                    cuantos.setText(sumarstring);
                    listaConta.totalpago2();
                    botonmenos.setEnabled(true);
                }




            }
        });

        botonmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                botonmas.setEnabled(true);

                    int bajar1= Integer.parseInt(cuantos.getText().toString());

                    int restar = bajar1-1;
                    String restarstring=Integer.toString(restar);

                    listaConta.listacont.set(position,restarstring);
                    cuantos.setText(restarstring);

                    listaConta.totalpago2();

                int checarquesea1=Integer.parseInt(listaConta.listacont.get(position));
                //si es 1 el boton "botonmenos" se deshabilitara mano
                if (checarquesea1<2){
                    botonmenos.setEnabled(false);

                }



            }
        });
        listaConta.totalpago2();
        return vista;
    }
}
