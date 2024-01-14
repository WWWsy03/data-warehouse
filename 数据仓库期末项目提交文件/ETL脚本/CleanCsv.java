import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CleanCsv {
    public static void main(String[] args) {
        String inputPath = "D:\\university\\数据仓库\\新建文件夹\\新建文件夹\\formatTableonlymovie.csv";
        String outputPath = "D:\\university\\数据仓库\\新建文件夹\\新建文件夹\\formatTableonlymovie_clean.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputPath));
             PrintWriter writer = new PrintWriter(outputPath)) {

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    // 尝试解析每一行
                    List<String> entries = List.of(nextLine);
                    // 如果解析成功，写入到新的CSV文件
                    writer.println(String.join(",", entries));
                } catch (Exception e) {
                    // 如果解析失败，跳过该行
                    System.out.println(nextLine);
                    continue;
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
