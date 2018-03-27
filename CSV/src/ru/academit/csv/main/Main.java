package ru.academit.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String inputPath = "CSV\\src\\table.csv";
        String outputPath = "CSV\\src\\table.html";
        StringBuilder resultString = new StringBuilder();
        char delimiter = ';';
        boolean flag = true;
        boolean flag1 = false;

        while (true) {
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
                    char[] lineArray = reader.nextLine().toCharArray();
                    int lastIndexDelimiter = 0;

                    if (!flag1) {
                        writer.append("<tr>\n");
                    }

                    for (int i = 0; i < lineArray.length; i++) {
                        if (lineArray[i] == delimiter) {
                            lastIndexDelimiter = i;
                            if (flag1) {
                                writer.append(resultString.toString()).append("</td>");
                                resultString.setLength(0);
                                flag1 = false;
                            } else {
                                writer.append("<td>").append(resultString.toString()).append("</td>");
                                resultString.setLength(0);
                            }
                        } else if (i == lineArray.length - 1 & lineArray[lastIndexDelimiter + 1] != '"') {
                            resultString.append(lineArray[i]);
                            writer.append("<td>").append(resultString.toString()).append("</td>");
                            resultString.setLength(0);
                            writer.append("</tr>");
                        } else if (i == lineArray.length - 1 & lineArray[lastIndexDelimiter + 1] == '"') {
                            resultString.append("<td>").append(lineArray[i]).append("<br>");

                        } else {
                            resultString.append(lineArray[i]);
                        }
                    }
                    flag1 = lineArray[lastIndexDelimiter + 1] == '"';
                    if (!flag1) {
                        writer.append("\n</tr>\n");
                    }
                }

                writer.append("</table>\n</body>\n</html>");
                System.out.print("Done.");
                return;
            } catch (FileNotFoundException e) {
                System.out.println("Исходный фаил не найден. Укажите корректный путь к CSV таблице:");
                inputPath = new Scanner(System.in).nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Исходный фаил пуст. Укажите корректный путь к CSV таблице:");
                inputPath = new Scanner(System.in).nextLine();
            }
        }
    }
}

//    String line = reader.nextLine();
//                    if (line.isEmpty()) {
//                            continue;
//                            }
//                            char[] charsFromLine = line.toCharArray();
//                            if (lastSymbolInLine == delimiter) {
//                            writer.append("<tr>\n");
//                            }
//                            lastSymbolInLine = charsFromLine[charsFromLine.length - 1];
//
//                            for (int i = 0; i < charsFromLine.length; i++) {
//        if (charsFromLine[i] == delimiter) {
//        writer.append("<td><center>").append(temp).append(stringBuilder.toString()).append("</center></td>\n");
//        stringBuilder.setLength(0);
//        } else if (charsFromLine[i] == '<') {
//        stringBuilder.append("&lt;");
//        } else if (charsFromLine[i] == '>') {
//        stringBuilder.append("&gt;");
//        } else if (charsFromLine[i] == '&') {
//        stringBuilder.append("&amp;");
//        } else if (i == charsFromLine.length - 1) {
//        stringBuilder.append(charsFromLine[i]);
//        stringBuilder.append("<br>");
//        } else {
//        stringBuilder.append(charsFromLine[i]);
//        }
//        }