package com.example.rohith.inclass09;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by This on 6/23/16.
 */

public class TopAppsUtil {
    public static class TopAppsSAXParser extends DefaultHandler{
        ArrayList<TopApps> topappslist;
        TopApps topapp;
        StringBuilder stringBuilder;
        String height="";

        public TopAppsSAXParser() {
            super();
        }


        public static ArrayList<TopApps> parseTopApps(InputStream inputStream){

            TopAppsSAXParser parser=new TopAppsSAXParser();
            try {
                Xml.parse(inputStream, Xml.Encoding.UTF_8,parser);
                Log.d("TAG", "parseTopApps: ");
                return parser.getTopappslist();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return null;
        };
        public ArrayList<TopApps> getTopappslist() {
            return topappslist;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            topappslist=new ArrayList<TopApps>();
            stringBuilder=new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals(("entry"))){
                topapp=new TopApps();
                //topapp.setId(attributes.getValue(localName));
                Log.d("demo", "startElement: ");
                topapp.setFav(0);
            }

            if(qName.equals("im:image")) {
                height=attributes.getValue("height");
                Log.d("height", "startElement:$$$$$$"+height );
                if(height.equals("53")){
                    //topapp.setSmall_photo_url(stringBuilder.toString());
                    height="53";
                    Log.d("height", "startElement:"+"####"+height );
                }
                else if(height.equals("100")){
                    //topapp.setLarge_photo_url(stringBuilder.toString());
                    //Log.d("height", "startElement:"+"@@@"+stringBuilder.toString() );
                    height="100";
                }
                else{
                    height="75";
                }
            }
            if(qName.equals("im:price")) {
                topapp.setPrice(attributes.getValue("amount"));
            }
            if(qName.equals("im:releaseDate"))
                topapp.setRelease_Date(attributes.getValue("label"));
            if(localName.equals("category"))
                topapp.setCategory(attributes.getValue("label"));

        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals(("entry"))) {
                topappslist.add(topapp);
                Log.d("values", "_________________________________________________________ "+topapp.getDevloper_name());


            } else if (qName.equals("im:name")) {
                if (topapp != null) {
                    topapp.setAppname(stringBuilder.toString().trim());
                    //Log.d("demo", "endElement: " + stringBuilder.toString());
                }
            } else if (qName.equals("im:artist")) {
                topapp.setDevloper_name(stringBuilder.toString().trim());
            } else if (qName.equals("im:image")) {
                Log.d("height", "endElement: 7777777" + height);
                if (height.equals("53")) {
                    topapp.setImage(stringBuilder.toString().trim());
                    //Log.d("test_v", "endElement: " + stringBuilder.toString());
                } else if (height.equals("100")) {
                    //topapp.setLarge_photo_url(stringBuilder.toString());
                }


            }
            stringBuilder.setLength(0);
        }
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            stringBuilder.append(ch,start,length);
        }


    }
}
