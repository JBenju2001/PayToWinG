package com.example.paytowing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import modelo.Usuario;

public class MainActivity extends AppCompatActivity {


    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_PayToWinG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usuario = null;
        Button bl = (Button) findViewById(R.id.buttonMainLog);
        Button bc = (Button) findViewById(R.id.buttonCrearUsuario);

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LogActivity.class);
                startActivity(i);
                finish();
            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            //Te lleva a la ventana de creación de usuario
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}