package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int whip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor==null)
            Toast.makeText(MainActivity4.this, "Sensor Unavailabe!", Toast.LENGTH_SHORT).show();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                System.out.println("Valor giro:" + x);
                if (x<-5 && whip >= 0){
                    whip++;
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if (x>5 && whip >= 0){
                    whip++;
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else if (y<-5 && whip >= 0){
                    whip++;
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else if (y>5 && whip >= 0){
                    whip++;
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                if (whip == 4)
                    whip =0;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }
    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }

    public void Inicio(View view){
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio);
    }

    public void Regresar3(View view){
        Intent regresar3 = new Intent(this, MainActivity3.class);
        startActivity(regresar3);
    }
}