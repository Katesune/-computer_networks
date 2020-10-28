package com.company;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class blackBase {
    ArrayList <String> url;
    ArrayList <String> available;
    ArrayList<domainInfo> info;

    blackBase(){
    }

    public void Write() {
        int count =0;
        while (count < 10) {
            System.out.println(getDomain(this.url.get(count)));
            count ++;
        }
    }

    public static blackBase findDomain(blackBase base) {
        base.available = new ArrayList<String>();
        base.available.add(" ");
        for (String u:base.url) {
            if (base.available.size()<20) {
                String domainu = getDomain(u);
                domainInfo inf = getDomainInfo(domainu);
                if (inf.Yes()) {
                    base.available.add(domainu);
                }
            }
        }
        return base;
    }

    public void sendFile() {

        String probel = "\n";
        byte [] pr = probel.getBytes();

        try(FileOutputStream file=new FileOutputStream("available.txt"))
        {
            for (String d:this.available) {
                // перевод строки в байты
                byte[] buffer = d.getBytes();
                file.write(buffer, 0, buffer.length);
                file.write(pr, 0, pr.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDomain(String url) {
        String pattern = "\\*\\.(\\w+\\.\\w+)";
        String d = url.replaceAll(pattern, "$1");
        return d;
    }

    public static domainInfo getDomainInfo (String URL) {
        domainInfo inf = new domainInfo();

        Gson gson = new Gson();
        String API_URL = "http://api.whois.vu/?q="+URL;;

        try {
            URL url = new URL(API_URL);
            InputStream stream = (InputStream) url.getContent();
            // передаём сетевой поток специальному считывателю
            InputStreamReader reader = new InputStreamReader(stream);
            inf = gson.fromJson(reader, domainInfo.class); // создать нужные классы
        } catch (IOException e) {
            System.out.println(e.getMessage()); // в случае исключения выдаём ошибку на экран
        }

        return inf;
    }

    public void writeAvailable() {
        for (String a:this.available) {
            System.out.println(a);
        }
    }

}

class domainInfo {
    String domain;
    String available;
    String type;
    String registrar;

    domainInfo(){}

    public Boolean Yes() {
        try {
        if (this.available.equals("yes"))  return true;
        else return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
