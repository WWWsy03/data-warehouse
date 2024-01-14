package org.example;

import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvIntersection {

    public static void main(String[] args) throws IOException {
        String firstFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\releasetimeOK.csv";
        String secondFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Review.csv";
        String outputFirstFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\releasetimeOK.csv";
        String outputSecondFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Reviews.csv";

        // 读取第一个文件，存储ASIN值
        Set<String> asinSet = new HashSet<>();
        try (Reader in = new FileReader(firstFile)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                asinSet.add(record.get("product_id"));
            }
        }

        // 读取第二个文件并过滤，写入新的文件
        try (Reader in = new FileReader(secondFile);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputSecondFile))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "review_id:ID", "product_id", "review_helpfulness", "review_score", "review_summary", "review_text", "review_time", "user_id", ":LABEL"));
            for (CSVRecord record : records) {
                if (asinSet.contains(record.get("product_id"))) {
                    csvPrinter.printRecord(
                            record.get("review_id:ID"), record.get("product_id"), record.get("review_helpfulness"), record.get("review_score"),
                            record.get("review_summary"), record.get("review_text"), record.get("review_time"), record.get("user_id"), record.get(":LABEL"));
                }
            }
        }

        // 重新读取第一个文件，并过滤
        Set<String> updatedProductIds = new HashSet<>(asinSet); // 复制原始ASIN集合
        try (Reader in = new FileReader(outputSecondFile);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFirstFile))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "movie_id", "movie_name", "product_id", "release_time", "time", "Director", "Actor", "Type", "Comments", "Format", "Cost", "Grade"));
            for (CSVRecord record : records) {
                updatedProductIds.remove(record.get("product_id"));
            }
        }

        // 再次读取第一个文件并写入新的文件
        try (Reader in = new FileReader(firstFile);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFirstFile))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "movie_id", "movie_name", "product_id", "release_time", "time",
                    "Director", "Actor", "Type", "Comments", "Format", "Cost", "Grade"
            )
            );
            for (CSVRecord record : records) {
                if (!updatedProductIds.contains(record.get("product_id"))) {
                    csvPrinter.printRecord(
                            record.get("movie_id"), record.get("movie_name"), record.get("product_id"), record.get("release_time"),
                            record.get("time"), record.get("Director"), record.get("Actor"), record.get("Type"),
                            record.get("Comments"), record.get("Format"), record.get("Cost"), record.get("Grade"));
                }
            }
        }
    }
}
