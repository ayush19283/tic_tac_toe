package com.ayush.tic_tac_toe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.UiAutomation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    TextView online,offline_play,play_custom;
    String Token,log_name,priority,token="",opponent_user,url="https://747d-2409-4053-e09-205b-791c-b3b8-9746-22cd.in.ngrok.io";
    Button forw_url;
    Database db=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Globals.login_name=db.show();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("GPSLocationUpdates"));
        log_name=db.show();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            log_name = bundle.getString("log_name");
        }

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        }

        forw_url=findViewById(R.id.options);
        online=findViewById(R.id.online);
        offline_play=findViewById(R.id.offline);
        play_custom=findViewById(R.id.friends);
        forw_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        offline_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offline_play.setAlpha(0.1f);
                startActivity(new Intent(MainActivity.this,offline_game.class));
            }
        });

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                online.setAlpha(0.1f);
                new RequestTask2().execute(url+"/updateStatus/"+log_name+"/1");
                new RequestTask2().execute(url + "/find");
            }
        });

        play_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_custom.setAlpha(0.1f);
                startActivity(new Intent(MainActivity.this,CustomGame.class));
            }
        });
    }
    public void startActivity(){
        Intent intent = new Intent(MainActivity.this, online_gameActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("priority",priority);
        intent.putExtra("opp_username",opponent_user);
        intent.putExtra("username",log_name);
        intent.putExtra("server_url",url);
        startActivity(intent);
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //   builder.setTitle("Safaricom");
        builder.setMessage("Enter forwarding URL");
        View view = this.getLayoutInflater().inflate(R.layout.dialogue_layout, null);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setPositiveButton("YES", (dialog, which) -> {
            EditText editTextPIN = view.findViewById(R.id.editTextInput);
            String pin = editTextPIN.getText().toString();
            System.out.println(pin);
            url=pin;
            Globals.url=pin;
            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
                if (!TextUtils.isEmpty(token)) {
                    Token=token;
                    Log.d(TAG, "retrieve token successful : " + Token);
                    new RequestTask2().execute(url+"/update/"+log_name+"/"+token);
                } else{
                    Log.w(TAG, "token should not be null...");
                }
            }).addOnFailureListener(e -> {
            }).addOnCanceledListener(() -> {
            }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));
            Toast.makeText(this, pin, Toast.LENGTH_SHORT).show();
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("broadcastReceiver running   ");
            opponent_user = intent.getStringExtra("username");
            priority = intent.getStringExtra("priority");
            token = intent.getStringExtra("token");
            System.out.println("token broadcast checking   "+token);
            startActivity();
        }
    };
}