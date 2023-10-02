package modelo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paytowing.MainActivity;

public class SplashActivity extends AppCompatActivity {

    //Muestra la pantalla de boot
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // evita regresar a éste activity
    }
}
