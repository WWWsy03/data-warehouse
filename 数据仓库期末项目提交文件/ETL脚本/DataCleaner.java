import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DataCleaner {

    public static void main(String[] args) {
        String actorsFilePath = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Actors.csv"; // 演员文件路径
        String moviesFilePath = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\Movies.csv"; // 电影文件路径
        String movieActorFilePath = "D:\\university\\数据仓库\\新建文件夹\\清洗\\Movie_Actor.csv"; // 电影-演员文件路径
        String outputFilePath = "D:\\university\\数据仓库\\新建文件夹\\清洗2.0\\Movie_Actor.csv"; // 输出文件路径

        Set<String> actors = new HashSet<>();
        Set<String> movies = new HashSet<>();

        // 读取演员和电影数据
        loadItems(actorsFilePath, actors);
        loadItems(moviesFilePath, movies);

        // 清洗 Movie_Actor 数据
        cleanData(movieActorFilePath, outputFilePath, actors, movies);
    }

    private static void loadItems(String filePath, Set<String> items) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanData(String inputFilePath, String outputFilePath, Set<String> actors, Set<String> movies) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             FileWriter fw = new FileWriter(outputFilePath)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // 假设 CSV 使用逗号分隔
                if (parts.length == 2 && actors.contains(parts[0].trim()) && movies.contains(parts[1].trim())) {
                    fw.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
