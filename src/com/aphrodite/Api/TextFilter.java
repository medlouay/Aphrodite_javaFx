/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aphrodite.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Nasr
 */
public class TextFilter {
  
       
    private final String weatherAPI = "https://api.apilayer.com/bad_words?censor_character=censor_character";

    
    public String GetTwi(String context) throws MalformedURLException, UnsupportedEncodingException, ParseException {
            try{
                URL url = new URL (weatherAPI);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                
                con.setRequestProperty("apikey", "VQZ62qtrLk5jSBOvy6u2wx4eU0ZL4412");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                                JSONParser parser = new JSONParser();

                con.setDoOutput(true);
                String jsonInputString = "{"+"text/plain"+": "+context+"}";
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                                    JSONObject resa =   (JSONObject) parser.parse(String.valueOf(response));

                System.out.println(" toString res suuuuuuuuuui  " + resa);
    
                        String me = (String) resa.get("censored_content");
        String find = "plain:";
        String psst = null;
        int i = me.indexOf(find);

        if (i > 0) {
            System.out.println(me.substring(i+6, me.length()));

            psst = me.substring(i+6, me.length() - 1);
        }
         return psst;
                }
    
            }   catch (IOException ex) {
                System.out.println(ex.getMessage());        }
            return null ;
    }
}
