import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVUpdate {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("D:\\kettle\\toBeMergedByName.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        Map<String, String> asinToTime = new HashMap<>();

        try (Reader in2 = new FileReader("D:\\javacode\\ETLHomework\\output\\result.csv")) {
            Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.parse(in2);
            for (CSVRecord record : records2) {
                String productId = record.get(0);
                String time = record.get(5);
                if (asinToTime.containsKey(productId)) {
                    if (asinToTime.get(productId).compareTo(time) > 0) {
                        asinToTime.put(productId, time);
                    }
                } else {
                    asinToTime.put(productId, time);
                }
            }
        }

        List<List<String>> updatedRecords = new ArrayList<>();
        for (CSVRecord record : records) {
            String releaseTime = record.get(2);
            String asin = record.get(1);
            if (releaseTime == null || releaseTime.trim().isEmpty()) {
                if (asinToTime.containsKey(asin)) {
                    long unixSeconds = Long.parseLong(asinToTime.get(asin));
                    Date date = new Date(unixSeconds*1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(date);
                    List<String> newRecord = new ArrayList<>();
                    for (String value : record) {
                        newRecord.add(value);
                    }
                    newRecord.set(2, formattedDate);
                    updatedRecords.add(newRecord);
                }
            } else {
                List<String> newRecord = new ArrayList<>();
                for (String value : record) {
                    newRecord.add(value);
                }
                updatedRecords.add(newRecord);
            }
        }

        try (Writer out = new FileWriter("D:\\kettle\\MergedByName.csv")) {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            for (List<String> record : updatedRecords) {
                printer.printRecord(record);
            }
        }
    }
}
