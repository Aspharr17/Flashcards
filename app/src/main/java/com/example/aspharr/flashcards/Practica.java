package com.example.brian.flashcars;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Practica extends AppCompatActivity {

    TextView WordsView; //Espacio donde aparecen las palabras
    String words[];     //Arreglo para guardar las palabras que serán mostradas
    TextToSpeech t1;    //Lector de palabras
    int end; //lugar final en array
    int i = 0;          //Contador de palabras
    int numw=0; //numero de palabra dentro de array
    int lvl;            //Tope final de palabras segun el nivel (fila en txt)
    SoundPool sp;       //Parte del lector
    ImageButton next;   //Boton de cambio de palabras
    ImageButton back;   //Boton para regresar a los niveles

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recibirNivel(); //Se reciben los valores segun el nivel

        //Objetos en pantalla
        next = findViewById(R.id.btnCambiar); // Busca el boton next
        next.setVisibility(View.INVISIBLE); //Se oculta el boton para evitar que el usuario presione continuamente
        WordsView = findViewById(R.id.WordsView); //Busca el Wordview donde se mostrará la palabra
        back = findViewById(R.id.backbtn); //Boton para regresar a los niveles
        //Regresa a pantalla de niveles al presionar back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),NivelesPractica.class);
                startActivity(intent);
                finish();
            }
        });

        //Crea el objeto para leer las palabras
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.getDefault());
                    t1.setSpeechRate(.7f);
                }
            }
        });
        //Llama a los datos del txt
        try {
            Datos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WordsView.setText("");
        WordsView.postDelayed(new Runnable() {
            @Override
            public void run() {
                WordsView.setText(words[numw]);
                t1.speak(words[numw],TextToSpeech.QUEUE_FLUSH,null);

            }
        },2000);
        WordsView.postDelayed(new Runnable() {
            @Override
            public void run() {
                WordsView.setText("");
                numw++;
                next.setVisibility(View.VISIBLE);
            }
        },5000);
        //Al presionar el boton next, cambia a la siguiente palabra
        next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    next.setVisibility(View.INVISIBLE);
                        if(numw<=end) {
                            show();
                            numw++;
                        }
                        else {
                            WordsView.setText("Fin del Nivel");
                            Intent intent = new Intent(getApplicationContext(), NivelesPractica.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
        }
    public void  recibirNivel(){
        Bundle recivelvl = getIntent().getExtras();
        end = recivelvl.getInt("end");
        lvl = recivelvl.getInt("lvl");
        } //Bring value from level
    public void Datos() throws IOException {
        words =new String[10];
        String line;
        InputStream is = this.getResources().openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(is!=null){
            while ((i<=end)&&(line=reader.readLine())!=null){
                words[i]=(line.split(";")[lvl]);
                i++;
            }

        }

        //is.close();



    } //Get data from txt
    public void show(){

            WordsView.setText("");
            t1.speak(words[numw],TextToSpeech.QUEUE_FLUSH,null);
            WordsView.setText(words[numw]);

            WordsView.postDelayed(new Runnable() {
                @Override
                public void run() {

                    WordsView.setText("");
                    next.setVisibility(View.VISIBLE); //Aparece el boton para poder cambiar
                }
            }, 2500);



        } //show the word, wait and disapears

}
