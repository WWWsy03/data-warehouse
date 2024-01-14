import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DataCleanerForMovieProduct {

    public static void main(String[] args) {
        String moviesFilePath = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Movies.csv"; // 电影文件路径
        String productsFilePath = "D:\\university\\数据仓库\\新建文件夹\\Directors.csv"; // 产品文件路径
        String movieProductFilePath = "D:\\university\\数据仓库\\新建文件夹\\清洗2.0\\Movie_Director.csv"; // 电影-产品文件路径
        String outputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Movie_Director.csv"; // 输出文件路径

        Set<String> movies = new HashSet<>();
        Set<String> products = new HashSet<>();

        // 读取电影和产品数据
        loadItems(moviesFilePath, movies, 0); // 假设 movie_id 在第一列
        loadItems(productsFilePath, products, 0); // 假设 product_id 在第一列

        // 清洗 Movie_Product 数据
        cleanData(movieProductFilePath, outputFilePath, movies, products);
    }

    private static void loadItems(String filePath, Set<String> items, int columnIndex) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isFirstLine = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // 跳过头行
                }
                String[] parts = line.split(",");
                if (parts.length > columnIndex) {
                    items.add(parts[columnIndex].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanData(String inputFilePath, String outputFilePath, Set<String> movies, Set<String> products) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             FileWriter fw = new FileWriter(outputFilePath)) {

            boolean isFirstLine = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    fw.write(line + "\n"); // 写入头行
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(","); // 假设 CSV 使用逗号分隔
                if (parts.length == 3 && movies.contains(parts[0].trim()) && products.contains(parts[1].trim())) {
                    fw.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}