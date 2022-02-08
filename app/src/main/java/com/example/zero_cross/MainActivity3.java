package com.example.zero_cross;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.view.View;
import android.widget.TextView;


public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int score=getIntent().getIntExtra("win",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView txt=(TextView) findViewById(R.id.changer);

        if(score==1){
        txt.setText("WINNER IS PLAYER 1");}
        else if(score==2){
            txt.setText("WINNER IS PLAYER 2");
        }
        else if(score==3){
            txt.setText("DRAW");
        }
        Button play_button = findViewById(R.id.play_ag);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }





    }
