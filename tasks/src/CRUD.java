import ru.kirstentasks.filecreator.FileCreator;
import ru.kirstentasks.filecreator.FileCreatorFactory;

import java.io.*;
import java.nio.file.Files;

public class CRUD {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filename = reader.readLine();
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));

            int[] argsLength = new int[]{0, 8, 30, 8, 4};

            FileCreator creator = new FileCreatorFactory().getFileCreator(args[0], args, argsLength, filename);
            String line;

            if (creator == null) {
                System.out.println("Неизвестная команда");
                return;
            }
            if ((line = creator.isLineIsset()) == null) {
                System.out.println("Запись с таким id не обнаружена");
                return;
            }

            File resultFile = creator.createNewFile(line);

            if (resultFile.length() == 0) {
                System.out.println("Неизвестная ошибка во время создания файла");
                return;
            }

            reader.close();
            fileReader.close();
            Files.delete(new File(filename).toPath());
            System.out.println("Результат:" + (resultFile.renameTo(new File(filename))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
