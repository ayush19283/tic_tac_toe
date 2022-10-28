package com.ayush.tic_tac_toe;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

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

public class PostRequest3  extends AsyncTask<String, String, String> {

    String token,details;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... arg) {
        URL url;
//            String filename = arg[1];
        int count = 0;
        int counter = 0;
        String s;

//            token=arg[1];
        token=arg[0];
        details=arg[1];
        try {
            url = new URL("https://fcm.googleapis.com/fcm/send");
            System.out.println("token in post request  "+arg[0]+" extra "+arg[1]);


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
            byte[] outputBytes = ("{\"to\":\""+token+"\"," +
                    "\"data\":{\"text\":\""+details+"\",\"title\":\"custom username\"}," +
                    "\"notification\":{\"title\":\"opponent has marked\",\"body\":\""+details+"\",\"sound\":\"Tri-tone\"},"+
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