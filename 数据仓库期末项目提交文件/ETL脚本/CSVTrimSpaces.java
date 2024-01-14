import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVTrimSpaces {
    public static void main(String[] args) {
        String inputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Movie_Actor.csv"; // 输入文件路径
        String outputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Movie_Actor1.csv"; // 输出文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",", -1); // 分割每一行
                columns[0] = columns[0].trim(); // 去除第一列末尾的空格
                String cleanedLine = String.join(",", columns); // 重新组合成一行
                writer.write(cleanedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
