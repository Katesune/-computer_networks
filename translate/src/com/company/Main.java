package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InputText {
    public String Text;
}

public class Main {

    public static String getText(String filename) {
        //считать города из файла
        String res = new String();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                res += line;
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {  // вместо перехвата исключений используем throws
        String settings = "?api-version=3.0&from=ru&to=de&to=it&to=pl";
        String URL_service = "https://api.cognitive.microsofttranslator.com/translate";

        String API_URL = URL_service+settings;

        // сформируйте данные в формате JSON
        // текст для перевода взять из файла
        String POSTData = getText("Mytext.json");

        URL url = new URL(API_URL); // нужно добавить поля в запрос (см. документацию)
        // creating connection
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Key", "82e622ccc27b4ad0af0918182329a742");
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Region", "westeurope");
        urlConnection.setRequestProperty("Content-type", "application/json");

        urlConnection.setDoOutput(true);
        OutputStream out = urlConnection.getOutputStream();
        out.write(POSTData.getBytes());

        Scanner in = new Scanner(urlConnection.getInputStream());

        try(FileOutputStream file=new FileOutputStream("result.json"))
        {
            if (in.hasNext()) {
                byte[] buffer = in.nextLine().getBytes();
                file.write(buffer, 0, buffer.length);
            } else System.out.println("No output returned");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        urlConnection.disconnect();
        System.out.println("Done");
    }
}