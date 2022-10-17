package com.ayush.tic_tac_toe;

import android.app.Activity;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class Globals {
    public static String username;
    public static String token;
    public static String priority="";
    public static int searching=0;
    public static int chance=0;
    public static Set<String> wset1 = new HashSet<String>();
    public static Set<String> wset2 = new HashSet<String>();
    public static Set<String> wset3 = new HashSet<String>();
    public static Set<String> wset4 = new HashSet<String>();
    public static Set<String> wset5 = new HashSet<String>();
    public static Set<String> wset6 = new HashSet<String>();
    public static Set<String> wset7 = new HashSet<String>();
    public static Set<String> wset8 = new HashSet<String>();
    public static Set<String> opponent=new HashSet<String>();
    public static Button b00;
    public static Button b01;
    public static Button b02;
    public static Button b10;
    public static Button b11;
    public static Button b12;
    public static Button b20;
    public static Button b21;
    public static Button b22;
    public static Activity demo;
    public static String message;
    public static String result="";
    public static String url="https://d9ff-2409-4053-58d-3e1-cdcb-bf0f-84e3-1b35.in.ngrok.io";
}
