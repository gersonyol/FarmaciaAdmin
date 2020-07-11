package com.example.ejemplowebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        switch(view.getId()){
            case R.id.btnInsertar:
                Intent intent = new Intent(getApplicationContext(), InsertarActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMostrar:
                Intent intent1 = new Intent(getApplicationContext(), MostrarActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnEliminar:
                Intent intent3 = new Intent(getApplicationContext(), EliminarActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnModificar:
                Intent intent4 = new Intent(getApplicationContext(), ModificarActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
