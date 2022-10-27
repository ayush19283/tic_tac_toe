package com.ayush.tic_tac_toe;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity {
    EditText get_Name;
    Button btn_login;
    String text,Token,log_name,url="https://747d-2409-4053-e09-205b-791c-b3b8-9746-22cd.in.ngrok.io";
    Database db=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.green));
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        }

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
            if (!TextUtils.isEmpty(token)) {
                Token=token;
                Log.d(TAG, "retrieve token successful : " + Token);
                if(db.show().matches("nothing")){
                    System.out.println("bad bad    ");
                }else{
                    new RequestTask2().execute(Globals.url+"/update/"+db.show()+"/"+token);
                    System.out.println("inside if");
                }

            } else{
                Log.w(TAG, "token should not be null...");
            }
        }).addOnFailureListener(e -> {
        }).addOnCanceledListener(() -> {
        }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));

        if(db.show().matches("nothing")){
            setContentView(R.layout.activity_login);
            get_Name=findViewById(R.id.username);
            btn_login=findViewById(R.id.submit);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text=get_Name.getText().toString();

                    if(text.matches("")){
                        Toast.makeText(Login.this,"username can not be empty",Toast.LENGTH_SHORT).show();
                    }else{
                        log_name=text;

                        System.out.println("inserting   data     "+text);
                        db.insert(text);
//                        System.out.println("tok tok token     "+Token);
                        new RequestTask2().execute(Globals.url+"/register/"+text+"/"+Token);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("login_name", log_name);  // pass your values and retrieve them in the other Activity using keyName
                        startActivity(intent);

                    }
                }
            });

        }else{
            startActivity(new Intent(Login.this,MainActivity.class));

        }

    }

}