package org.example;

import info.debatty.java.stringsimilarity.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MovieSimilarity {
    public static void main(String[] args) throws IOException {
        // 读取CSV文件，并将数据存储在一个列表中
        Reader in = new FileReader("D:\\kettle\\timeAndmovie.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        List<CSVRecord> webs = new ArrayList<>();
        for (CSVRecord record : records) {
            webs.add(record);
        }

        // 创建一个邻接表，用于存储产品之间的相似度
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < webs.size(); i++) {
            map.add(new ArrayList<>());
        }

        // 计算产品之间的相似度，并更新邻接表
        JaroWinkler jw = new JaroWinkler();
        for (int i = 0; i < webs.size() - 1; i++) {
            for (int j = i + 1; j < webs.size(); j++) {
                double scoreName = jw.similarity(webs.get(i).get(0), webs.get(j).get(0));
                if (scoreName < 0.96) {
                    break;
                }
                double similar = calculate(webs.get(i), webs.get(j), scoreName);
                if (similar >= 0.73) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        // 创建一个新的列表，用于存储分组后的数据
        List<List<CSVRecord>> groups = new ArrayList<>();
        boolean[] visited = new boolean[webs.size()];
        for (int i = 0; i < webs.size(); i++) {
            if (!visited[i]) {
                List<CSVRecord> group = new ArrayList<>();
                dfs(i, group, visited, map, webs);
                groups.add(group);
            }
        }

        // 将分组后的数据写入一个新的CSV文件
        try (Writer out = new FileWriter("groupedmovie.csv")) {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            int groupId = 0;
            for (List<CSVRecord> group : groups) {
                for (CSVRecord record : group) {
                    List<String> newRecord = new ArrayList<>();
                    newRecord.add(String.valueOf(groupId));
                    for (String value : record) {
                        newRecord.add(value);
                    }
                    printer.printRecord(newRecord);
                }
                groupId++;
            }
        }
    }

    private static void dfs(int i, List<CSVRecord> group, boolean[] visited, List<List<Integer>> map, List<CSVRecord> webs) {
        visited[i] = true;
        group.add(webs.get(i));
        for (int j : map.get(i)) {
            if (!visited[j]) {
                dfs(j, group, visited, map, webs);
            }
        }
    }

    private static double calculate(CSVRecord record1, CSVRecord record2, double scoreName) {
        // 计算导演的相似度
        double scoreDirector = calculateSimilarity(record1.get(4), record2.get(4));

        // 计算演员的相似度
        double scoreActor = calculateSimilarity(record1.get(6), record2.get(6));

        // 计算总相似度
        double score = scoreName * scoreDirector * scoreActor;

        return score;
    }

    private static double calculateSimilarity(String str1, String str2) {
        Set<String> set1 = Arrays.stream(str1.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(str2.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        if (set1.isEmpty() || set2.isEmpty() || set1.containsAll(set2) || set2.containsAll(set1)) {
            return 1.0;
        } else {
            return 2.0 * intersection.size() / (set1.size() + set2.size());
        }
    }

}
