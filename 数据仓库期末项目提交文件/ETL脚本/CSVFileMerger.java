package org.example;

import java.io.*;

public class CSVFileMerger {
    public static void main(String[] args) {
        String csvFile1 = "Web_part1.csv";
        String csvFile2 = "Web_part2.csv";
        String csvFile3 = "Web_part3.csv";
        String csvFile4 = "Web_part4.csv";
        String outputCSVFile = "merged.csv";

        mergeFiles(new String[]{csvFile1, csvFile2, csvFile3, csvFile4}, outputCSVFile);
    }

    private static void mergeFiles(String[] filenames, String outputFilename) {
        File outputFile = new File(outputFilename);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (String filename : filenames) {
                File csvFile = new File(filename);
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

                reader.close();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
