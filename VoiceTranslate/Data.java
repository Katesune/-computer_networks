package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {

    String filename;
    String data;
    ArrayList<String> data_array;
    String token;

    public String getText() {

        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                data += line;
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getDataArray() {

        for (String str: data.split(" ")) {
            data_array.add(str);
        }

        return data_array;
    }

    public boolean dataToFile(Scanner in, String filename) {

        try(FileOutputStream file=new FileOutputStream(filename))
        {
            if (in.hasNext()) {
                byte[] buffer = in.nextLine().getBytes();
                file.write(buffer, 0, buffer.length);
                return true;
            } else System.out.println("No output returned");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to write to file");
            return false;
        }
        return true;
    }
}
