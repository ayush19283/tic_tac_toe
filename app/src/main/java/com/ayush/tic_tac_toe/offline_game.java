package com.ayush.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class offline_game extends AppCompatActivity implements View.OnClickListener {
    Button b00,b01,b02,b10,b11,b12,b20,b21,b22,restart_match;
    String[] w1= {"zero_zero","zero_one","zero_two"};
    String[] w2 ={"one_zero","one_one","one_two"};
    String[] w3= {"two_zero","two_one","two_two"};
    String[] w4= {"zero_zero","one_zero","two_zero"};
    String[] w5={"zero_one","one_one","two_one"};
    String[] w6={"zero_two","one_two","two_two"};
    String[] w7={"zero_zero","one_one","two_two"};
    String[] w8={"zero_two","one_one","two_zero"};
    int win=0;
    TextView result,match_info;
    Set<String> wset1 = new HashSet<String>();
    Set<String> wset2 = new HashSet<String>();
    Set<String> wset3 = new HashSet<String>();
    Set<String> wset4 = new HashSet<String>();
    Set<String> wset5 = new HashSet<String>();
    Set<String> wset6 = new HashSet<String>();
    Set<String> wset7 = new HashSet<String>();
    Set<String> wset8 = new HashSet<String>();
    int chance=0;
    Set<String> user1=new HashSet<String>();
    Set<String> user2=new HashSet<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.purple));
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.pink));
        }
        result=findViewById(R.id.text_result);
        restart_match=findViewById(R.id.restart);
        match_info=findViewById(R.id.participants);

        b00=findViewById(R.id.zero_zero);
        b01=findViewById(R.id.zero_one);
        b02=findViewById(R.id.zero_two);
        b10=findViewById(R.id.one_zero);
        b11=findViewById(R.id.one_one);
        b12=findViewById(R.id.one_two);
        b20=findViewById(R.id.two_zero);
        b21=findViewById(R.id.two_one);
        b22=findViewById(R.id.two_two);

        findViewById(R.id.zero_one).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.zero_zero).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.zero_two).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.one_zero).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.one_one).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.one_two).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.two_zero).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.two_one).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.two_two).setOnClickListener((View.OnClickListener) this);
        restart_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(offline_game.this,MainActivity.class));
            }
        });
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

    @Override
    public void onClick(View v) {
        Button clicked_button=findViewById(v.getId());
        String cross_or_dot = clicked_button.getText().toString();
        String s = clicked_button.getResources().getResourceEntryName(v.getId());
        System.out.println(cross_or_dot+"    "+s);
        if(cross_or_dot.matches("X") || cross_or_dot.matches("0")){
            System.out.println("already marked");
        }
        else {
            if (chance % 2 == 0) {
                user1.add(s);
                System.out.println(" user 1  "+s);
                clicked_button.setText("X");
                chance++;
                boolean result1 = user1.containsAll(wset1);
                boolean result2 = user1.containsAll(wset2);
                boolean result3 = user1.containsAll(wset3);
                boolean result4 = user1.containsAll(wset4);
                boolean result5 = user1.containsAll(wset5);
                boolean result6 = user1.containsAll(wset6);
                boolean result7 = user1.containsAll(wset7);
                boolean result8 = user1.containsAll(wset8);

                if (result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {
                    if (result1) {
                        Iterator<String> it = wset1.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                        }
                    } else if (result2) {
                        Iterator<String> it = wset2.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.one_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_two);
                            bt.setTextColor(Color.RED);
                        }
                    } else if (result3) {
                        Iterator<String> it = wset3.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result4){
                        Iterator<String> it = wset4.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result5){
                        Iterator<String> it = wset5.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_one);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result6){
                        Iterator<String> it = wset6.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_two);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result7){
                        Iterator<String> it = wset7.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result8){
                        Iterator<String> it = wset8.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                        }
                    }
                    System.out.println("winner user1");
                    result.setText("winner is X");
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                    win=1;
                }
                if(chance==9 && win==0){
                    result.setText("Match Draw");
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                }

            } else if(chance%2!=0) {
                user2.add(s);
                System.out.println("user2    "+s);
                clicked_button.setText("0");
                chance++;
                boolean result1 = user2.containsAll(wset1);
                boolean result2 = user2.containsAll(wset2);
                boolean result3 = user2.containsAll(wset3);
                boolean result4 = user2.containsAll(wset4);
                boolean result5 = user2.containsAll(wset5);
                boolean result6 = user2.containsAll(wset6);
                boolean result7 = user2.containsAll(wset7);
                boolean result8 = user2.containsAll(wset8);

                if (result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {

                    if (result1) {
                        Iterator<String> it = wset1.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                        }
                    } else if (result2) {
                        Iterator<String> it = wset2.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.one_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_two);
                            bt.setTextColor(Color.RED);
                        }
                    } else if (result3) {
                        Iterator<String> it = wset3.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result4){
                        Iterator<String> it = wset4.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result5){
                        Iterator<String> it = wset5.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_one);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result6){
                        Iterator<String> it = wset6.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_two);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result7){
                        Iterator<String> it = wset7.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.zero_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.two_two);
                            bt.setTextColor(Color.RED);
                        }
                    }else if(result8){
                        Iterator<String> it = wset8.iterator();
                        while (it.hasNext()) {
                            System.out.print(it.next() + " ");
                            Button bt;
                            bt = findViewById(R.id.two_zero);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.one_one);
                            bt.setTextColor(Color.RED);
                            bt = findViewById(R.id.zero_two);
                            bt.setTextColor(Color.RED);
                        }
                    }
                    System.out.println("winner user2");
                    result.setText("winner is 0");
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                    win=1;


                }
                if(chance==9 && win==0){
                    result.setText("Match Draw");
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                }
            }else{
                System.out.println("not your chance");
            }
        }
    }
}