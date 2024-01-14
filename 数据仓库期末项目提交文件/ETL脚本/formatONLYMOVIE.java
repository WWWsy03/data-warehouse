package org.example;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class formatONLYMOVIE {
    public static void main(String[] args) throws IOException {
        String inputFile1 = "D:\\kettle\\result.csv";  // 第一个CSV文件路径
        String inputFile2 = "D:\\university\\数据仓库\\formatTableonlymovie.csv"; // 第二个CSV文件路径
        String outputFile = "D:\\university\\数据仓库\\commentTableonlymovie.csv"; // 输出文件路径

        Set<String> ProductId = loadProductIds(inputFile2);
        int lineNumber=1;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if(lineNumber==1){
                    writer.write(line);
                    writer.newLine();
                    lineNumber++;
                    continue;
                }
                String[] fields = line.split(",");
                String ID=fields[0].substring(1,fields[0].length()-1);
                if (fields.length > 1 && ProductId.contains(ID)){
                    writer.write(line);
                    writer.newLine();
                }
                lineNumber++;
            }
        }
    }

    private static Set<String> loadProductIds(String filePath) throws IOException {
        Set<String> productIds = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    productIds.add(fields[0].trim());
                }
            }
        }

        return productIds;
    }
}
