package net.lampetty.samples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXSample extends DefaultHandler {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(true);
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(args[0])));
            SAXParser saxParser = saxParserFactory.newSAXParser();
            InputSource inputSource = new InputSource(input);
            // XMLファイルをリード
            saxParser.parse(inputSource, new SAXSample());
          } catch (Exception e) {
            System.err.println(e);
          }
    }

    @Override
    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        System.out.println("characters: " + new String(arg0, arg1, arg2));
    }

    @Override
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {
        System.out.println("endElement: " + arg2);
    }

    @Override
    public void startElement(String namespaceUri, String localName, String qName,
            Attributes attrs) throws SAXException {
        System.out.println("startElement: " + qName);
    }


}
