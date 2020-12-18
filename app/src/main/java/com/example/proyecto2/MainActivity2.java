package com.example.proyecto2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt_msg = findViewById(R.id.txt_msg);
        Button login_btn = findViewById(R.id.login_btn);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                txt_msg.setText("You can use thi FingerPrint Sensor to Login");
                txt_msg.setTextColor(Color.parseColor("#fafafa"));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                txt_msg.setText("The device don't have a fingerprint sensor");
                login_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                txt_msg.setText("The biometric sensor is currently unavailable");
                login_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                txt_msg.setText("Your device don;t have any fingerprint saved, please check your security settings");
                login_btn.setVisibility(View.GONE);
                break;
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity2.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //error authenticating, stop tasks that requires auth
                txt_msg.setText("Error de autenticacion: " + errString);
                Toast.makeText(MainActivity2.this, "Error de autenticacion: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //authentication succeed, continue tasts that requires auth
                txt_msg.setText("Autenticacion exitosa!");
                Toast.makeText(MainActivity2.this, "Autenticacion exitosa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                txt_msg.setText("Autenticacion Fallida");
                Toast.makeText(MainActivity2.this, "Autenticacion Fallida!", Toast.LENGTH_SHORT).show();
            }
        });


        //setup title,description on auth dialog
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion biometrica")
                .setSubtitle("Login utilizando autenticacion por huella")
                .setNegativeButtonText("Contraseña de usuario")
                .build();

        //handle authBtn click, start authentication
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show auth dialog
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }

    public void Siguiente(View view){
        Intent siguente = new Intent(this, MainActivity3.class);
        startActivity(siguente);
    }

    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}