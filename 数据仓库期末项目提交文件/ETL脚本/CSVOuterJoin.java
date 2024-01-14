package org.example;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class CSVOuterJoin {
    public static void main(String[] args) throws IOException {
        // 读取第二个CSV文件，并将ASIN存储在一个HashSet中
        Reader in2 = new FileReader("C:\\Users\\WWWsy\\Downloads\\MovieProducts.csv");
        Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.parse(in2);
        Set<String> asinSet = new HashSet<>();
        for (CSVRecord record : records2) {
            String asin = record.get(0);
            asinSet.add(asin);
        }

        // 读取第一个CSV文件
        Reader in = new FileReader("D:\\kettle\\format.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

        // 创建一个新的列表来存储匹配的记录
        List<CSVRecord> matchedRecords = new ArrayList<>();
        for (CSVRecord record : records) {
            String asin = record.get(1);
            if (asinSet.contains(asin)) {
                matchedRecords.add(record);
            }
        }

        // 将匹配的记录写入一个新的CSV文件
        try (Writer out = new FileWriter("format.csv")) {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            for (CSVRecord record : matchedRecords) {
                printer.printRecord(record);
            }
        }
    }
}
