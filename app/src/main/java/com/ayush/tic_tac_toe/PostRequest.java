package com.ayush.tic_tac_toe;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class PostRequest  extends AsyncTask<String, String, String> {

    String token,marked_box;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... arg) {
        URL url;
//            String filename = arg[1];
        int count = 0;
        int counter = 0;
        String s;

        token=arg[1];
        marked_box=arg[2];
        try {
            url = new URL(arg[0]);
              System.out.println("token in post request  "+arg[1]+" extra "+arg[2]);


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        try {
            HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));

            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Authorization","key=your key\n");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

//            String notification_body;
//            if(Globals.message.length()>10){
//                notification_body=
//            }
//            Globals.message=Globals.message.replaceAll("\"", "\\\\\"");
            byte[] outputBytes = ("{\"to\":\""+token+"\"," +
                    "\"data\":{\"text\":\""+marked_box+"\",\"title\":\"gsdg\"}," +
                    "\"notification\":{\"title\":\"opponent has marked\",\"body\":\""+marked_box+"\",\"sound\":\"Tri-tone\"},"+
                    "\"priority\":\"high\"," +
                    "\"content_available\":true}").getBytes(StandardCharsets.UTF_8);

            OutputStream os = httpcon.getOutputStream();
            os.write(outputBytes);
            os.close();

            InputStream input = httpcon.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println(result);

        JSONObject jsonObj = null;

    }
}