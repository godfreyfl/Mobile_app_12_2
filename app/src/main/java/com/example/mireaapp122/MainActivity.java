package com.example.mireaapp122;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int WRITE_EXTERNAL_STORAGE_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.println(Log.ASSERT,"TEST", "PARSE ARRAY");
        parseJsonArrayUsingGson();
        Log.println(Log.ASSERT,"TEST", "CREATE JSON");


            createJsonUsingGson();
        Log.println(Log.ASSERT,"TEST", "PARSE JSON");
        parseJsonUsingGson();
    }

    public void parseJsonArrayUsingGson() {
        String jsonArrayStr = "[{\"name\":\"John\", \"age\":30,\"email\":\"john@example.com\"}," +
                "{\"name\":\"Alice\", \"age\":25,\"email\":\"alice@example.com\"}]";
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>(){}.getType();

        List<User> users = gson.fromJson(jsonArrayStr, userListType);

        for (User user : users) {
            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);
            System.out.println("Email: " + user.email);
        }
    }

    public String createJsonUsingGson() {
        User user = new User();
        user.name = "Alice";
        user.age = 25;
        user.email = "alice@example.com";

        Gson gson = new Gson();
        String json = gson.toJson(user);

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "person.json");

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
            Log.d("File saved", "File saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }

    public void parseJsonUsingGson() {
        String jsonStr = "{\"name\":\"John\", \"age\":30,\"email\":\"john@example.com\"}";
        Gson gson = new Gson();

        try {
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(folder, "person.json");
            BufferedReader br = new BufferedReader(new FileReader(file));

            User person = gson.fromJson(br, User.class);

            Log.d("Person Details", "Name: " + person.name + ", Age: " + person.age);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JSON Read Error", e.getMessage());
        }

    }



}