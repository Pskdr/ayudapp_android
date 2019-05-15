package com.example.appalertas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PantallaDatosGenerales extends AppCompatActivity {
    ImageButton btnModificarDG, btnRDatosGenerales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos_generales);
        conectar();
        btnModificarDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Se ha guardado la informacion", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), PantallaMiPerfil.class);
                startActivity(i);
            }
        });
        btnRDatosGenerales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PantallaMiPerfil.class);
                startActivity(i);
            }
        });

    }
    // Prueba numero uno
    private void conectar(){
        btnModificarDG = findViewById(R.id.btnModificarDG);
        btnRDatosGenerales = findViewById(R.id.btnRDatosGenerales);

    }

}
