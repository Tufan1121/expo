package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.github.snowdream.android.widget.SmartImageView;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.util.ArrayList;

public class Adaptador_x_nombre_b extends BaseAdapter{
  private static LayoutInflater inflater= null;

  Context contexto;
    ArrayList<String> datos;
    ArrayList<String> datosdes;
    ArrayList<String> datospmedida;
    ArrayList<String> datospstok;
    ArrayList<String> datosimg;
    ArrayList<String> datosdiseno;
    ArrayList<String> datosprecio1;
    ArrayList<String> datosprecio2;
    ArrayList<String> datosprecio3;

    //Almacen
    ArrayList<String> datosalmacen;



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

    public int totalp1=0;
    public int totalp2=0;
    public int totalp3=0;


    public Adaptador_x_nombre_b(Context contexto, ArrayList<String> datos, ArrayList<String> datosdes, ArrayList<String> datospmedida, ArrayList<String> datospstok, ArrayList<String>datosimg, ArrayList<String>datosdiseno, ArrayList<String>datosprecio1, ArrayList<String>datosprecio2, ArrayList<String>datosprecio3, ArrayList<String>datosalmacen) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosdes = datosdes;
        this.datospmedida = datospmedida;
        this.datospstok = datospstok;
        this.datosimg=datosimg;
        this.datosdiseno=datosdiseno;
        this.datosprecio1=datosprecio1;
        this.datosprecio2=datosprecio2;
        this.datosprecio3=datosprecio3;
        this.datosalmacen=datosalmacen;

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

        final View vista1 = inflater.inflate(R.layout.elemento_lista_bg, null);

        final TextView xlaclave=(TextView)vista1.findViewById(R.id.txt_clave_col);
        final TextView descrip=(TextView)vista1.findViewById(R.id.txtclient);
        final TextView medida= (TextView)vista1.findViewById(R.id.txt_medida_col);
        final TextView stoxk= (TextView)vista1.findViewById(R.id.txt_stok);

        //Almacen
        final TextView xalmacen=(TextView) vista1.findViewById(R.id.txtalmacen);

        final MoneyTextView xprecio1=(MoneyTextView) vista1.findViewById(R.id.txt_precio1);
        final MoneyTextView xprecio2=(MoneyTextView) vista1.findViewById(R.id.txt_precio2);
        final MoneyTextView xprecio3=(MoneyTextView) vista1.findViewById(R.id.txt_precio3);

        smartImageView=(SmartImageView)vista1.findViewById(R.id.imgver);

        xlaclave.setText(datos.get(position));
        descrip.setText(datosdes.get(position)+" "+datosdiseno.get(position));
        medida.setText(datospmedida.get(position));
        stoxk.setText(datospstok.get(position));
        xalmacen.setText(datosalmacen.get(position));

        if(datosprecio1.get(position).equalsIgnoreCase("0")){
            xprecio1.setAmount(0);

        }else{
            totalp1=Integer.parseInt(datosprecio1.get(position));
            xprecio1.setAmount(totalp1);

        }


        if(datosprecio2.get(position).equalsIgnoreCase("0")){
            xprecio2.setAmount(0);

        }else{
            totalp2=Integer.parseInt(datosprecio2.get(position));
            xprecio2.setAmount(totalp2);

        }

        if(datosprecio3.get(position).equalsIgnoreCase("0")){
            xprecio3.setAmount(0);

        }else{
            totalp3=Integer.parseInt(datosprecio3.get(position));
            xprecio3.setAmount(totalp3);

        }

//*****************************************************************************************************************************
//************checar imagen por escaneo de tapete solo vista

        String urlfinal=appRutaservidor.IPIMG2+datosimg.get(position);
        Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());

        smartImageView.setImageUrl(urlfinal,rect);

        return vista1;
    }
}
