package org.example;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;
import com.opencsv.*;

public class CSVProcessor {
    public static void main(String[] args) throws Exception {
        String inputFile = "D:\\university\\数据仓库\\movie_comment_withtype.csv";
        String outputFile = "D:\\university\\数据仓库\\movie_comment_withType.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFile));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            String[] line;
            int lineNumber = 1;
            while ((line = reader.readNext()) != null) {
                if (lineNumber == 1) {
                    String[] newLine = Arrays.copyOf(line, line.length + 1);
                    newLine[newLine.length - 1] = "Type"; // 在第一行的最后添加"Type"
                    writer.writeNext(newLine);
                } else {
                    if(line.length==22){
                        writer.writeNext(line);
                    }else{
                        System.err.println("lineNumber:"+lineNumber+"*"+line);
                    }

                }
                lineNumber++;
            }
        }
    }
}
