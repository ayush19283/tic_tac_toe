package com.ayush.tic_tac_toe;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

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

public class GetRequest  extends AsyncTask<String, String, String> {
    HttpURLConnection conn;
    String req;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... arg) {
        URL url;
//            String filename = arg[1];

        int count = 0;
        int counter = 0;
        String s;
        try {
            url = new URL(arg[0]);
            req=arg[0];
            System.out.println(req);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        try {
//            StringBuilder result = new StringBuilder();
//            url = new URL(req);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            try (BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()))) {
//                for (String line; (line = reader.readLine()) != null; ) {
//                    result.append(line);
//                }
//            }
            StringBuilder result = new StringBuilder();
            url = new URL(req);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            System.out.println(result.toString());

        } catch (Exception ignored) {
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println("get request             fdf     "+result);
        Globals.message=result;

    }

}