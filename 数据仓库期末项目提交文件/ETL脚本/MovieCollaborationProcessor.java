import java.io.*;
import java.util.*;

public class MovieCollaborationProcessor {

    public static void main(String[] args) throws IOException {
        // 文件路径
        String movieActorFilePath = "D:\\university\\数据仓库\\新建文件夹\\Movie_Actor.csv";
        String movieDirectorFilePath = "D:\\university\\数据仓库\\新建文件夹\\Movie_Director.csv";
        String actorActorOutputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Actor_Actor_Relationship.csv";
        String directorActorOutputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Director_Actor_Relationship.csv";
        String directorDirectorOutputFilePath = "D:\\university\\数据仓库\\新建文件夹\\Director_Director_Relationship.csv";

        // 读取数据并建立映射
        Map<String, Set<String>> movieToActors = new HashMap<>();
        Map<String, Set<String>> movieToDirectors = new HashMap<>();
        processFile(movieActorFilePath, movieToActors);
        processFile(movieDirectorFilePath, movieToDirectors);

        Set<String> actors = readNames("D:\\university\\数据仓库\\新建文件夹\\Actors.csv");
        Set<String> directors = readNames("D:\\university\\数据仓库\\新建文件夹\\Directors.csv");
        // 处理合作关系
        Map<String, Integer> actorActorCollaboration = new HashMap<>();
        Map<String, Integer> directorActorCollaboration = new HashMap<>();
        Map<String, Integer> directorDirectorCollaboration = new HashMap<>();

        processCollaborations(movieToActors, actorActorCollaboration);
        processCollaborations(movieToDirectors, directorDirectorCollaboration);
        processMixedCollaborations(movieToActors, movieToDirectors, directorActorCollaboration);

        // 写入文件
        String actorActorHeader = ":START_ID(Actor),:END_ID(Actor),counts,:TYPE";
        String directorActorHeader = ":START_ID(Director),:END_ID(Actor),counts,:TYPE";
        String directorDirectorHeader = ":START_ID(Director),:END_ID(Director),counts,:TYPE";

        writeToFile(actorActorOutputFilePath, actorActorCollaboration, actorActorHeader, actors, actors);
        writeToFile(directorActorOutputFilePath, directorActorCollaboration, directorActorHeader, directors, actors);
        writeToFile(directorDirectorOutputFilePath, directorDirectorCollaboration, directorDirectorHeader, directors, directors);
    }

    private static Set<String> readNames(String filePath) throws IOException {
        Set<String> names = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                names.add(parts[0].trim()); // 假设名字在第一列
            }
        }
        return names;
    }

    private static void processFile(String filePath, Map<String, Set<String>> movieToPeople) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String movieId = parts[0].trim();
                String personId = parts[1].trim();
                movieToPeople.computeIfAbsent(movieId, k -> new HashSet<>()).add(personId);
            }
        }
    }

    private static void processCollaborations(Map<String, Set<String>> movieToPeople, Map<String, Integer> collaborations) {
        for (Set<String> people : movieToPeople.values()) {
            for (String person1 : people) {
                for (String person2 : people) {
                    if (!person1.equals(person2)) {
                        String key = buildKey(person1, person2);
                        collaborations.put(key, collaborations.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }
    }

    private static void processMixedCollaborations(Map<String, Set<String>> movieToActors, Map<String, Set<String>> movieToDirectors, Map<String, Integer> collaborations) {
        for (String movie : movieToActors.keySet()) {
            if (movieToDirectors.containsKey(movie)) {
                for (String actor : movieToActors.get(movie)) {
                    for (String director : movieToDirectors.get(movie)) {
                        String key = buildKey(actor, director);
                        collaborations.put(key, collaborations.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }
    }

    private static String buildKey(String id1, String id2) {
        return id1.compareTo(id2) < 0 ? id1 + "," + id2 : id2 + "," + id1;
    }

    private static void writeToFile(String filePath, Map<String, Integer> collaborations, String header, Set<String> startNames, Set<String> endNames) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            writer.newLine();

            for (Map.Entry<String, Integer> entry : collaborations.entrySet()) {
                String[] parts = entry.getKey().split(",");
                if (startNames.contains(parts[0]) && endNames.contains(parts[1])) {
                    int count = entry.getValue() / 2; // 将计数除以2
                    writer.write(entry.getKey() + "," + count + ",COOPERATE");
                    writer.newLine();
                }
            }
        }
    }

}
