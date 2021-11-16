package com.tufan.sistemas1.com.expotufan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner selec_expo;
    String devuelve;
    String devuelveexpo;
    String sesionactiva;
    String pass;
    String passexpo;
    TextView mensaje;
    private ProgressDialog progress;
    String idusuario;
    String cadenatxtpass;
    String cadenapassword;
    String cadenapasa1;
    ObtenerWebService hiloconexion;
    ObtenerWebService2 hiloconexion2;
    ObtenerWebService3 hilo;
    ObtenerWebServiceexpo hiloexpo;
    public Thread t;
    ArrayList<String>expos= new ArrayList<String>();
    ArrayList<String>expos_id= new ArrayList<String>();
    ArrayList<String>expos_identificador= new ArrayList<String>();


    // Rutas de los Web Services
    String GET = appRutaservidor.IP + "/obtener_solicitudes.php";
    String GET_BY_ID = appRutaservidor.IP  + "/obtener_solicitudes_por_id.php";

    //Ruta de los Web Services..
    String GETExpo=appRutaservidor.IP + "/obtener_EXPO1_por_id.php";
    String GETExpotodo=appRutaservidor.IP + "/obtener_expotodo.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mensaje=(TextView)findViewById(R.id.txtmensajeserver);
        mPasswordView = (EditText) findViewById(R.id.password);
        selec_expo=(Spinner)findViewById(R.id.sp_expo);

        //cerrarxdxd();

     selec_expo.setOnItemSelectedListener(this);

        try
        {
            BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("meminterna1.txt")));

            pass = fin.readLine();
            fin.close();

        }catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
        }


        if(pass==null){
            pass="1";

        }

        try
        {
            BufferedReader fin =new BufferedReader(new InputStreamReader(openFileInput("meminterna2.txt")));

             passexpo= fin.readLine();
            fin.close();

        }catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
        }

