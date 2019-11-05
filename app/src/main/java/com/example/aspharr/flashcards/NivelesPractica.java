package com.example.brian.flashcars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class NivelesPractica extends AppCompatActivity {

    ImageButton regresar;
    int lvl;
    int end;
    ToggleButton practica;
    ToggleButton evaluacion;
    Class selected=Practica.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_practica);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        practica = findViewById(R.id.btnpractica);
        evaluacion = findViewById(R.id.btnEvaluacion);
        practica.setBackgroundColor(Color.BLUE);
        evaluacion.setBackgroundColor(Color.GRAY);
        regresar = findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        practica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practica.setChecked(true);
                practica.setBackgroundColor(Color.BLUE);
                evaluacion.setChecked(false);
                evaluacion.setBackgroundColor(Color.GRAY);
                selected = Practica.class;

                 }
        });
        evaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluacion.setChecked(true);
                evaluacion.setBackgroundColor(Color.BLUE);
                practica.setChecked(false);
                practica.setBackgroundColor(Color.GRAY);
                selected=Evaluacion.class;
            }
        });


       Button lvl1=  findViewById(R.id.lvl1);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getApplicationContext(), selected);
                end = 1;
                lvl = 0;
                intent.putExtra("end",end);
                intent.putExtra("lvl",lvl);
                startActivity(intent);
                finish();

            }
        });
        Button lvl2= findViewById(R.id.lvl2);
        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),selected);
                end = 5;
                lvl = 1;
                intent.putExtra("lvl",lvl);
                intent.putExtra("end",end);
                startActivity(intent);
                finish();

            }
        });
        Button lvl3= findViewById(R.id.lvl3);
        lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),selected);
                end = 5;
                lvl = 2;
                intent.putExtra("lvl",lvl);
                intent.putExtra("end",end);
                startActivity(intent);
                finish();

            }
        });
        Button lvl4= findViewById(R.id.lvl4);
        lvl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),selected);
                end = 7;
                lvl = 3;
                intent.putExtra("lvl",lvl);
                intent.putExtra("end",end);
                startActivity(intent);
                finish();

            }
        });
        Button lvl5= findViewById(R.id.lvl5);
        lvl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),selected);
                end = 5;
                lvl = 4;
                intent.putExtra("lvl",lvl);
                intent.putExtra("end",end);
                startActivity(intent);
                finish();

            }
        });
        Button lvl6= findViewById(R.id.lvl6);
        lvl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),selected);
                end = 5;
                lvl = 5;
                intent.putExtra("lvl",lvl);
                intent.putExtra("end",end);
                startActivity(intent);
                finish();

            }
        });
    }


}