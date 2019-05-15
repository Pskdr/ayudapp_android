package com.example.appalertas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PantallaMiPerfil extends AppCompatActivity {
    ImageButton btnDatosGrles, btnInfoAdicional, btnRMiPerfil;
    ImageButton btnModificar, btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_mi_perfil);

        Conectar();
        btnDatosGrles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaDatosGenerales.class);
                startActivity(i);
            }
        });

        btnRMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaEmergenciaPrincipal.class);
                startActivity(i);
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaDatosGenerales.class);
                startActivity(i);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaDatosGenerales.class);
                startActivity(i);
            }
        });
    }


    private void Conectar() {
        btnDatosGrles = findViewById(R.id.btnDatosGenerales);
        btnInfoAdicional = findViewById(R.id.btnInformacionAdicional);
        btnRMiPerfil = findViewById(R.id.btnRMiPerfil);
        btnModificar = findViewById(R.id.btnModificar);
        btnAgregar = findViewById(R.id.btnAgregar);

    }
}
