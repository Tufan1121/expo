package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.github.snowdream.android.widget.SmartImageView;

import java.util.ArrayList;

public class Adaptador_inv extends BaseAdapter{
  private static LayoutInflater inflater= null;

  Context contexto;
    ArrayList<String> datosdes;


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

    //int []dadosimg;

    public Adaptador_inv(Context contexto,  ArrayList<String> datosdes) {
        this.contexto = contexto;
        this.datosdes = datosdes;
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return datosdes.size();
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

        final View vista = inflater.inflate(R.layout.elemento_lista_inv, null);

        TextView titulo = vista.findViewById(R.id.txtclient);
        titulo.setText(datosdes.get(position));

        return vista;
    }
}
