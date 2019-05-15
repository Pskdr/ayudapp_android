package com.example.appalertas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PantallaInformacionAdicional extends AppCompatActivity {
    ImageButton btnInformacionAdicional,btnModificarIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_informacion_adicional);
        Conectar();
        btnInformacionAdicional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaMiPerfil.class);
                startActivity(i);
            }
        });
    }
    private void Conectar() {
        btnInformacionAdicional = findViewById(R.id.btnInformacionAdicional);
        btnModificarIA = findViewById(R.id.btnModificarIA);


    }
}
