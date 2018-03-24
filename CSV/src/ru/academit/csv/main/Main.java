package ru.academit.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String inputPath = "CSV\\src\\table.csv";
        String outputPath = "CSV\\src\\table.html";
        String delimiter = ";";

        try (Scanner reader = new Scanner(new FileInputStream(inputPath), "windows-1251");
             PrintWriter writer = new PrintWriter(outputPath)) {

            writer.append("<table border=\"1\" width=\"100%\">");
            while (reader.hasNextLine()) {
                writer.append("<tr>");

                String[] strings = reader.nextLine().replace("&", "&amp").replace("<", "&lt").replace(">", "&gt").split(delimiter);

                for (String string : strings) {
                    writer.append("<td><center>");
                    writer.append(string);
                    writer.append("</center></td>");
                }
                writer.append("<br></tr>");
            }
            writer.append("</table>");
            System.out.print("Done.");
        }
    }
}
