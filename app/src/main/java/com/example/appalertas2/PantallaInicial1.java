package com.example.appalertas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PantallaInicial1 extends AppCompatActivity {
    ImageButton btnIngresar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial1);
        conectar();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PantallaInicial2.class);
                startActivity(i);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PantallaRegistrar1.class);
                startActivity(i);
            }
        });

    }


    private void conectar(){
        btnIngresar = findViewById(R.id.btnIngresarInicio1);
        btnRegistrar = findViewById(R.id.btnRegistrarInicio1);

    }

}
