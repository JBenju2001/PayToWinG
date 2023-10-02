package com.example.paytowing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import modelo.Usuario;

public class MenuActivity extends AppCompatActivity {

    Usuario u;
    int yearInit=2011;
    String month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bundle = getIntent().getExtras();

        //Establece el usuario a partir del que invocaste con el email y la contraseña
        try {
            u = (Usuario) bundle.getSerializable("usuario");

        }
        catch (Exception e){
            u=null;
        }

        if (u!=null){
            TextView tnu=(TextView) findViewById(R.id.NombreU);
            tnu.setText(u.getNombre());
        }

        TextView tfa=(TextView) findViewById(R.id.fechaActual);
        month=fijarMes(Calendar.getInstance().get(Calendar.MONTH));
        year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        tfa.setText(month+"/"+year);
        /*ArrayList<Gasto> gastos= (ArrayList<Gasto>) new  controladores.CargarResumenMes().run(u,
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.YEAR));*/
        //Te devuelve al comienzo
        Button bcs=(Button) findViewById(R.id.buttonCerrarSesion);
        bcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Te lleva a la ventana visualizar
        Button bvd=(Button) findViewById(R.id.buttonVisualizar);
        bvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                Intent i=new Intent(MenuActivity.this,VisualiceActivity.class);
                i.putExtra("usuario",u);
                startActivity(i);
                finish();*/
            }
        });

        //Te lleva a la ventana visualizar
        Button bg=(Button) findViewById(R.id.buttonGrafico);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                Intent i=new Intent(MenuActivity.this,GraficoActivity.class);
                i.putExtra("usuario",u);
                startActivity(i);
                finish();*/
            }
        });

        //Te lleva a la ventana añadir
        Button ba=(Button) findViewById(R.id.buttonAnnadir);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                Intent i=new Intent(MenuActivity.this,AnnadirActivity.class);
                i.putExtra("usuario",u);
                startActivity(i);
                finish();*/
            }
        });
    }

    //Relaciona los meses con un número
    private String fijarMes(int m){
        switch (m+1){
            case 1: return getString(R.string.mes1);
            case 2: return getString(R.string.mes2);
            case 3: return getString(R.string.mes3);
            case 4: return getString(R.string.mes4);
            case 5: return getString(R.string.mes5);
            case 6: return getString(R.string.mes6);
            case 7: return getString(R.string.mes7);
            case 8: return getString(R.string.mes8);
            case 9: return getString(R.string.mes9);
            case 10: return getString(R.string.mes10);
            case 11: return getString(R.string.mes11);
            case 12: return getString(R.string.mes12);
        }
        return null;
    }

}