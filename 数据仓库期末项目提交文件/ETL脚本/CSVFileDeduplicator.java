package org.example;

import java.io.*;
import java.util.*;

public class CSVFileDeduplicator {
    public static void main(String[] args) {
        String csvFile = "merged.csv";
        String outputCSVFile = "mergedwithoutduplicat.csv";
        int columnToDeduplicate = 1; // Change this to the column you want to deduplicate based on. Remember, columns are 0-indexed.

        deduplicateFile(csvFile, outputCSVFile, columnToDeduplicate);
    }

    private static void deduplicateFile(String filename, String outputFilename, int columnToDeduplicate) {
        File inputFile = new File(filename);
        File outputFile = new File(outputFilename);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            Set<String> seenValues = new HashSet<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (seenValues.add(columns[columnToDeduplicate])) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
