package com.ayush.tic_tac_toe;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class FirebaseMessagingReceiver extends FirebaseMessagingService {
    String text,sender;
    TextView Time;
    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "New token: " + token);
        System.out.println(token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        text= data.get("text"); //data received from fcm body i.e message
        System.out.println(text);
        if(Globals.searching==1)
        {
            String[] parts = text.split(" "); //returns an array with the 2 parts
            String firstPart = parts[0];
            System.out.println(parts[0]+"   the splitted text  "+parts[1]+"     "+parts[2]);
            Globals.username=parts[0];
            Globals.token=parts[1];
            Globals.priority=parts[2];
            Globals.searching=0;
        }else{
            Globals.chance++;
            Globals.opponent.add(text);
            Globals.demo.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(Globals.priority.matches("1"))
                    {
                        if(text.matches("zero_zero"))
                        {
                            Globals.b00.setText("0");
                        }
                        else if(text.matches("zero_one"))
                        {
                            Globals.b01.setText("0");
                        }
                        else if(text.matches("zero_two"))
                        {
                            Globals.b02.setText("0");
                        }
                        else if(text.matches("one_zero"))
                        {
                            Globals.b10.setText("0");
                        }
                        else if(text.matches("one_one"))
                        {
                            Globals.b11.setText("0");
                        }
                        else if(text.matches("one_two"))
                        {
                            Globals.b12.setText("0");
                        }
                        else if(text.matches("two_zero"))
                        {
                            Globals.b20.setText("0");
                        }
                        else if(text.matches("two_one"))
                        {
                            Globals.b21.setText("0");
                        }
                        else if(text.matches("two_two"))
                        {
                            Globals.b22.setText("0");
                        }
                    }else{
                        if(text.matches("zero_zero"))
                        {
                            Globals.b00.setText("X");
                        }
                        else if(text.matches("zero_one"))
                        {
                            Globals.b01.setText("X");
                        }
                        else if(text.matches("zero_two"))
                        {
                            Globals.b02.setText("X");
                        }
                        else if(text.matches("one_zero"))
                        {
                            Globals.b10.setText("X");
                        }
                        else if(text.matches("one_one"))
                        {
                            Globals.b11.setText("X");
                        }
                        else if(text.matches("one_two"))
                        {
                            Globals.b12.setText("X");
                        }
                        else if(text.matches("two_zero"))
                        {
                            Globals.b20.setText("X");
                        }
                        else if(text.matches("two_one"))
                        {
                            Globals.b21.setText("X");
                        }
                        else if(text.matches("two_two"))
                        {
                            Globals.b22.setText("X");
                        }

                    }
                }
            });


            boolean result1 = Globals.opponent.containsAll(Globals.wset1);
            boolean result2 = Globals.opponent.containsAll(Globals.wset2);
            boolean result3 = Globals.opponent.containsAll(Globals.wset3);
            boolean result4 = Globals.opponent.containsAll(Globals.wset4);
            boolean result5 = Globals.opponent.containsAll(Globals.wset5);
            boolean result6 = Globals.opponent.containsAll(Globals.wset6);
            boolean result7 = Globals.opponent.containsAll(Globals.wset7);
            boolean result8 = Globals.opponent.containsAll(Globals.wset8);

            if (result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {
                System.out.println("opponent is winner");
            }
        }

        }
    }
