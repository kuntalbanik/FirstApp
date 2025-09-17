package com.example.firstapp;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFileManager {

    public static void createAndSaveJsonFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists()) {
            try {
                JSONObject jsonObject = new JSONObject();
                // Add initial data if needed
                jsonObject.put("setting1", "value1");
                jsonObject.put("setting2", 123);

                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(jsonObject.toString().getBytes());
                fos.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }



    public static JSONObject readJsonFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            try {
                FileInputStream fis = context.openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                fis.close();
                return new JSONObject(sb.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