//cerrarxdxd();
        if(passexpo==null){

            expos.add("Selecciona Expo");
            expos_id.add("0");
            expos_identificador.add("0");


            hiloexpo= new ObtenerWebServiceexpo();
            cadenapasa1= GETExpotodo;


            try {
                hiloexpo.execute(cadenapasa1,"2").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expos);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selec_expo.setAdapter(dataAdapter);


        }else{

            hilo = new ObtenerWebService3();
            String cadena = GETExpo + "?nom_expo=" + passexpo;
            try {
                hilo.execute(cadena,"2").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }





        hiloconexion2 = new ObtenerWebService2();
        String cadenallamada2 = GET_BY_ID + "?pass=" + pass;
        hiloconexion2.execute(cadenallamada2,"2");






        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                if(mPasswordView.getText().toString().trim().equalsIgnoreCase("")){
                    mPasswordView.requestFocus();

                }else if(selec_expo.getSelectedItem().toString().equalsIgnoreCase("Selecciona Expo")){
                    Toast.makeText(LoginActivity.this,"Selecciona la Expo",Toast.LENGTH_SHORT).show();
                    selec_expo.setFocusable(true);
                    selec_expo.setFocusable(true);
                    selec_expo.setFocusableInTouchMode(true);
                    selec_expo.requestFocus();

                }else {
                        //***PROGRES BARRRRR****-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                        progress=new ProgressDialog(LoginActivity.this);
                        progress.setMessage("Conectando algo....");
                        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        // progress.setIndeterminate(true);
                        progress.setProgress(0);
                        progress.show();

                        final int totalProgressTime = 100;
                        t = new Thread() {
                            @Override
                            public void run() {
                                int jumpTime = 0;

                                while(jumpTime < totalProgressTime) {
                                    try {
                                        jumpTime += 5;
                                        progress.setProgress(jumpTime);
                                        sleep(100);
                                    }
                                    catch (InterruptedException e) {
                                        Log.e("error", e.getMessage());
                                    }
                                }
                            }
                        };
                        t.start();

                        //*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*---*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
                    hilo = new ObtenerWebService3();
                    String cadena = GETExpo + "?nom_expo=" + selec_expo.getSelectedItem().toString().trim();
                    try {
                        hilo.execute(cadena,"2").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                        cadenatxtpass = "";
                    cadenapassword = "";
                    cadenatxtpass = mPasswordView.getText().toString();

                    hiloconexion = new ObtenerWebService();
                    String cadenallamada = GET_BY_ID + "?pass=" + cadenatxtpass;
                    hiloconexion.execute(cadenallamada, "2");
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mPasswordView.getText().toString().trim().equalsIgnoreCase("")){
                    mPasswordView.requestFocus();
                    Snackbar.make(view, "Coloca Contraseña", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(selec_expo.getSelectedItem().toString().equalsIgnoreCase("Selecciona Expo")){
                    Toast.makeText(LoginActivity.this,"Selecciona la Expo",Toast.LENGTH_SHORT).show();
                    selec_expo.setFocusable(true);
                    selec_expo.setFocusable(true);
                    selec_expo.setFocusableInTouchMode(true);
                    selec_expo.requestFocus();

                }else {
                    //***PROGRES BARRRRR****-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                    progress=new ProgressDialog(LoginActivity.this);
                    progress.setMessage("Conectando algo....");
                    progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    // progress.setIndeterminate(true);
                    progress.setProgress(0);
                    progress.show();

                    final int totalProgressTime = 100;
                    t = new Thread() {
                        @Override
                        public void run() {
                            int jumpTime = 0;

                            while(jumpTime < totalProgressTime) {
                                try {
                                    jumpTime += 5;
                                    progress.setProgress(jumpTime);
                                    sleep(100);
                                }
                                catch (InterruptedException e) {
                                    Log.e("error", e.getMessage());
                                }
                            }
                        }
                    };
                    t.start();

                    //*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*--*-*-*-*-*-*-*---*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--
                    hilo = new ObtenerWebService3();
                    String cadena = GETExpo + "?nom_expo=" + selec_expo.getSelectedItem().toString().trim();
                    try {
                        hilo.execute(cadena,"2").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    cadenatxtpass = "";
                    cadenapassword = "";
                    cadenatxtpass = mPasswordView.getText().toString();

                    hiloconexion = new ObtenerWebService();
                    String cadenallamada = GET_BY_ID + "?pass=" + cadenatxtpass;
                    hiloconexion.execute(cadenallamada, "2");
                }

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void cerrarxdxd() {
        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("meminterna1.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }

        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("meminterna2.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }


        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("idexpo.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }

        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("encabezadoexpo.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }

        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("almacen1.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }

        try
        {
            OutputStreamWriter fout=new OutputStreamWriter(openFileOutput("idusuario.txt", Context.MODE_PRIVATE));

            fout.write("");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {

            return;
        }

        // Reset errors.
        //mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        //String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) ) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            hiloconexion = new ObtenerWebService();
            String cadenallamada = GET_BY_ID + "?pass=" + cadenatxtpass;
            hiloconexion.execute(cadenallamada, "2");

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.sp_expo:
                //Toast.makeText(this, "Tuposicion: " + position, Toast.LENGTH_SHORT).show();
                listaConta.id_expo=expos_id.get(position);
                listaConta.nombre_Expo=expos.get(position);
                listaConta.encabrzadopedido=expos_identificador.get(position);

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            // Document document = null;
            // Document document = null;

            //ListView listView = null;

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            devuelve ="";

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
                        //resultJSON="1";
                        int resultadouser=Integer.parseInt(resultJSON);
                        if (resultadouser==1){      // hay un alumno que mostrar
                            devuelve = devuelve + respuestaJSON.getJSONObject("alumno").getString("pass");
                            idusuario=respuestaJSON.getJSONObject("alumno").getString("idusuario");
                            listaConta.idusuariotufan=respuestaJSON.getJSONObject("alumno").getString("idusuario");


                            try {
                                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("idusuario.txt", Context.MODE_PRIVATE));

                                fout.write(listaConta.idusuariotufan.toString());
                                fout.close();
                            } catch (Exception ex) {
                                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                            }



                            sesionactiva=respuestaJSON.getJSONObject("alumno").getString("sesion");
                            listaConta.usuariopass=devuelve;
                            cadenapassword=devuelve;
                        }
                       else if (resultadouser==2){
                           devuelve = "No hay alumnos";
                       }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(LoginActivity.this,"No hay Servidor",Toast.LENGTH_SHORT).show();
                    //finish();
                    devuelve="No Server";
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

            if (s.equals("No Server")){
                Toast.makeText(LoginActivity.this, "Tienda Cerrada ....",Toast.LENGTH_LONG).show();
                mensaje.setText("no hay servidor... ");
                progress.dismiss();
                //t.isInterrupted();
            }else if (cadenatxtpass.equals(s)){
                //Toast.makeText(inicio.this,cadenatxtpass+"Si es igual"+cadenapassword,Toast.LENGTH_SHORT).show();

               /* if (sesionactiva.equals("1")){
                    mPasswordView.setError("Error de password");
                    mPasswordView.setFocusable(true);
                    mPasswordView.setText("");
                    Toast.makeText(LoginActivity.this," Esta cuenta Tufan ESTA EN USO",Toast.LENGTH_SHORT).show();
                    progress.dismiss();

                }else {*/
                    try {
                        OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("meminterna1.txt", Context.MODE_PRIVATE));

                        fout.write(mPasswordView.getText().toString().trim());
                        fout.close();
                    } catch (Exception ex) {
                        Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                    }




                try {
                    OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("meminterna2.txt", Context.MODE_PRIVATE));

                    fout.write(selec_expo.getSelectedItem().toString().trim());
                    fout.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }
                    sesion.iniciosesion(s);


                    //barra1.setVisibility(ProgressBar.VISIBLE);
                    Intent pasa = new Intent(LoginActivity.this, MainActivity.class);
                    pasa.putExtra("DATOinicio", s);
                    pasa.putExtra("IDUSER", idusuario);
                    startActivity(pasa);

                    //progress.dismiss();

                    finish();
/*
                Intent intento = new Intent(MainActivity.this,detalle.class);
                intento.putExtra("DATO",datoss);
                startActivity(intento);*/

                //}
            }else{

                mPasswordView.setError("Error de password");
                mPasswordView.setFocusable(true);
                mPasswordView.setText("");
                Toast.makeText(LoginActivity.this," Esta cuenta Tufan no existe. Indica una cuenta diferente ",Toast.LENGTH_SHORT).show();
                progress.dismiss();

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

    public class ObtenerWebService2 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            // Document document = null;
            // Document document = null;

            //ListView listView = null;

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            devuelve ="";

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
                        int resjsonxd=Integer.parseInt(resultJSON);
                        if (resjsonxd==1){      // hay un alumno que mostrar
                            devuelve = devuelve + respuestaJSON.getJSONObject("alumno").getString("pass");
                            idusuario=respuestaJSON.getJSONObject("alumno").getString("idusuario");
                            sesionactiva=respuestaJSON.getJSONObject("alumno").getString("sesion");
                            //idusuariotufan=respuestaJSON.getJSONObject("alumno").getString("idusuario");
                            listaConta.idusuariotufan=respuestaJSON.getJSONObject("alumno").getString("idusuario");
                            listaConta.usuariopass=devuelve;

                            cadenapassword=devuelve;

                        }
                        else if (resjsonxd==2){
                            devuelve = "No hay alumnos";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(LoginActivity.this,"No hay servidor",Toast.LENGTH_SHORT).show();
                    //finish();
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

            if (pass.equals(s)){
                //Toast.makeText(inicio.this,cadenatxtpass+"Si es igual"+cadenapassword,Toast.LENGTH_SHORT).show();

                //barra1.setVisibility(ProgressBar.VISIBLE);
                Intent pasa= new Intent(LoginActivity.this,MainActivity.class);
                pasa.putExtra("DATOinicio",s);
                pasa.putExtra("IDUSER",idusuario);
                sesion.iniciosesion(s);
                startActivity(pasa);
                //progress.dismiss();


                finish();



            }else{
                //Toast.makeText(LoginActivity.this," Iniciar sesión ",Toast.LENGTH_SHORT).show();
                //mPasswordView.setError("Error de password");
                mPasswordView.setFocusable(true);
                mPasswordView.setText("");
                //Toast.makeText(LoginActivity.this," Esta cuenta Tufan no existe. Indica una cuenta diferente ",Toast.LENGTH_SHORT).show();
                //progress.dismiss();

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

    public class ObtenerWebService3 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            // Document document = null;
            // Document document = null;

            //ListView listView = null;

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            devuelveexpo ="";

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

                        //String resultJSON = respuestaJSON1.getString("estado");   // estado es el nombre del campo en el JSON
                        //resultJSON="1";
                        String resultJSON = respuestaJSON.getString("estado");
                        int resultadouser=Integer.parseInt(resultJSON);
                        if (resultadouser==1){      // hay un alumno que mostrar
                            //cJSONArray solicitaJSON = respuestaJSON1.getJSONArray("alumno");   // estado es el nombre del campo en el JSON

                            //devuelveexpo = respuestaJSON1.getJSONObject("solicita").getString("iden_pedido");
                            devuelveexpo=respuestaJSON.getJSONObject("alumno").getString("iden_pedido");
                            //devuelveexpo=respuestaJSON.getString("iden_pedido");

                            listaConta.nombre_Expo=respuestaJSON.getJSONObject("alumno").getString("nom_expo");
                            listaConta.id_expo=respuestaJSON.getJSONObject("alumno").getString("idexpo");
                            listaConta.encabrzadopedido=devuelveexpo;
                            listaConta.almacen1=respuestaJSON.getJSONObject("alumno").getString("almacen1");

                            //*-*-*-*-*-**-*-*-
                            //*-*-*-*-PARA NOMBRE EXPO....
                            try {
                                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("meminterna2.txt", Context.MODE_PRIVATE));

                                fout.write(listaConta.nombre_Expo.toString().trim());
                                fout.close();
                            } catch (Exception ex) {
                                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                            }

                            //*-*-*-*-*-**-*-*-
                            //*-*-*-*-PARA ID EXPO....
                            try {
                                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("idexpo.txt", Context.MODE_PRIVATE));

                                fout.write(listaConta.id_expo.toString().trim());
                                fout.close();
                            } catch (Exception ex) {
                                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                            }

                            //*-*-*-*-*-**-*-*-
                            //*-*-*-*-PARA encabezado EXPO....
                            try {
                                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("encabezadoexpo.txt", Context.MODE_PRIVATE));

                                fout.write(listaConta.encabrzadopedido.toString().trim());
                                fout.close();
                            } catch (Exception ex) {
                                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                            }

                            //*-*-*-*-*-**-*-*-
                            //*-*-*-*-PARA NOMBRE EXPO....
                            try {
                                OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("almacen1.txt", Context.MODE_PRIVATE));

                                fout.write(listaConta.almacen1.toString().trim());
                                fout.close();
                            } catch (Exception ex) {
                                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                            }


                            //cadenapassword=devuelve;
                        }
                        else if (resultadouser==2){
                            devuelveexpo = "No hay alumnos";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(LoginActivity.this,"No hay Servidor",Toast.LENGTH_SHORT).show();
                    //finish();
                    //devuelve="No Server";
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

    public class ObtenerWebServiceexpo extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            // Document document = null;
            // Document document = null;

            //ListView listView = null;

            String cadena = params[0];

            URL url = null; // Url de donde queremos obtener información
            devuelveexpo ="";

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
                        JSONObject respuestaJSON1 = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON1.getString("estado");   // estado es el nombre del campo en el JSON
                        //resultJSON="1";
                        int resultadouser=Integer.parseInt(resultJSON);
                        if (resultadouser==1){      // hay un alumno que mostrar
                            JSONArray solicitaJSON = respuestaJSON1.getJSONArray("solicita");   // estado es el nombre del campo en el JSON
                            String identificadorexpo;
                            String nombreexpo;
                            String idexpo;
                            for(int i=0;i<solicitaJSON.length();i++){
                                devuelve = solicitaJSON.getJSONObject(i).getString("iden_pedido");
                                identificadorexpo = solicitaJSON.getJSONObject(i).getString("iden_pedido");
                                nombreexpo = solicitaJSON.getJSONObject(i).getString("nom_expo");
                                idexpo = solicitaJSON.getJSONObject(i).getString("idexpo");

                                expos.add(nombreexpo);
                                expos_id.add(idexpo);
                                expos_identificador.add(identificadorexpo);
                            }


                            //cadenapassword=devuelve;
                        }
                        else if (resultadouser==2){
                            devuelveexpo = "No hay alumnos";
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(LoginActivity.this,"No hay Servidor",Toast.LENGTH_SHORT).show();
                    //finish();
                    //devuelve="No Server";
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

}

