package ru.kirstentasks.filecreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class FileCreator {
    protected String[] args;
    protected int[] argsMaxLength;
    protected String fileName;

    FileCreator(String fileName, String[] args, int[] argsMaxLength) {
        this.args = args;
        this.argsMaxLength = argsMaxLength;
        this.fileName = fileName;
    }

    public String isLineIsset() throws IOException {
        String result;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (!((result = reader.readLine()) == null)) {
            if (args[1].trim().equals(result.substring(0, argsMaxLength[1]).trim())) {
                reader.close();
                return result;
            }
        }
        reader.close();
        return null;
    }

    public abstract File createNewFile(String line);
}
