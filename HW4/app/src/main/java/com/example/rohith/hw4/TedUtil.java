package com.example.rohith.hw4;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Rohith on 6/19/2016.
 */

public class TedUtil {
    public static class TedSAXParser extends DefaultHandler {
        ArrayList<Ted> tedlist;
        Ted ted;
        StringBuilder stringBuilder;
        String href="";

        public TedSAXParser() {
            super();
        }


        public static ArrayList<Ted> parseTed(InputStream inputStream){

            TedSAXParser parser=new TedSAXParser();
            try {
                //Xml.parse(inputStream,parser);
                       Xml.parse(inputStream, Xml.Encoding.UTF_8,parser);
                Log.d("TAG", "parseTopApps: "+inputStream);
                //Log.d(TAG, "parseTed: "+);
                return parser.getTedlist();

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        public ArrayList<Ted> getTedlist() {
            Log.d("demo2", "getTedlist: "+tedlist.size());
            return tedlist;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            tedlist=new ArrayList<Ted>();
            stringBuilder=new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals(("item"))){
                ted=new Ted();
                //topapp.setId(attributes.getValue(localName));
                Log.d("demo", "startElement: ");

            }

            if(qName.equals("itunes:image")) {
                if (ted != null) {
                    href = attributes.getValue("href");
                    //Log.d("", "startElement: "+href);
                    ted.setHref(href);
                }
            }
            if(qName.equals("enclosure")) {
                if(ted!=null)
                    ted.setUrl(attributes.getValue("url"));
            }


        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals(("item"))) {
                tedlist.add(ted);
                Log.d("values", "--"+ted.toString());


            } else if (qName.equals("title")) {
                if(ted!=null)
               ted.setTitle(stringBuilder.toString().trim());
            } else if (qName.equals("description")) {
                if(ted!=null)
                ted.setDescription(stringBuilder.toString().trim());
            } else if (qName.equals("pubDate")) {
                if(ted!=null)
                ted.setPubdate(stringBuilder.toString().trim());
            }
            else if (qName.equals("itunes:duration")) {
                if(ted!=null)
                ted.setDuration(stringBuilder.toString().trim());
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
