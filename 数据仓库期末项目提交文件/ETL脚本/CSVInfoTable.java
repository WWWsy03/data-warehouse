package org.example;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVInfoTable {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D:\\kettle\\同一部电影同一个编号+只剩电影.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);

        Map<String, Map<String, String>> groupedRecords = new LinkedHashMap<>();
        for (CSVRecord record : records) {
            String groupOrder = record.get("groupOrder");
            if (!groupedRecords.containsKey(groupOrder)) {
                groupedRecords.put(groupOrder, new HashMap<>());
            }
            Map<String, String> firstRecord = groupedRecords.get(groupOrder);
            for (String field : new String[]{"Name", "ReleaseTime", "Time", "Director", "Writer", "Actor", "Type"}) {
                if (!firstRecord.containsKey(field) || firstRecord.get(field).isEmpty()) {
                    firstRecord.put(field, record.get(field));
                }
            }
        }

        Writer out = new FileWriter("InfoTable.csv");
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Name", "ReleaseTime", "Time", "Director", "Writer", "Actor", "Type"));
        for (Map<String, String> record : groupedRecords.values()) {
            printer.printRecord(record.get("Name"), record.get("ReleaseTime"), record.get("Time"), record.get("Director"), record.get("Writer"), record.get("Actor"), record.get("Type"));
        }
        printer.close();
    }
}
