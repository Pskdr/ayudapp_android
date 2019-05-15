package com.example.appalertas2;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class PantallaRegistrar1 extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnRegistrarGuardar, btnIngresarRegistro;
    EditText txtNombres,txtApellidos, txtNumeroDocumento, txtEmail, txtContraseña, txtTelefonoCelular, txtTelefonoFijo,
            txtDireccionResidencia;
    TextView txtFechaNacimiento;
    Spinner spnTipoDocumento, spnTipoSangre, spnEPS;
    ArrayAdapter<String> adapter;
    ArrayList<String> listaEPS = new ArrayList<>();
    ArrayList<String> listaIdNombreEPS = new ArrayList<>();
    ArrayList<String> listaTipoSangre = new ArrayList<>();
    ArrayList<String> listaTipoDocumento = new ArrayList<>();
    Integer dia,mes,ano;
    static String fechaNacimiento;
    static String idEPS;
    static String validarEmail ;
    static boolean valido = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar_1);
        conectar();
        valido=false;
        llenarSpnTipoDocumento();
        llenarSpnTipoSangre();
        llenarSpnEPS();
        validarEmail = String.valueOf(103);
        timer();



        btnRegistrarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombres.getText().toString().equals("") || !txtEmail.getText().toString().equals("") || !txtContraseña.getText().toString().equals("") ||
                        !txtNumeroDocumento.getText().toString().equals("") || !txtApellidos.getText().toString().equals("") || !txtTelefonoCelular.getText().toString().equals("")  ){

                    if(validarEmail.equals("102")) {
                        seleccionarIdEPS(spnEPS.getSelectedItem().toString());

                        registrarUsuario(String.valueOf(spnTipoSangre.getSelectedItemId() + 1), idEPS,
                                String.valueOf(spnTipoDocumento.getSelectedItemId() + 1), txtNumeroDocumento.getText().toString(),
                                txtNombres.getText().toString(), txtApellidos.getText().toString(), txtTelefonoFijo.getText().toString(),
                                txtTelefonoCelular.getText().toString(), txtEmail.getText().toString(), txtDireccionResidencia.getText().toString(),
                                txtContraseña.getText().toString(), fechaNacimiento);

                        Toast.makeText(getApplicationContext(), "Registro Exitoso!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), PantallaEmergenciaPrincipal.class);
                        startActivity(i);
                        valido=true;
                    }else {
                        Toast.makeText(getApplicationContext(), "El EMAIL ya ha sido registrado", Toast.LENGTH_LONG).show();
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), "Favor llenar los campos obligatorios", Toast.LENGTH_LONG).show();

                }
            }
        });



        btnIngresarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaInicial2.class);
                startActivity(i);
            }
        });



        txtFechaNacimiento.setOnClickListener(this);

    }

    private void timer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          buscarEmailRegistrado(txtEmail.getText().toString());
                                      }
                                  },
                1000, 2000);   // 1000 Millisecond  = 1 second
        if(valido){
            timer.cancel();
        }
    }


    private void seleccionarIdEPS(String nombreEPS){
        String[] todo = new String[listaIdNombreEPS.size() * 2];
        String valor;
        for(int i = 0; i<listaIdNombreEPS.size(); i++){
            valor = listaIdNombreEPS.get(i);
            todo = valor.split(";");
        }

        for(int i=1; i <todo.length; i+=2){
            if(todo[i] == nombreEPS)  {
                idEPS = todo[i-1];
            }
        }

    }



    @Override
    public void onClick(View v) {
        if (v == txtFechaNacimiento) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtFechaNacimiento.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
                    asignarFechaFormatoAAAAMMDD(year, monthOfYear, dayOfMonth);
                }}, dia, mes, ano);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
        }
    }



    private void asignarFechaFormatoAAAAMMDD(int year, int monthOfYear, int dayOfMonth){
        if((dayOfMonth==1 || dayOfMonth==2 || dayOfMonth==3 || dayOfMonth==4 || dayOfMonth==5 || dayOfMonth==6 || dayOfMonth==7 ||
                dayOfMonth==8 || dayOfMonth==9 ) && (monthOfYear==0 || monthOfYear==1 || monthOfYear==2 || monthOfYear==3 || monthOfYear==4 ||
                monthOfYear==5 || monthOfYear==6 || monthOfYear==7 || monthOfYear==8 )) {
            fechaNacimiento = year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth;
        }
        else if((dayOfMonth==1 || dayOfMonth==2 || dayOfMonth==3 || dayOfMonth==4 || dayOfMonth==5 || dayOfMonth==6 || dayOfMonth==7 ||
                dayOfMonth==8 || dayOfMonth==9 ) && (monthOfYear==9 || monthOfYear==10 || monthOfYear==11)) {
            fechaNacimiento = year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth;

        }else if((dayOfMonth!=1 || dayOfMonth!=2 || dayOfMonth!=3 || dayOfMonth!=4 || dayOfMonth!=5 || dayOfMonth!=6 || dayOfMonth!=7 ||
                dayOfMonth!=8 || dayOfMonth!=9 ) && (monthOfYear==0 || monthOfYear==1 || monthOfYear==2 || monthOfYear==3 || monthOfYear==4 ||
                monthOfYear==5 || monthOfYear==6 || monthOfYear==7 || monthOfYear==8 )){
            fechaNacimiento = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
        }else {
            fechaNacimiento = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        }
    }



    private void llenarSpnEPS(){
        String URL_EPS = "http://labs.ebotero.com/servicios_ayudapp/public/eps";

        JsonObjectRequest jsonObjRqst = new JsonObjectRequest(Request.Method.GET,
                URL_EPS,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        llenarListaEPS(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :"+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestqueue = Volley.newRequestQueue(this);
        requestqueue.add(jsonObjRqst);

    }



    private void llenarListaEPS(JSONObject obj){
        try{
            JSONArray list = obj.optJSONArray("data");
            for(int i = 0; i<list.length(); i++){
                JSONObject json_data = list.getJSONObject(i);
                String contenido =  json_data.getString("Nombre");
                listaEPS.add(contenido);
                contenido = json_data.getString("Id") + ";" + json_data.getString("Nombre");
                listaIdNombreEPS.add(contenido);

            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaEPS);
            spnEPS.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
        }
    }




    private void buscarEmailRegistrado(final String email){

        String URL_BuscarEmail = "http://labs.ebotero.com/servicios_ayudapp/public/buscar_usuario_email";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BuscarEmail,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonobj = new JSONObject(response);
                            validarEmail = jsonobj.getString("status");
                        }catch (JSONException ex){
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("Email", email);
                return parametros;
            }
        };
        RequestQueue requestqueue = Volley.newRequestQueue(this);
        requestqueue.add(stringRequest);
    }




    private void registrarUsuario(final String idRh, final String idEps, final String idTipoDocumento, final String numeroDocumento, final String nombres, final String apellidos,
                                  final String telefonoFijo, final String telefonoCelular, final String email, final String direccion, final String contrasena, final String fecha){

        String URL_RegistroUsuario = "http://labs.ebotero.com/servicios_ayudapp/public/crear_usuario";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RegistroUsuario,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("IdRh", idRh);
                parametros.put("IdEps", idEps);
                parametros.put("IdTipoDeDocumento", idTipoDocumento);
                parametros.put("NroDeDocumento", numeroDocumento);
                parametros.put("Nombre", nombres);
                parametros.put("Apellido", apellidos);
                parametros.put("TelefonoFijo", telefonoFijo);
                parametros.put("Celular", telefonoCelular);
                parametros.put("Email", email);
                parametros.put("Direccion", direccion);
                parametros.put("Constrasena", contrasena);
                parametros.put("FechaDeNacimiento", fecha);
                return parametros;
            }
        };

        RequestQueue requestqueue = Volley.newRequestQueue(this);
        requestqueue.add(stringRequest);
    }



    private void llenarSpnTipoDocumento(){
        listaTipoDocumento.clear();
        listaTipoDocumento.add("Cédula de ciudadanía");
        listaTipoDocumento.add("Tarjeta de identidad");
        listaTipoDocumento.add("Cédula de extranjería");
        listaTipoDocumento.add("Pasaporte");
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listaTipoDocumento);
        spnTipoDocumento.setAdapter(adapter);
    }


    private void llenarSpnTipoSangre(){
        listaTipoSangre.clear();
        listaTipoSangre.add("O -");
        listaTipoSangre.add("O +");
        listaTipoSangre.add("A -");
        listaTipoSangre.add("A +");
        listaTipoSangre.add("B -");
        listaTipoSangre.add("B +");
        listaTipoSangre.add("AB -");
        listaTipoSangre.add("AB +");

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listaTipoSangre);
        spnTipoSangre.setAdapter(adapter);
    }
    private void conectar(){
        btnRegistrarGuardar = findViewById(R.id.btnRegistrarGuardar);
        btnIngresarRegistro = findViewById(R.id.btnIngresarRegistro);
        txtFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        txtNombres = findViewById(R.id.etNombres);
        txtApellidos = findViewById(R.id.etApellidos);
        txtNumeroDocumento = findViewById(R.id.etDocumentoID);
        txtEmail = findViewById(R.id.etEmail);
        txtContraseña = findViewById(R.id.etContraseña);
        txtTelefonoCelular = findViewById(R.id.etTelefonoCelularRegistro);
        txtTelefonoFijo = findViewById(R.id.etTelefonoFijoRegistro);
        txtDireccionResidencia = findViewById(R.id.etDireccion);
        spnEPS = findViewById(R.id.spnEPS);
        spnTipoDocumento = findViewById(R.id.spnTipoDocumentoID);
        spnTipoSangre = findViewById(R.id.spnTipoSangre);
    }

}
