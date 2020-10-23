package com.company;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static blackBase getBase(String filename) {
        blackBase myBase = new blackBase();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                myBase = filer(line, myBase);
            }
            sc.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return myBase;
    }

    public static blackBase filer(String line, blackBase base) {
        for (String s : line.split(";+|;;+|,+")) {
            try {
                if (!s.isEmpty()) {
                    s.replaceAll("\\s+","");
                    base.url.add(s);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return base;
    }

    public static String getDomain(String url) {
        String pattern = "\\w+://*(\\w+\\.\\w+)\\/*.*";
        String d = url.replaceAll(pattern, "$1");
        return d;
    }

    public static String getIP(String url) {
        String pattern = ".*\\/([\\d.]+)";
        String d = url.replaceAll(pattern, "$1");
        return d;
    }

    public static void exception1(String newURL, blackBase myBase) {
        if (myBase.findURL(newURL)) {
            System.out.println("Your URL is in the Registry of Banned Sites");
        } else System.out.println("Your URL is resolved ");
    }

    public static void exception2(String newURL, blackBase myBase) {
        if (myBase.findDomain(newURL)) {
            System.out.println("Your domain is in the Registry of Banned Domains");
        } else System.out.println("Your domain is resolved ");
    }

    public static void exception3(String IP, blackBase myBase) {
        if (myBase.findIP(IP)) {
            System.out.println("Your IP is in the Registry of Banned IPs");
        } else System.out.println("Your IP is resolved ");
    }

    public static void main(String[] args) throws UnknownHostException {

        blackBase myBase = getBase("register.txt");

        System.out.println("Write the URL");
        Scanner in = new Scanner(System.in);

        String newURL = in.nextLine();
        newURL.replaceAll("\\s+","");

        exception1(newURL,myBase);
        exception2(newURL,myBase);

        String domain = getDomain(newURL);
        String IP = getIP(InetAddress.getByName(domain).toString());
        exception3(IP,myBase);
    }
}
