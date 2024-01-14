//import java.io.*;
//import java.text.ParseException;
//import java.util.*;
//import java.text.SimpleDateFormat;
//import com.opencsv.*;
//import com.opencsv.exceptions.CsvValidationException;
//import com.opencsv.exceptions.CsvMalformedLineException;
//
//public class CSVDataProcessor {
//    public static void main(String[] args) {
//
//        String inputFile = "D:\\university\\数据仓库\\movie_comment_completed(1).csv";
//        String outputFile = "D:\\university\\数据仓库\\movie_comment_completed.csv";
//
//        Map<String, String> productIdToEarliestReviewTime = new HashMap<>();
//        Map<String, String> productIdToReleaseYear = new HashMap<>();
//
//        CSVReader reader = null;
//        CSVWriter writer = null;
//        try {
//            reader = new CSVReader(new FileReader(inputFile));
//            writer = new CSVWriter(new FileWriter(outputFile));
//
//            String[] line;
//            int lineNumber = 1;
//            while ((line = reader.readNext()) != null) {
//                try {
//                    if (line.length != 23) {
//                        System.out.println("Skipping line " + lineNumber + " due to missing fields");
//                        continue;
//                    }
//
//                    String productId = line[0]; // 假设product_id是第一列
//                    String reviewTime = line[5]; // 假设review_time是第22列
//
//                    // 检查review_time是否为空
//                    if (reviewTime == null || reviewTime.isEmpty()) {
//                        System.out.println("Skipping line " + lineNumber + " due to empty review_time");
//                        continue;
//                    }
//
//                    // 将Unix时间戳转换为日期
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                    String reviewDate = sdf.format(new Date(Long.parseLong(reviewTime) * 1000L));
//
//                    if (productIdToEarliestReviewTime.containsKey(productId)) {
//                        String earliestReviewTime = productIdToEarliestReviewTime.get(productId);
//                        if (reviewDate.compareTo(earliestReviewTime) < 0) {
//                            productIdToEarliestReviewTime.put(productId, reviewDate);
//                        }
//                    } else {
//                        productIdToEarliestReviewTime.put(productId, reviewDate);
//                    }
//
//                    String releaseYear = line[17]; // 假设release_year是第19列
//
//                    // 如果releaseYear不符合格式，使用最早的review_time作为release_year
//                    if (!releaseYear.matches("\\d{4}/\\d{1,2}/\\d{1,2}") && !releaseYear.matches("\\d{1,2}-[A-Za-z]{3}-\\d{2}")) {
//                        if (productIdToEarliestReviewTime.containsKey(productId)) {
//                            releaseYear = productIdToEarliestReviewTime.get(productId);
//                        } else {
//                            System.out.println("Skipping line " + lineNumber + " due to missing earliest review_time");
//                            continue;
//                        }
//                    }
//
//                    // 将releaseYear转换为统一的格式
//                    if (releaseYear.matches("\\d{1,2}-[A-Za-z]{3}-\\d{2}")) {
//                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
//                        Date date = sdf2.parse(releaseYear);
//                        releaseYear = sdf.format(date);
//                    }
//
//                    line[17] = releaseYear; // 更新release_year
//
//                    // 只选择前22个元素写入新文件
//                    String[] newLine = Arrays.copyOf(line, 22);
//                    writer.writeNext(newLine);
//                } catch (IOException | ParseException | CsvValidationException e) {
//                    System.out.println("Skipping line " + lineNumber + " due to an error: " + e.getMessage());
//                }
//
//                lineNumber++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
