package ru.kirstentasks.filecreator;

import java.io.*;

public class idUpdater extends FileCreator {
    private String filename;

    idUpdater(String filename, String[] args, int[] argsMaxLength) {
        super(filename, args, argsMaxLength);
        this.filename = filename;
    }

    @Override
    public File createNewFile(String line) {
        BufferedReader fileReader;
        BufferedWriter fileWriter;

        File tempFile = new File(filename + ".temp");

        try {
            fileReader = new BufferedReader(new FileReader(filename));
            fileWriter = new BufferedWriter(new FileWriter(tempFile));
            String temp;
            while (!((temp = fileReader.readLine()) == null)) {
                if (temp.equals(line)) {
                    temp = createLine(args, argsMaxLength);
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

    private String createLine(String[] args, int[] argsLength) {
        if (args.length != argsLength.length) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(String.format("%-" + argsLength[i] + "s", args[i]));
        }
        return sb.toString();
    }
}
