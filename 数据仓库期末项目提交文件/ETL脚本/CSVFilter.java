//package org.example;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CSVFilter {
//    public static void main(String[] args) throws Exception {
//        String firstFile = "D:\\university\\数据仓库\\movie_comment_joined.csv"; // 替换为第一个CSV文件的路径
//        String secondFile = "D:\\kettle\\2151894王姝谕\\formatTable.csv"; // 替换为第二个CSV文件的路径
//        String outputFile = "D:\\university\\数据仓库\\pure_movie_comment_joined.csv"; // 输出文件的路径
//
//        try {
//            // 使用HashSet存储第二个文件的ASIN值
//            HashSet<String> asins = new HashSet<>();
//            try (BufferedReader br = Files.newBufferedReader(Paths.get(secondFile))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] parts = line.split(",");
//                    if (parts.length > 0) {
//                        asins.add(parts[1]); // 假设ASIN是每行的第一个字段
//                    }
//                }
//            }
//
//            // 处理第一个文件，并写入新文件
//            try (BufferedReader br = Files.newBufferedReader(Paths.get(firstFile));
//                 BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] parts = line.split(",");
//                    if (asins.contains(parts[0]) || line.startsWith("product_id")) { // 保留标题行和匹配的行
//                        bw.write(line);
//                        bw.newLine();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
