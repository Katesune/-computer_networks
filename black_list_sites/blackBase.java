package com.company;

import java.util.ArrayList;

public class blackBase {
    ArrayList<String> url;

    blackBase(){
        url = new ArrayList<String>();
    }

    public void Write() {
        int count =0;
        while (count < 10) {
            System.out.println(this.url.get(count));
            count ++;
        }
    }
    public Boolean findURL(String URL) {

        for (String u:this.url) {
            if (u.equals(URL)) return true;
        }

        return false;
    }

    public Boolean findDomain(String URL) {

        String URLdomain = getDomain(URL);
        for (String u:this.url) {
            String domainu = getDomain(u);
            if (domainu.equals(URLdomain)) return true;
        }

        return false;
    }

    public Boolean findIP(String IP) {
        for (String u:this.url) {
            String IPu = getIP(u);
            if (IP.equals(IPu)) return true;
        }

        return false;
    }

    public static String getDomain(String url) {
        String pattern = "\\w+://*(\\w+\\.\\w+)\\/*.*";
        String d = url.replaceAll(pattern, "$1");
        return d;
    }

    public static String getIP(String url) {
        String pattern = ".*\\/([\\d.]+).*";
        String d = url.replaceAll(pattern, "$1");
        return d;
    }
}
