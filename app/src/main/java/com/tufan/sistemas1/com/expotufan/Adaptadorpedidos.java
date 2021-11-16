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

public class Adaptadorpedidos extends BaseAdapter{
  private static LayoutInflater inflater= null;

  Context contexto;
    ArrayList<String> endatospedido;
    ArrayList<String> endatosfecha;
    ArrayList<String> endatosnombre;
    ArrayList<String> endatostotalcompra;

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

    public Adaptadorpedidos(Context contexto, ArrayList<String>endatospedido,ArrayList<String>endatosfecha,ArrayList<String>endatosnombre,ArrayList<String>endatostotalcompra) {
        this.contexto = contexto;
        this.endatospedido=endatospedido;
        this.endatosfecha=endatosfecha;
        this.endatosnombre=endatosnombre;
        this.endatostotalcompra=endatostotalcompra;

        //this.dadosimg = dadosimg;
        inflater=(LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return endatospedido.size();
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

        final View vistaxpedido = inflater.inflate(R.layout.elemento_listapedido, null);
        TextView enpedido=(TextView)vistaxpedido.findViewById(R.id.txtclient);
        TextView enfecha=(TextView)vistaxpedido.findViewById(R.id.entxtfecha);
        TextView ennombrecliente=(TextView)vistaxpedido.findViewById(R.id.txtclientRFC);
        MoneyTextView tcompra=(MoneyTextView)vistaxpedido.findViewById(R.id.totalcompra);

        enpedido.setText(endatospedido.get(position));
        enfecha.setText(endatosfecha.get(position));
        ennombrecliente.setText(endatosnombre.get(position));
        int valor=Integer.parseInt(endatostotalcompra.get(position));
        tcompra.setAmount(valor);

        return vistaxpedido;
    }
}
