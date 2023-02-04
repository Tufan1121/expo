package com.tufan.sistemas1.com.expotufan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import static com.tufan.sistemas1.com.expotufan.TemplatePDF.pdffile;


public class clsEnviaCorreo extends AsyncTask<Void,Void,Void> {
    private final Context contexto;
    private Session De_Sesion;

    private final String A_Correo;
    private final String A_Asunto;
    private final String A_Mensaje;

   public ProgressDialog progreso;

    public clsEnviaCorreo(Context cContexto, String cCorreo, String cAsunto, String cMensaje) {
        this.contexto = cContexto;
        this.A_Correo = cCorreo;
        this.A_Asunto = cAsunto;
        this.A_Mensaje = cMensaje;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progreso = ProgressDialog.show(contexto, "Enviando mensaje", "Espere...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //progreso.dismiss();
        Toast.makeText(contexto, "Mensaje Enviado", Toast.LENGTH_LONG).show();
        //progreso.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        /*Configuraciones según el proveedor de Correo electrónico que enviará el Mensaje*/
        /*=========================================================================================
                                         PARA GMAIL
         Requisito: se debe activar "Permitir que aplicaciones menos seguras accedan a tu cuenta"
     https://www.google.com/settings/security/lesssecureapps
            ===========================================================================================*/


       /*
        ---------------------------------------------------------------------------------------------------------
        --------------------------------------------------------------------------------------------------------
        ---------------------------------------------------------------------------------------------------------

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        ----------------------------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------------------------

        */
        /*
        props.put("mail.smtp.host", "mail.tapetestufan.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true") ;
        props.put("mail.smtp.port", "465");

*/
        /*
        //Configuracion con SSL
        props.put("mail.smtp.host", "mail.tapetestufan.com");
        props.put("mail.smtp.socketFactory.port", "426");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "426");
        */

        /*
        props.put("mail.smtp.host", "mail.tapetestufan.com");
        props.put("mail.smtp.socketFactory.port", "26");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "26");
        */


        //Configuracion con SSL 2
        props.put("mail.smtp.host", "host425.hostmonster.com");
        props.put("mail.smtp.socketFactory.port", "26");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "26");


        /*=========================================================================================
                                         PARA OUTLOOK (ANTES HOTMAIL)
    ===========================================================================================*/
        //props.put("mail.smtp.host", "smtp.tapetestufan.com");
        //props.put("mail.smtp.socketFactory.port", "465");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "465");
       /*=========================================================================================
                                         PARA YAHOO
         Requisito: se debe activar "Permitir aplicaciones que utilicen un inicio de sesión menos seguro"
         https://login.yahoo.com/account/security#other-apps?lang=es-ES
    ===========================================================================================*/
        /*props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");*/

        De_Sesion = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(clsConfiguracion.DE_CORREO, clsConfiguracion.DE_PASSWORD);
            }
        });

        try {
            MimeMessage mm = new MimeMessage(De_Sesion);
            mm.setFrom(new InternetAddress(clsConfiguracion.DE_CORREO));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(A_Correo));
            mm.setSubject(A_Asunto);
            mm.setText(A_Mensaje);
            Transport.send(mm);
            Toast.makeText(contexto, "Mensaje Enviado", Toast.LENGTH_LONG).show();

        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(contexto, "Mensaje No Enviado", Toast.LENGTH_LONG).show();

        }
        return null;
    }

}