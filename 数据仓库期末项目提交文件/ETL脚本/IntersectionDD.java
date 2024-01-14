package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IntersectionDD {

    public static void main(String[] args) throws IOException {
        String movieFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\releasetimeOK.csv";
        String reviewFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Review.csv";
        String outputReviewFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Reviews.csv";
        String outputMovieFile = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\UpdatedMovies.csv";

        // 读取电影文件，存储 product_id
        Set<String> productIdSet = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(movieFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                productIdSet.add(values[2]); // Assuming product_id is the third column
            }
        }

        // 处理评论文件
        try (BufferedReader br = new BufferedReader(new FileReader(reviewFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputReviewFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    writer.write(line + "\n");
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                if (productIdSet.contains(values[1])) { // Assuming product_id is the second column
                    writer.write(line + "\n");
                }
            }
        }

        // 更新电影文件的 movie_id
        try (BufferedReader br = new BufferedReader(new FileReader(movieFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputMovieFile))) {
            String line;
            int movieId = 1;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    writer.write(line + "\n"); // Write header as is
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                values[0] = String.valueOf(movieId++); // Update movie_id
                String joinedLine = String.join(",", values);
                writer.write(joinedLine + "\n");
            }
        }
    }
}
