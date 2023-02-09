package com.tufan.sistemas1.com.expotufan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Button escaner;
    private Button actualiza;
    private TextView txtlista;
    private ListView mlista;
    ArrayList<String> al= new ArrayList<String>();
    ArrayList<String> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        escaner= findViewById(R.id.btn_codigo);
        final Activity activity = this;
        //activity.setRequestedOrientation(ActivityInfo.o);
        //txtlista=(TextView)findViewById(R.id.txt_lista);
        mlista= findViewById(R.id.milista);
        actualiza= findViewById(R.id.btn_actualizalista);
        actualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Main2Activity.this,Main2Activity.class);
                intent.putExtra("al", al);
                startActivity(intent);

            }
        });
        escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator= new IntentIntegrator(Main2Activity.this);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("escaneo");
                integrator.setOrientationLocked(false);
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });


    }

         @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
                 if(result.getContents()!=null){
                    if(result.getContents()== null){
                        Toast.makeText(this,"you cancelled the scand",Toast.LENGTH_LONG).show();

                    }else{

                Toast.makeText(this,"resultado "+result.getContents(),Toast.LENGTH_LONG).show();
                arrayList.add(result.getContents()+"\n");
                al.add(result.getContents());

                //txtlista.setText(arrayList.toString());
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
                mlista.setAdapter(adapter);


            }


             }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    }



