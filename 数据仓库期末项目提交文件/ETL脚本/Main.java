package org.example;

import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "D:\\javacode\\ETLHomework\\output\\result.csv";
        String outputFilePath = "output.csv";

        Reader in = new FileReader(inputFilePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        Map<String, CSVRecord> uniqueRecords = new LinkedHashMap<>();
        for (CSVRecord record : records) {
            String productId = record.get("product/productId");
            if (!uniqueRecords.containsKey(productId)) {
                uniqueRecords.put(productId, record);
            }
        }

        int[] index = {1}; // 创建一个包含一个元素的数组来存储序号
        List<String[]> newRecords = uniqueRecords.values().stream()
                .map(record -> {
                    String productId =  record.get("product/productId");
                    return new String[] {productId, "0"}; // 在每个新记录前添加序号
                })
                .collect(Collectors.toList());

        Writer out = new FileWriter(outputFilePath);
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("asin", "isGot"));
        for (String[] newRecord : newRecords) {
            printer.printRecord((Object[]) newRecord);
        }
        printer.close();
    }
}
