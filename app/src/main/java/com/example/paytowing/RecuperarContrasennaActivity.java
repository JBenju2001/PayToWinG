package com.example.paytowing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import modelo.Usuario;

public class RecuperarContrasennaActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseUser user;
    Usuario usuario;
    EditText nombre, contrasenna;
    String email, contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenna);


        usuario = null;

        nombre = (EditText) findViewById(R.id.editTextMainNombre);
        contrasenna = (EditText) findViewById(R.id.editPassword);
        Button bl = (Button) findViewById(R.id.buttonMainLog);
        Button bv = (Button) findViewById(R.id.buttonVolver2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message1");

        myRef.setValue("Hello, World!1");

        /*bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                //Establece las variables de email y contraseña como los text views
                email = nombre.getText().toString();
                //Codigo que carga un usuario por defecto. Se usa para hacer pruebas rapidas
                //email="benjullb@gmail.com";
                //contra="Benju2001";

                //Compruba que en efecto los text views no estan vacios antes de revisarlos
                if (!email.equals("")) {
                    //Buscamos un usuario en firebase a potir del email y la contraseña
                    mAuth.sendPasswordResetEmail(email).
                            addOnCompleteListener(RecuperarContrasennaActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //En caso de exito llamamos al usuario para abrir la aplicación
                                        user = mAuth.getCurrentUser();
                                        Toast.makeText(RecuperarContrasennaActivity.this, "correcto ", Toast.LENGTH_SHORT).show();
                                        //Preparamos todo para abrir el menu, la siguiente ventana y el usuario que pasaremos
                                        Intent i = new Intent(RecuperarContrasennaActivity.this, MenuActivity.class);

                                        i.putExtra("usuario", new Usuario(user.getEmail(), contra, user.getUid()));
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference("message3");

                                        myRef.setValue("Hello, World!3");

                                        startActivity(i);
                                        finish();
                                    } else {
                                        // Comunica si ocurrio algún error
                                        nombre.setText("");
                                        contrasenna.setText("");
                                        Toast.makeText(RecuperarContrasennaActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    //Comunica si hay campos vaciós
                    Toast.makeText(RecuperarContrasennaActivity.this, "Email necesario.",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });*/

        bv.setOnClickListener(new View.OnClickListener() {
            //Te lleva a la ventana de creación de usuario
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecuperarContrasennaActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}