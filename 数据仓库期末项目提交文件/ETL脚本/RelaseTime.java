package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RelaseTime {

    private static final Map<String, String> MONTH_MAP = new HashMap<>();
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{1,2})-([a-zA-Z]{3})-(\\d{2})");

    static {
        MONTH_MAP.put("Jan", "01");
        MONTH_MAP.put("Feb", "02");
        MONTH_MAP.put("Mar", "03");
        MONTH_MAP.put("Apr", "04");
        MONTH_MAP.put("May", "05");
        MONTH_MAP.put("Jun", "06");
        MONTH_MAP.put("Jul", "07");
        MONTH_MAP.put("Aug", "08");
        MONTH_MAP.put("Sep", "09");
        MONTH_MAP.put("Oct", "10");
        MONTH_MAP.put("Nov", "11");
        MONTH_MAP.put("Dec", "12");
    }

    public static void main(String[] args) throws IOException {
        String inputFilePath = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\同一部电影同一个编号+只剩电影.csv"; // 输入文件的路径
        String outputFilePath = "D:\\university\\数据仓库\\新建文件夹\\数据预处理2\\releasetime50.csv"; // 输出文件的路径

        // 创建用于读取输入文件的BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             // 创建用于写入输出文件的BufferedWriter
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            // 逐行读取输入文件
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 检查每行是否有足够的字段，然后处理ReleaseTime字段
                if (fields.length > 3) {
                    fields[3] = formatReleaseTime(fields[3]); // 调用formatReleaseTime方法进行日期格式化
                    writer.write(String.join(",", fields)); // 将处理后的行写入输出文件
                    writer.newLine();
                }
            }
        }
    }

    private static String formatReleaseTime(String releaseTime) {
        if (releaseTime.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            return releaseTime;
        }

        Matcher matcher = DATE_PATTERN.matcher(releaseTime);
        if (matcher.matches()) {
            String day = matcher.group(1);
            String month = MONTH_MAP.get(matcher.group(2));
            String year = determineYear(matcher.group(3));

            if (month != null && year != null) {
                return year + "/" + month + "/" + String.valueOf(Integer.parseInt(day));
            }
        }

        // 如果格式不匹配，则返回原始字符串或进行其他处理
        return "error";
    }

    private static String determineYear(String twoDigitYear) {
        int year = Integer.parseInt(twoDigitYear);
        // 确定是20世纪还是21世纪
        if (year >= 0 && year <= 22) {  // 假设22是当前年份的最后两位
            return "20" + twoDigitYear;
        } else if (year >= 23 && year <= 99) {
            return "19" + twoDigitYear;
        } else {
            return null;
        }
    }

}
