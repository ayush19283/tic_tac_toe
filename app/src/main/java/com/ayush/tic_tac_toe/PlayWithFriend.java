package com.ayush.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PlayWithFriend extends AppCompatActivity implements View.OnClickListener{
    String opp_username,my_name,opp_token,priority,response;
    Database db=new Database(this);
    Button b00,b01,b02,b10,b11,b12,b20,b21,b22,restart_match;
    TextView match_info,result;
    int chance=0,win=0;
    String[] w1= {"zero_zero","zero_one","zero_two"};
    String[] w2 ={"one_zero","one_one","one_two"};
    String[] w3= {"two_zero","two_one","two_two"};
    String[] w4= {"zero_zero","one_zero","two_zero"};
    String[] w5={"zero_one","one_one","two_one"};
    String[] w6={"zero_two","one_two","two_two"};
    String[] w7={"zero_zero","one_one","two_two"};
    String[] w8={"zero_two","one_one","two_zero"};

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
    Set<String> opponent_response=new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_friend);
        match_info=findViewById(R.id.participants);
        result=findViewById(R.id.text_result);
        restart_match=findViewById(R.id.restart);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        my_name=db.show();
        if(bundle != null){
            opp_username = bundle.getString("Username");
            opp_token=bundle.getString("Token");
            priority=bundle.getString("priority");
        }
        match_info.setText(my_name+" vs "+opp_username);
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
        System.out.println(v.getId());
        Button clicked_button=findViewById(v.getId());
        String cross_or_dot = clicked_button.getText().toString();
        String s = clicked_button.getResources().getResourceEntryName(v.getId());
        System.out.println(cross_or_dot+"    "+s);
        if(cross_or_dot.matches("X") || cross_or_dot.matches("0")){
            System.out.println("already marked");
        }
        else {
            if (chance % 2 == 0 && priority.matches("1")) {
                user1.add(s);
                System.out.println(" user 1  "+s);
                new PostRequest().execute("https://fcm.googleapis.com/fcm/send",opp_token,s);
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
                    result.setText("winner is "+my_name);
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                    win=1;
                }
                if(chance==9 && win==0){
                    result.setText("Match Draw");
                    result.setVisibility(View.VISIBLE);
                    restart_match.setVisibility(View.VISIBLE);
                }

            } else if(priority.matches("2") && chance%2!=0) {
                user2.add(s);
                System.out.println("user2    "+s);
                clicked_button.setText("0");
                chance++;
                new PostRequest().execute("https://fcm.googleapis.com/fcm/send",opp_token,s);
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
                    result.setText("winner is "+my_name);
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
    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyData")
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            chance++;
            response=intent.getExtras().getString("response"); //setting values to the TextViews
            opponent_response.add(response);
            if(priority.matches("1")){
                if(response.matches("zero_zero"))
                {
                    b00.setText("0");
                }
                else if(response.matches("zero_one"))
                {
                    b01.setText("0");
                }
                else if(response.matches("zero_two"))
                {
                    b02.setText("0");
                }
                else if(response.matches("one_zero"))
                {
                    b10.setText("0");
                }
                else if(response.matches("one_one"))
                {
                    b11.setText("0");
                }
                else if(response.matches("one_two"))
                {
                    b12.setText("0");
                }
                else if(response.matches("two_zero"))
                {
                    b20.setText("0");
                }
                else if(response.matches("two_one"))
                {
                    b21.setText("0");
                }
                else if(response.matches("two_two"))
                {
                    b22.setText("0");
                }
            }else{
                if(response.matches("zero_zero"))
                {
                    b00.setText("X");
                }
                else if(response.matches("zero_one"))
                {
                    b01.setText("X");
                }
                else if(response.matches("zero_two"))
                {
                    b02.setText("X");
                }
                else if(response.matches("one_zero"))
                {
                    b10.setText("X");
                }
                else if(response.matches("one_one"))
                {
                    b11.setText("X");
                }
                else if(response.matches("one_two"))
                {
                    b12.setText("X");
                }
                else if(response.matches("two_zero"))
                {
                    b20.setText("X");
                }
                else if(response.matches("two_one"))
                {
                    b21.setText("X");
                }
                else if(response.matches("two_two"))
                {
                    b22.setText("X");
                }

            }
            boolean result1 = opponent_response.containsAll(wset1);
            boolean result2 = opponent_response.containsAll(wset2);
            boolean result3 = opponent_response.containsAll(wset3);
            boolean result4 = opponent_response.containsAll(wset4);
            boolean result5 = opponent_response.containsAll(wset5);
            boolean result6 = opponent_response.containsAll(wset6);
            boolean result7 = opponent_response.containsAll(wset7);
            boolean result8 = opponent_response.containsAll(wset8);

            if (result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {
                System.out.println("opponent is winner");
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
                result.setText("winner is "+opp_username);
                result.setVisibility(View.VISIBLE);
                restart_match.setVisibility(View.VISIBLE);
            }
        }
    };

}