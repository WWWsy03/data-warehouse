import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UniqueActorsCSV {
    public static void main(String[] args) {
        String inputFilePath = "D:\\university\\数据仓库\\新建文件夹\\清洗2.0\\Directors.csv"; // 输入文件路径
        String outputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Directors.csv"; // 输出文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            Set<String> uniqueKeys = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",", -1); // 分割每一行

                // 检查第一列的值是否已在集合中
                if (!uniqueKeys.contains(columns[0])) {
                    uniqueKeys.add(columns[0]); // 添加新的键到集合
                    writer.write(line); // 写入文件
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
