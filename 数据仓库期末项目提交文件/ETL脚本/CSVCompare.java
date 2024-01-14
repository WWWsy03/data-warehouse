package org.example;

import java.io.*;
import java.util.*;

public class CSVCompare {
    public static void main(String[] args) {
        String csvFile1 = "D:\\teamSpider\\AmazonMovieIntegration-main\\amazon_movies\\wsy\\wsyTest.csv";
        String csvFile2 = "D:\\teamSpider\\AmazonMovieIntegration-main\\result\\567remain.csv";
        String line = "";
        String cvsSplitBy = ",";
        Set<String> set1 = new HashSet<>();
        Map<String, String> map2 = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) {
            while ((line = br.readLine()) != null) {
                String[] id = line.split(cvsSplitBy);
                set1.add(id[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
            while ((line = br.readLine()) != null) {
                String[] id = line.split(cvsSplitBy);
                map2.put(id[0], line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        set1.removeAll(map2.keySet());

        try (PrintWriter pw = new PrintWriter(new File("567.csv"))) {
            for (String id : set1) {
                pw.println(id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
