package com.ayush.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Find_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetRequest().execute();
    }
}