package com.company;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Translator {
    String settings = "?api-version=3.0&from=ru&to=de&to=it&to=pl";
    String token_URL = "https://eastasia.api.cognitive.microsoft.com/sts/v1.0/issueToken";
    String dictors_URL = "https://eastasia.tts.speech.microsoft.com/cognitiveservices/voices/list";
    String text_to_speech_URL = "https://eastasia.voice.speech.microsoft.com/cognitiveservices/v1?deploymentId={deploymentId}";
    List<Dictor> dictors;
    String data;

    String token;

    public Translator()  throws IOException {
    }

    public void getToken() throws IOException{
        URL url = new URL(token_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("Content-Length", "0");
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Key", "1db01788ad86488d90d573a7fe502c11");

        urlConnection.setDoOutput(true);
        OutputStream out = urlConnection.getOutputStream();

        Scanner in = new Scanner(urlConnection.getInputStream());
        token = in.nextLine();

        urlConnection.disconnect();
        System.out.println("Token received");
    }

    public void getDictors() throws IOException{

        URL url = new URL(dictors_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String full_token = "Bearer " + token;
        urlConnection.setRequestProperty("Authorization", full_token);

        Scanner in = new Scanner(urlConnection.getInputStream());

        InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream());

        Type type = new TypeToken<List<Dictor>>() {}.getType();
        dictors= new Gson().fromJson(reader, type);

        for (Dictor d: dictors) {
            System.out.println(d.Name);
        }

        urlConnection.disconnect();
        System.out.println("Dictors received");
    }

    public void TextToSpeech() throws IOException {
        URL url = new URL(text_to_speech_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String full_token = "Bearer " + token;
        urlConnection.setRequestProperty("Authorization", full_token);
        urlConnection.setRequestProperty("Content-Type", "application/ssml+xml");
        urlConnection.setRequestProperty("X-Microsoft-OutputFormat", "audio-16khz-64kbitrate-mono-mp3");
    }

    class Dictor {
        String Name;
        String DisplayName;
        String VoiceType;
    }
}
