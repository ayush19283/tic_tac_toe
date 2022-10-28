package com.ayush.tic_tac_toe;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class FirebaseMessagingReceiver extends FirebaseMessagingService {
    String text,opponent_details,priority="";
    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "New token: " + token);
        System.out.println(token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        text= data.get("text"); //data received from fcm body i.e message
        opponent_details=data.get("title");
        System.out.println(text+"     title title      "+opponent_details);
        if(opponent_details.matches("user details"))
        {
            String[] parts = text.split(" "); //returns an array with the 2 parts
            System.out.println(parts[0]+"   the splitted text  "+parts[1]+"     "+parts[2]);
            priority=parts[2];
            send_userDetails(parts[0],parts[1],parts[2]);
        }else if(opponent_details.matches("custom game")){
            String[] parts=text.split("-");
            send_CustomGameDetails(parts[0],parts[1]);
        }else if(opponent_details.matches("custom username")){
            send_CustomGameUsername(text);
        }
        else{
            Intent intent = new Intent("MyData");
            intent.putExtra("response", text);
            broadcaster.sendBroadcast(intent);
            }
        }

    public void send_userDetails(String username,String token,String priority){
        Intent intent = new Intent("GPSLocationUpdates");
        intent.putExtra("username", username);
        intent.putExtra("token",token);
        intent.putExtra("priority", priority);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void send_CustomGameDetails(String username,String token){
        Intent intent = new Intent("CustomGame");
        intent.putExtra("username", username);
        intent.putExtra("token",token);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    public void send_CustomGameUsername(String username){
        Intent intent = new Intent("CustomUsername");
        intent.putExtra("username", username);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
