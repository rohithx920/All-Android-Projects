package com.example.rohith.firstproject;

/**
 * Created by Rohith on 5/24/2016.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Rohith on 5/24/2016.
 */
public class Movie {
    public void readFileAtPath(String filename) {
// Lets make sure the file path is not empty or null

        if (filename == null || filename.isEmpty()) {
            System.out.println("Invalid File Path");
            return;
        }

        String filePath = System.getProperty("user.dir") + "/" + filename;
        BufferedReader inputStream = null;
// We need a try catch block so we can handle any potential IO errors
        try {
            try {
                inputStream = new BufferedReader(new FileReader(filePath));
                String lineContent = null;
// Loop will iterate over each line within the file.
// It will stop when no new lines are found.
                while ((lineContent = inputStream.readLine()) != null) {
                    System.out.println("Found the line: " + lineContent);
                }
            }
// Make sure we close the buffered reader.
            finally {
                if (inputStream != null)
                    inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sep(String lineContent) {

        String[] resultingTokens = lineContent.split(",");
        for (int i = 0; i < resultingTokens.length; i++) {
            System.out.println(resultingTokens[i].trim());
        }
    }
}

