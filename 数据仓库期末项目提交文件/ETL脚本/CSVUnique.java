package org.example;

import java.io.*;
import java.util.*;

public class CSVUnique {
    public static void main(String[] args) {
        String csvFile = "D:\\teamSpider\\AmazonMovieIntegration-main\\result\\5.csv";
        String line = "";
        String cvsSplitBy = ",";
        Map<String, String> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] id = line.split(cvsSplitBy);
                map.put(id[0], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new File("5.csv"))) {
            for (String row : map.values()) {
                pw.println(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

