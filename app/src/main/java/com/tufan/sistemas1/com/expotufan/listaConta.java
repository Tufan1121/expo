package com.tufan.sistemas1.com.expotufan;

import java.util.ArrayList;

public class listaConta {
    //*-*-*-*-*-*--*-*-*-*-*---**-*--*-*-**-1*1-*1-*1-1*1-*1-*1-*1-*1*-1-*21*-21-*21-*12-*21-*2-1*
    public static String imgver1;
    public static String nombresT;
    public static String medidasT;

    //********-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //TODO... ID cliente SELECCIONADO
    public static String idclientesp;

    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-
    //TODO... VARIABLE QUE CLAVE QUE PASA DE BUSQUEDA DE IVENTARIO

    public static String claveseleccionada;

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    //TODO... VARIABLES QUE SE USAN PARA HACER COTIZACION

    public static String nombre_cotiza;
    public static String apellido_cotiza;
    public static String telefono_cotiza;
    public static String correo_cotiza;
    //-**-*-*-*-*-*-*--*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*--*--*-*--**-*-*-*-*-
    //TODO varibles para sacar que tipo de expo estamos
    public static String encabrzadopedido;
    public static String nombre_Expo;
    public static String id_expo;
    public static String almacen1;
    //
    //*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-**-
    public static String idusuariotufan;
    public static String usuariopass;
    //***-*-**-*-*-*--*-**-*-*-*-*-*-*-*-*-*-*-*--*--*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-
    //Variable predido*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    public static String pedidoverpdf;
    //*--*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    //*-*-*-VARIABLE SACAR CORREO DE USUARIO XD **--*-*-*-**-*-*--
    public static ArrayList<String> correoenviar= new ArrayList<String>();
    public static String correoxcliente;
    //*-*-*-*---*-*------*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

    //Variable para WhatsApp
    public static ArrayList<String> WhatsAppEnviar= new ArrayList<String>();
    public static  String WhatsApp;

    public static ArrayList<String>listacont= new ArrayList<String>();
    public static ArrayList<String>precioXtapete=new ArrayList<String>();
    public static ArrayList<String>precioXtapeteEx=new ArrayList<String>();
    public static ArrayList<String>precioXtapeteMa=new ArrayList<String>();
    public static ArrayList<String>descripciontapete = new ArrayList<String>();
    public static ArrayList<String>opclist= new ArrayList<String>();
    public static ArrayList<String>imgserver = new ArrayList<String>();
    public static ArrayList<String>clavetapete=new ArrayList<String>();
    public static ArrayList<String>medidastapete= new ArrayList<String>();
    public static ArrayList<String>clavelarga= new ArrayList<String>();
    public static ArrayList<String>listahm = new ArrayList<String>();
    public static ArrayList<String> precioseleccionado = new ArrayList<String>();
    public static ArrayList<String>porciento = new ArrayList<String>();

    public static String totalpagartodo;
    public static String estatusselect;

    //*-PARA INVENTARIO*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    public static  ArrayList<String>imgserverinv = new ArrayList<String>();
    //*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*




    public static void subircantidadtapete(int contai){
        int unomas=0;
        unomas=Integer.parseInt(listacont.get(contai))+1;

        listacont.set(contai, Integer.toString(unomas));

        totalpago();
    }

    public static void totalpago(){
        int i;
        int sizedelist;
        int total;
        int valorlstacont;
        int valorlistaprecio;
        int total3=0;
        int milestotalpago;
        int reciduostotalpago;
        String printprecio;
        sizedelist=listacont.size();

        for (i=0;i<sizedelist;i++){
            valorlstacont=Integer.parseInt(listacont.get(i));
            valorlistaprecio=Integer.parseInt(precioseleccionado.get(i));

            total=valorlistaprecio*valorlstacont;

            total3=total3+total;
        }
        listatapetes.totalprecioxd.setAmount(total3);
        totalpagartodo=Integer.toString(total3);

    }




    public static void subircantidadtapetecotiza(int contai){
        int unomas=0;
        unomas=Integer.parseInt(listacont.get(contai))+1;

        listacont.set(contai, Integer.toString(unomas));

        totalpagocotiza();
    }

    public static void totalpagocotiza(){
        int i;
        int sizedelist;
        int total;
        int valorlstacont;
        int valorlistaprecio;
        int total3=0;
        int milestotalpago;
        int reciduostotalpago;
        String printprecio;
        sizedelist=listacont.size();

        for (i=0;i<sizedelist;i++){
            valorlstacont=Integer.parseInt(listacont.get(i));
            valorlistaprecio=Integer.parseInt(precioseleccionado.get(i));

            total=valorlistaprecio*valorlstacont;

            total3=total3+total;
        }
        //listatapetescotiza.totalprecioxd.setAmount(total3);
        totalpagartodo=Integer.toString(total3);

    }

    public static void totalpago2(){
        int i;
        int sizedelist;
        int total;
        int valorlstacont;
        int valorlistaprecio;
        int total3=0;
        int milestotalpago;
        int reciduostotalpago;
        String printprecio;
        sizedelist=listacont.size();

        for (i=0;i<sizedelist;i++){
            valorlstacont=Integer.parseInt(listacont.get(i));
            valorlistaprecio=Integer.parseInt(precioseleccionado.get(i));

            total=valorlistaprecio*valorlstacont;

            total3=total3+total;
        }
        listatapetes2.totalprecioxd.setAmount(total3);
        totalpagartodo=Integer.toString(total3);

    }

    public static void totalpago3(){
        int i;
        int sizedelist;
        int total;
        int valorlstacont;
        int valorlistaprecio;
        int total3=0;
        int milestotalpago;
        int reciduostotalpago;
        String printprecio;
        sizedelist=listacont.size();

        for (i=0;i<sizedelist;i++){
            valorlstacont=Integer.parseInt(listacont.get(i));
            valorlistaprecio=Integer.parseInt(precioseleccionado.get(i));

            total=valorlistaprecio*valorlstacont;

            total3=total3+total;
        }
        listatapetes3.totalprecioxd.setAmount(total3);
        totalpagartodo=Integer.toString(total3);

    }


}
