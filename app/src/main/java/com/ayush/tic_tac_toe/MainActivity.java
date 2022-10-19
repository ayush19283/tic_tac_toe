package com.ayush.tic_tac_toe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    TextView online;
    String Token;
    Database db=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiii         ");
//        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
//            if (!TextUtils.isEmpty(token)) {
//                Token=token;
//                Log.d(TAG, "retrieve token successful : " + Token);
//                new RequestTask2().execute(Globals.url+"/update/"+db.show()+"/"+token);
//                System.out.println(token);
//
//            } else{
//                Log.w(TAG, "token should not be null...");
//            }
//        }).addOnFailureListener(e -> {
//        }).addOnCanceledListener(() -> {
//        }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));
        Globals.login_name=db.show();
//        System.out.println("updating    "+Globals.url+"/update/"+db.show()+"/"+Token);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        }

        Globals.demo=MainActivity.this;
        online=findViewById(R.id.online);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.searching=1;
                new RequestTask2().execute(Globals.url+"/updateStatus/"+Globals.login_name+"/1");
//                new GetRequest().execute(Globals.url+"/find");
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while(true) {
                                if(Globals.searching==1) {
                                    sleep(6000);

                                    new RequestTask2().execute(Globals.url + "/find");
                                    System.out.println(Globals.result + "   result global");
                                    if (Globals.res==1) {
                                        System.out.println(Globals.result + " balle balle");
                                        startActivity(new Intent(MainActivity.this, online_gameActivity.class));
                                        Globals.res=0;
                                    }
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();
            }
        });
    }

}