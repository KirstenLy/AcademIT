package ru.academit.csv.main;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String inputPath = "CSV\\src\\table.csv";
        String outputPath = "CSV\\src\\table.html";
        StringBuilder resultString = new StringBuilder();
        char delimiter = ',';
        boolean isInQuotes = false;

        try (Scanner reader = new Scanner(new FileInputStream(inputPath), "windows-1251");
             PrintWriter writer = new PrintWriter(outputPath)) {

            if (!reader.hasNext()) {
                throw new IllegalArgumentException("Исходный фаил пуст.");
            }

            writer.append("<!DOCTYPE HTML>\n<html>\n");
            writer.append("<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n</head>\n");
            writer.append("<title>CSV</title>\n");
            writer.append("<body>\n");
            writer.append("<table border=\"1\" width=\"100%\">\n");

            while (reader.hasNextLine()) {
                if (!isInQuotes) {
                    writer.append("<tr>\n");
                }
                char[] charArrayFromLine = reader.nextLine().toCharArray();

                for (int i = 0; i < charArrayFromLine.length; i++) {
                    if (charArrayFromLine[i] == delimiter) {
                        resultString.append("");
                    } else if (charArrayFromLine[i] == '"') {
                        isInQuotes = !isInQuotes;
                    } else if (charArrayFromLine[i] == '<') {
                        resultString.append("&lt;");
                    } else if (charArrayFromLine[i] == '>') {
                        resultString.append("&gt;");
                    } else if (charArrayFromLine[i] == '&') {
                        resultString.append("&amp;");
                    } else {
                        resultString.append(charArrayFromLine[i]);
                    }

                    if (!isInQuotes & (charArrayFromLine[i] == delimiter | (i == charArrayFromLine.length - 1 & !isInQuotes))) {
                        writer.append("\n<td><center>").append(resultString.toString()).append("</center></td>\n");
                        resultString.setLength(0);
                    }

                    if (isInQuotes & i == charArrayFromLine.length - 1) {
                        resultString.append("<br>");
                    }
                }
                if (!isInQuotes) {
                    writer.append("</tr>\n");
                }
            }
            writer.append("\n</table>\n</body>\n</html>");
            System.out.print("Done.");
        } catch (FileNotFoundException e) {
            System.out.println("Исходный фаил не найден.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}