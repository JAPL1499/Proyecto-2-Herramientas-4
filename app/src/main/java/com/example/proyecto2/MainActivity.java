package com.example.proyecto2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Siguiente1(View view){
        Intent siguente1 = new Intent(this, MainActivity2.class);
        startActivity(siguente1);
    }
}