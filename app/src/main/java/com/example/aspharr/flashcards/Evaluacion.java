package com.example.brian.flashcars;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Evaluacion extends AppCompatActivity {
    TextView WordsView;
    String words[];
    int start; //lugar inicial en array
    int end; //lugar final en array
    int i = 0;
    int numw = 0; //numero de palabra dentro de array
    int lvl;
    ImageButton correct;
    ImageButton incorrect;
    int green = 0;
    int red = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recibirNivel(); //Se reciben los valores segun el nivel

       // final ImageButton next = findViewById(R.id.btnCambiar);
        correct = findViewById(R.id.correcto);
        correct.setVisibility(View.INVISIBLE);
        incorrect = findViewById(R.id.incorrecto);
        incorrect.setVisibility(View.INVISIBLE);
        WordsView = findViewById(R.id.WordsView);
        //Boton para regresar a los niveles
        ImageButton back = findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), NivelesPractica.class);
                startActivity(intent);
                finish();
            }
        });


        try {
            Datos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        show();
        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red++;
                correct.setVisibility(View.INVISIBLE);
                incorrect.setVisibility(View.INVISIBLE);
                if (numw <= end) {
                    show();
                } else {
                    calificacion();
                    Intent intent = new Intent(getApplicationContext(), NivelesPractica.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green++;
                correct.setVisibility(View.INVISIBLE);
                incorrect.setVisibility(View.INVISIBLE);
                if (numw <= end) {
                    show();
                } else {
                    calificacion();
                    Intent intent = new Intent(getApplicationContext(), NivelesPractica.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
    public void recibirNivel() {
        Bundle recivelvl = getIntent().getExtras();
        end = recivelvl.getInt("end");
        lvl = recivelvl.getInt("lvl");
    } //Bring value from level
    public void Datos() throws IOException {
        words = new String[10];
        String line;
        InputStream is = this.getResources().openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while ((i <= end) && (line = reader.readLine()) != null) {
                words[i] = (line.split(";")[lvl]);
                i++;
            }

        }

       // is.close();

    } //Get data from txt
    public void show() {
            WordsView.setText(words[numw]);
            WordsView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WordsView.setText("");
                    correct.setVisibility(View.VISIBLE);
                    incorrect.setVisibility(View.VISIBLE);
                    numw++;
                }
            }, 3000);


    }
    public  void calificacion(){
           WordsView.setTextSize(50);
        WordsView.setText("Aciertos: "+green + "\nErrores: "+red);
        WordsView.postDelayed(new Runnable() {
            @Override
            public void run() {
                WordsView.setText("");
            }
        },5000);



            }

}
