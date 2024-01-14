package org.example;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVFormatTable {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D:\\kettle\\同一部电影同一个编号+只剩电影.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);

        Map<String, List<CSVRecord>> groupedRecords = new HashMap<>();
        for (CSVRecord record : records) {
            String groupOrder = record.get("groupOrder");
            if (!groupedRecords.containsKey(groupOrder)) {
                groupedRecords.put(groupOrder, new ArrayList<>());
            }
            groupedRecords.get(groupOrder).add(record);
        }

        Writer out = new FileWriter("formatTable.csv");
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Name", "ASIN", "Comments", "Format", "Cost","Grade"));
        for (List<CSVRecord> group : groupedRecords.values()) {
            String name = group.get(0).get("Name");
            for (CSVRecord record : group) {
                printer.printRecord(name, record.get("ASIN"), record.get("Comments"), record.get("Format"), record.get("Cost"),record.get("Grade"));
            }
        }
        printer.close();
    }
}
