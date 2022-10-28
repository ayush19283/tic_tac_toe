package com.ayush.tic_tac_toe;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CustomGame extends AppCompatActivity {
    Button btn_create,btn_join,btn_copy,btn_play;
    EditText get_code;
    TextView show_code;
    String code,log_name,my_token,opp_token,opp_username,custom_username;
    Database db=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("CustomGame"));
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver1, new IntentFilter("CustomUsername"));
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        }
        log_name=db.show();
        btn_copy=findViewById(R.id.copy_code);
        btn_create=findViewById(R.id.create);
        btn_join=findViewById(R.id.join);
        btn_play=findViewById(R.id.proceed);
        show_code=findViewById(R.id.token);
        get_code=findViewById(R.id.code);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_create.setVisibility(View.GONE);
                get_code.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.VISIBLE);
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code=get_code.getText().toString();
                new PostRequest2().execute(my_token,log_name,code);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_join.setVisibility(View.GONE);
                show_code.setVisibility(View.VISIBLE);
                btn_copy.setVisibility(View.VISIBLE);
                FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
                    if (!TextUtils.isEmpty(token)) {
                        Log.d(TAG, "retrieve token successful : " + token);
                        my_token=token;
                        show_code.setText(token);
                    } else{
                        Log.w(TAG, "token should not be null...");
                    }
                }).addOnFailureListener(e -> {
                }).addOnCanceledListener(() -> {
                }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));
            }
        });
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label="copied";
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
                    if (!TextUtils.isEmpty(token)) {
                        Log.d(TAG, "retrieve token successful : " + token);
                        setClipboard(CustomGame.this,token);
                    } else{
                        Log.w(TAG, "token should not be null...");
                    }
                }).addOnFailureListener(e -> {
                }).addOnCanceledListener(() -> {
                }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));

                Toast.makeText(CustomGame.this,"CODE COPIED",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }



    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("broadcastReceiver running   ");
            opp_username = intent.getStringExtra("username");
            opp_token = intent.getStringExtra("token");
            System.out.println("token broadcast checking   "+opp_username);
            new PostRequest3().execute(opp_token,log_name);
            Intent intent1 = new Intent(CustomGame.this, PlayWithFriend.class);
            intent1.putExtra("Username", opp_username);  // pass your values and retrieve them in the other Activity using keyName
            intent1.putExtra("Token",opp_token);
            intent1.putExtra("priority","1");
            startActivity(intent1);

        }
    };
    private BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("broadcastReceiver running   ");
            custom_username = intent.getStringExtra("username");
            System.out.println("token broadcast checking   "+opp_username);
            Intent intent1 = new Intent(CustomGame.this, PlayWithFriend.class);
            intent1.putExtra("Username", custom_username);  // pass your values and retrieve them in the other Activity using keyName
            intent1.putExtra("Token",code);
            intent1.putExtra("priority","2");
            startActivity(intent1);
        }
    };


}