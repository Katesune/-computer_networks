package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, TransformerException {

        Data input_text = new Data();
        input_text.filename = "MyText.json";
        String data = input_text.getText();

        Translator translator = new Translator();
        translator.data = data;
        translator.getToken();
        translator.getDictors();

        input_text.token = translator.token;
        //System.out.println(translator.token);
    }
}
