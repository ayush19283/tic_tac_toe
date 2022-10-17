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

public class RequestTask2  extends AsyncTask<String, String, String> {
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


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        try {
            System.out.println(req);
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
            System.out.println("          fadsfaf res"+result.toString());
            Globals.result=result.toString();
//            Globals.token=result.toString();
//            System.out.println(Globals.url+Globals.params);

        } catch (Exception ignored) {
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println(result);

    }

}
