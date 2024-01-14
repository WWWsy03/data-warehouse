package org.example;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVUserFilter {

    public static void main(String[] args) throws IOException {
        String userFile = "D:\\university\\数据仓库\\新建文件夹\\图数据库导入数据预处理1.0\\Users.csv"; // 替换为您的用户文件路径
        String checkFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\usertobe.csv"; // 替换为您的检查文件路径
        String outputFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\User.csv"; // 替换为您的输出文件路径

        // 读取检查文件，存储 user_id
        Set<String> userIdSet = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(checkFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header line
                }
                String[] values = line.split(",");
                userIdSet.add(values[0]); // Assuming user_id is the first column
            }
        }

        // 处理用户文件
        try (BufferedReader br = new BufferedReader(new FileReader(userFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    writer.write(line + "\n"); // Write header
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                if (userIdSet.contains(values[0])) { // Assuming user_id:ID is the first column
                    writer.write(line + "\n");
                }
            }
        }
    }
}

