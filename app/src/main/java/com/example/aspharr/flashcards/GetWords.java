package com.example.brian.flashcars;

import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetWords implements Runnable {
        int nwords;
        int lvl;
        int i=0;
        int t=1;
        ImageButton next;
        ImageButton back;
        TextView WordsView;
        String words [];
        int numw;

        public GetWords(int nwords, int lvl, ImageButton next, ImageButton back, TextView WordsView,String words[], int numw){
        this.nwords=nwords;
        this.lvl=lvl;
        this.next=next;
        this.back=back;
        this.WordsView=WordsView;
        this.words=words;
        this.numw=numw;

        }
    @Override
    public void run() {



        }

        public void start(){
            WordsView.setText(words[numw]);

            WordsView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    WordsView.setText("");

                }
            },3000);


        }




}
