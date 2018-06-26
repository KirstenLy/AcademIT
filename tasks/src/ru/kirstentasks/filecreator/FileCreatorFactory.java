package ru.kirstentasks.filecreator;

public class FileCreatorFactory {
    public FileCreator getFileCreator(String arg,String[] args, int[] argsMaxLength,String filename) {
        switch (arg) {
            case "-u":
                return new idUpdater(filename,args,argsMaxLength);
            case "-d":
                return new idDeleter(filename,args,argsMaxLength);
            default:
                return null;
        }
    }
}
