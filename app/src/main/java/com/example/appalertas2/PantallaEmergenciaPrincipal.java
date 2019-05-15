package com.example.appalertas2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PantallaEmergenciaPrincipal extends AppCompatActivity {
    ImageButton btnEmergenciaPrincipal, btnMiPerfilPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_emergencia_principal);
        conectar();
        btnMiPerfilPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaMiPerfil.class);
                startActivity(i);
            }
        });


        if (ActivityCompat.checkSelfPermission(PantallaEmergenciaPrincipal.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PantallaEmergenciaPrincipal.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {ActivityCompat.requestPermissions(PantallaEmergenciaPrincipal.this, new String[]
                {
                            Manifest.permission.SEND_SMS,}, 1000);
        }else{

        };


        btnEmergenciaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarMensaje("3234729383","Sorry we");
                //Toast.makeText(getApplicationContext(), "La ayuda va en camino", Toast.LENGTH_LONG).show();
                //Intent i = new Intent(getApplicationContext(), PantallaEmergenciaPrincipal.class);
                //startActivity(i);
            }
        });


    }

    private void enviarMensaje (String numero, String mensaje){
        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(),"Mensaje Enviado",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void conectar(){
        btnMiPerfilPrincipal = findViewById(R.id.btnMiPerfilPrincipal);
        btnEmergenciaPrincipal = findViewById(R.id.btnEmergenciaPrincipal);

    }

}
