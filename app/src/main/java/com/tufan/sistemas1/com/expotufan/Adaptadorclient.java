package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.github.snowdream.android.widget.SmartImageView;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.util.ArrayList;

public class Adaptadorclient extends BaseAdapter{
  private static LayoutInflater inflater= null;

  Context contexto;
    ArrayList<String> cdatosname;
    ArrayList<String> cdatosrfc;
    ArrayList<String> cdatosC;
    ArrayList<String> cdatosDIR;


    public Adaptadorclient(Context contexto, ArrayList<String>cdatosname, ArrayList<String>cdatosrfc, ArrayList<String>cdatosC, ArrayList<String>cdatosDIR) {
        this.contexto = contexto;
        this.cdatosname=cdatosname;
        this.cdatosrfc=cdatosrfc;
        this.cdatosC=cdatosC;
        this.cdatosDIR=cdatosDIR;

        //this.dadosimg = dadosimg;
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return cdatosname.size();
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

        final View vistaxclient = inflater.inflate(R.layout.elemento_listauser, null);

        TextView nombreclient= vistaxclient.findViewById(R.id.txtclient);
        TextView clienterfc= vistaxclient.findViewById(R.id.txtclientRFC);
        TextView clientecorreo= vistaxclient.findViewById(R.id.txtclientC);
        TextView clientedir= vistaxclient.findViewById(R.id.txtclientDIR);

        nombreclient.setText(cdatosname.get(position));
        clienterfc.setText(cdatosrfc.get(position));
        clientecorreo.setText(cdatosC.get(position));
        clientedir.setText(cdatosDIR.get(position));

        return vistaxclient;
    }
}
