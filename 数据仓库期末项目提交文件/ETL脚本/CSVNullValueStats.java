package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class CSVNullValueStats {
    public static void main(String[] args) {
        String csvFile = "D:\\university\\数据仓库\\data\\movie.csv";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int totalRows = 0;
            int[] nullCounts = new int[5];
            while ((line = reader.readNext()) != null) {
                totalRows++;
                for (int i = 0; i < line.length; i++) {
                    if (line[i] == null || line[i].trim().isEmpty()) {
                        nullCounts[i]++;
                    }
                }
            }
            System.out.println("总行数: " + totalRows);
            String[] columns = {"movie_id", "movie_name", "release_time_id", "time", "Type"};
            for (int i = 0; i < columns.length; i++) {
                System.out.println(columns[i] + "列的空值数量: " + nullCounts[i]);
                System.out.println(columns[i] + "列的空值比例: " + (double) nullCounts[i] / totalRows * 100 + "%");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

