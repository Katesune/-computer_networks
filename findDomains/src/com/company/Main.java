package com.company;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

    public static blackBase getBase() {
        Gson gson = new Gson();
        String API_URL = "https://reestr.rublacklist.net/api/v2/domains/json/";

        blackBase myBase = new blackBase();

        try {
            URL url = new URL(API_URL);

            InputStream stream = (InputStream) url.getContent();
            // передаём сетевой поток специальному считывателю
            InputStreamReader reader = new InputStreamReader(stream);
            Type type = new TypeToken<List<String>>() {}.getType();
            myBase.url = new Gson().fromJson(reader, type);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return myBase;
    }


    public static void main(String[] args) {
        blackBase myBase = getBase();

        myBase = myBase.findDomain(myBase);
        myBase.sendFile();

        System.out.println("Done");
    }
}
