package ru.kirstentasks.filecreator;

import java.io.*;

public class idDeleter extends FileCreator {
    private String fileName;

    idDeleter(String fileName, String[] args, int[] argsMaxLength) {
        super(fileName, args, argsMaxLength);
        this.fileName = fileName;
    }


    @Override
    public File createNewFile(String line) {
        BufferedReader fileReader;
        BufferedWriter fileWriter;

        File tempFile = new File(fileName + ".temp");

        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            fileWriter = new BufferedWriter(new FileWriter(tempFile));
            String temp;
            while (!((temp = fileReader.readLine()) == null)) {
                if (temp.equals(line)) {
                    continue;
                }
                fileWriter.write(temp);
                fileWriter.newLine();
            }
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
