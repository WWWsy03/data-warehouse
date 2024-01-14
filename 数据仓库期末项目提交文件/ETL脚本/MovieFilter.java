package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class MovieFilter {

    private static Map<String, String> dic = new HashMap<>();
    private static int turn = 0;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\kettle\\SourceData.csv"));
             BufferedWriter regularWriter = new BufferedWriter(new FileWriter("regularMovie.csv", true));
             BufferedWriter dropWriter = new BufferedWriter(new FileWriter("dropMovie.csv", true))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] frame = line.split(",");
                try {
                    if(filterAndWrite(frame, regularWriter, dropWriter, line)){
                        regularWriter.write(line); // 使用原始的line而不是拼接的frame
                        regularWriter.newLine();
                    }// 尝试调用filterAndWrite方法
                    else{
                        dropWriter.write(String.join(",", frame));
                        dropWriter.newLine();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // 忽略此异常并继续处理下一行
                    System.err.println("Error processing line: " + line); // 这一行将错误信息输出到标准错误流，但你可以选择不这样做或输出到其他地方
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean filterAndWrite(String[] frame, BufferedWriter regularWriter, BufferedWriter dropWriter, String line) throws IOException {
        // 创建一个新的ArrayList来保存需要修改的数据
        List<String> newFrame = new ArrayList<>(Arrays.asList(frame));

        boolean drop = false;

        turn++;

        String times = newFrame.get(4);
        // 使用正则表达式匹配小时和分钟部分
        Matcher matcher = Pattern.compile("(\\d+)h(\\d+)min").matcher(times);
        if (!matcher.find()) {
            // 如果未匹配到 "xhymin" 格式，则尝试匹配 "ymin" 格式
            matcher = Pattern.compile("(\\d+)min").matcher(times);
            if (!matcher.find()) {
                newFrame.set(4, "0"); // 如果还未匹配到，则设为0
            } else {
                newFrame.set(4, matcher.group(1)); // 只有分钟部分
            }
        } else {
            // 将小时部分乘以60并加上分钟部分
            int totalMinutes = Integer.parseInt(matcher.group(1)) * 60 + Integer.parseInt(matcher.group(2));
            if (totalMinutes > 170 || (totalMinutes < 40 && totalMinutes > 0)) {
                drop = true;
            }
            newFrame.set(4, String.valueOf(totalMinutes));
        }
        String[] typesToDrop = {"TV", "Pop","Jazz","World Music","Blues","CBS News Network","Opera", "Karaoke", "Rap", "Sound", "Music", "Rock", "Yoga", "Self-Help","Musicals & Performing Arts"};
        for (String type : typesToDrop) {
            if (newFrame.get(8).contains(type)) {
                drop = true;
                break;
            }
        }


        Pattern typePattern = Pattern.compile("Movies");
        Matcher typeMatcher = typePattern.matcher(newFrame.get(8));
        if (typeMatcher.find()) {
            drop = false;
        }

        if (drop) {
            return false;
        } else {
            return true;
        }
    }
}
