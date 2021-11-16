package com.tufan.sistemas1.com.expotufan;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.Log;
import android.view.ViewGroup;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.tufan.sistemas1.com.expotufan.R.mipmap.logot;
import static com.tufan.sistemas1.com.expotufan.generaPedido.pedidoserie;

public class TemplatePDF extends BitmapFactory {
    private Context context;
    public static File pdffile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    //private MarginLayoutParamsCompat
    private Font fTitlex= new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD);
    private Font fSubTitlex= new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    private Font fTextx= new Font(Font.FontFamily.TIMES_ROMAN,8,Font.BOLD);

    private Font fTitle= new Font(Font.FontFamily.TIMES_ROMAN,11,Font.ITALIC);
    private Font fSubTitle= new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
    private Font fText= new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
    private Font fHighText= new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD, BaseColor.RED);

    public TemplatePDF(Context context) {
        this.context=context;

    }
    public  void openDocument(){
        float left = 30;
        float right = 30;
        float top = 60;
        float bottom = 0;
        createFile();

        try {
            document= new Document(PageSize.A4,left, right, top, bottom);
            document.setMargins(left, right, 0, bottom);
            pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(pdffile));
            document.open();

        }catch (Exception e){
            Log.e("opendocument",e.toString());
        }









}
    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"pdf");
        if (!folder.exists())
            folder.mkdirs();
        pdffile= new File(folder,"Pedido"+pedidoserie+".pdf");

    }
    public void closeDocument(){
        document.close();

    }



    public  void addMetaData(String title,String subject,String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
        }


    public void addTitles(String title,String subtitle ,String date ){
        try {

            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subtitle, fSubTitle));
            addChildP(new Paragraph("Generando:" + date, fHighText));
            addChildP(new Paragraph());
            paragraph.setSpacingAfter(30);
            document.add(paragraph);


        }catch (Exception e){
            Log.e("error de cucuemnto",e.toString());
        }
    }


    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }
    public  void addParagraph(String text){
        try {
            paragraph=new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);




            document.add(paragraph);

        }catch (Exception e){
            Log.e("error en el texto", e.toString());
        }


    }
    public void imgpdf(ByteArrayOutputStream xs){

        Image image = null;
        try {
            image = Image.getInstance(xs.toByteArray());
            image.scaleAbsolute(550f, 100f);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            document.add(image);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
    public  void createTable(String[]header, ArrayList<String[]> clientes){
        try {


            paragraph=new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable= new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int index=0;
            while(index<header.length){
                pdfPCell=new PdfPCell(new Phrase(header[index++],fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.MAGENTA);
                pdfPTable.addCell(pdfPCell);

            }

            for(int indexr=0;indexr<clientes.size();indexr++){
                String[]row = clientes.get(indexr);
                for(index=0;index<header.length;index++){
                    pdfPCell=new PdfPCell(new Phrase(row[index]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);

                }

            }

            paragraph.add(pdfPTable);

            document.add(paragraph);

        }catch (Exception e){


        }

    }

    public  void createTable1(String[]header1, ArrayList<String[]> listass){
        try {
            paragraph=new Paragraph();
            paragraph.setFont(fTextx);
            PdfPTable pdfPTable= new PdfPTable(header1.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;

            int index=0;

            while(index<header1.length){
                pdfPCell=new PdfPCell(new Phrase(header1[index++],fSubTitlex));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.MAGENTA);
                pdfPTable.addCell(pdfPCell);

            }

            for(int indexr=0;indexr<listass.size();indexr++){
                String[]row = listass.get(indexr);
                for(index=0;index<header1.length;index++) {

                        pdfPCell = new PdfPCell(new Phrase(row[index]));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setFixedHeight(40);
                        pdfPTable.addCell(pdfPCell);

                }

            }

            paragraph.add(pdfPTable);

            document.add(paragraph);

        }catch (Exception e){


        }

    }


    public  void verpdfya(){
        Intent pasa = new Intent(context,view_pdf.class);
        pasa.putExtra("path",pdffile.getAbsolutePath());
        pasa.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(pasa);

    }

    public  void verpdfya2(){
        Intent pasa2 = new Intent(context,verpedf2.class);
        //pasa2.putExtra("path",pdffile.getAbsolutePath());
        //.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(pasa2);

    }
}
