package com.example.zero_cross;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.util.*;
import android.widget.Button;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    int chance=0;
    int win;

//    String w1 ="zero_zero,zero_one,zero_two";
//    String w2 ="one_zero,one_one,one_two";
//    String w3= "two_zero,two_one,two_two";
//    String w4= "zero_zero,one_zero,two_zero";
//    String w5="zero_one,one_one,two_one";
//    String w6="zero_two,one_two,two_two";
//    String w7="zero_zero,one_one,two_two";
//    String w8="zero_two,one_one,two_zero";
    String[] w1= {"zero_zero","zero_one","zero_two"};
    String[] w2 ={"one_zero","one_one","one_two"};
    String[] w3= {"two_zero","two_one","two_two"};
    String[] w4= {"zero_zero","one_zero","two_zero"};
    String[] w5={"zero_one","one_one","two_one"};
    String[] w6={"zero_two","one_two","two_two"};
    String[] w7={"zero_zero","one_one","two_two"};
    String[] w8={"zero_two","one_one","two_zero"};

   // List<String> w1=new ArrayList<String>();
    //w1.add()
    Set<String> wset1 = new HashSet<String>();
    Set<String> wset2 = new HashSet<String>();
    Set<String> wset3 = new HashSet<String>();
    Set<String> wset4 = new HashSet<String>();
    Set<String> wset5 = new HashSet<String>();
    Set<String> wset6 = new HashSet<String>();
    Set<String> wset7 = new HashSet<String>();
    Set<String> wset8 = new HashSet<String>();


    Set<String> user1=new HashSet<String>();
    Set<String> user2=new HashSet<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.zero_one).setOnClickListener(this);
        findViewById(R.id.zero_zero).setOnClickListener(this);
        findViewById(R.id.zero_two).setOnClickListener(this);
        findViewById(R.id.one_zero).setOnClickListener(this);
        findViewById(R.id.one_one).setOnClickListener(this);
        findViewById(R.id.one_two).setOnClickListener(this);
        findViewById(R.id.two_zero).setOnClickListener(this);
        findViewById(R.id.two_one).setOnClickListener(this);
        findViewById(R.id.two_two).setOnClickListener(this);


        for (String v : w1) {
            wset1.add(v);
        }
        for (String v : w2) {
            wset2.add(v);
        }

        for (String v : w3) {
            wset3.add(v);
        }
        for (String v : w4) {
            wset4.add(v);
        }
        for (String v : w5) {
            wset5.add(v);
        }
        for (String v : w6) {
            wset6.add(v);
        }
        for (String v : w7) {
            wset7.add(v);
        }
        for (String v : w8) {
            wset8.add(v);
        }



    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        int id = view.getId();



        Button clicked_button=findViewById(id);
        String cross_or_dot = clicked_button.getText().toString();

        String s = clicked_button.getResources().getResourceEntryName(id);
        System.out.println(s+"id   id  495u49tujrigp fjk ");
        if (cross_or_dot=="X" || cross_or_dot=="0"){

        }
        else{
            if(chance%2==0){
                clicked_button.setText("X");
                user1.add(s);
                System.out.println(user1);
                boolean result1 = user1.containsAll(wset1);
                boolean result2= user1.containsAll(wset2);
                boolean result3 = user1.containsAll(wset3);
                boolean result4 = user1.containsAll(wset4);
                boolean result5 = user1.containsAll(wset5);
                boolean result6 = user1.containsAll(wset6);
                boolean result7 = user1.containsAll(wset7);
                boolean result8 = user1.containsAll(wset8);



                if(result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {
                    System.out.println("winner");

                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("win", 1);
                    startActivity(intent);

                }
//                if(chance==9){
//                    declare();
//                }
//                if(chance==9)
//                {
//                    System.out.println("draw");
//                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
//                    intent.putExtra("win",0);
//                    startActivity(intent);
//                }

            }
            else{
                clicked_button.setText("0");
                user2.add(s);
                System.out.println(user2+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                boolean result1 = user2.containsAll(wset1);
                boolean result2= user2.containsAll(wset2);
                boolean result3 = user2.containsAll(wset3);
                boolean result4 = user2.containsAll(wset4);
                boolean result5 = user2.containsAll(wset5);
                boolean result6 = user2.containsAll(wset6);
                boolean result7 = user2.containsAll(wset7);
                boolean result8 = user2.containsAll(wset8);

                if(result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8)
                {   System.out.println("winner");

                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("win",2);
                    startActivity(intent);

                }


//                if(chance==9)
//                {
//                    System.out.println("draw");
//                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
//                    intent.putExtra("win",0);
//                    startActivity(intent);
//                }


            }
            chance++;
            if(chance==9){
                declare();
            }
        }
    }
  public void declare(){
      System.out.println("draw");
      Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
      intent.putExtra("win",3);
      startActivity(intent);
  }

}

