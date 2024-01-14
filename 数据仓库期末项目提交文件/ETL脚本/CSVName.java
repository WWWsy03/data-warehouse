package org.example;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVName {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D:\\kettle\\InfoTable.csv");
        Writer out = new FileWriter("infoWithcorrectName.csv");

        CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Name", "ReleaseTime", "Time", "Director", "Writer", "Actor", "Type"));

        Map<String, String> nameMap = new HashMap<>();

        for (CSVRecord record : parser) {
            List<String> newRecord = new ArrayList<>();
            for (String header : parser.getHeaderMap().keySet()) {
                String value = record.get(header);
                if (header.equals("Director") || header.equals("Actor")) {
                    for (Map.Entry<String, String> nameEntry : nameMap.entrySet()) {
                        if (value.contains(nameEntry.getKey())) {
                            value = nameEntry.getValue();
                            break;
                        }
                    }
                    if (!nameMap.containsKey(value)) {
                        nameMap.put(value, value);
                    }
                }
                newRecord.add(value);
            }
            printer.printRecord(newRecord);
        }

        out.close();
        in.close();
    }
}

