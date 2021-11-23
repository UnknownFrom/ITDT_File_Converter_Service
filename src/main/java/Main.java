import service.converters.Json;
import service.ManageExtension;
import service.structure.University;
import service.converters.Xml;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<University> universities = new ArrayList<>();
        ManageExtension extension = new ManageExtension();
        try {
            //File read = new File(args[0]);
            //File write = new File(args[1]);
            File read = new File("src/main/java/data/data.json");
            File write = new File("src/main/java/data/dataResult.xml");
            switch (FilenameUtils.getExtension(read.getAbsolutePath())) {
                case "json" -> extension.setReader(new Json());
                case "xml" -> extension.setReader(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            extension.getReader().read(universities, read.getAbsolutePath()); /* считываем данные */

            switch (FilenameUtils.getExtension(write.getAbsolutePath())) {
                case "json" -> extension.setWriter(new Json());
                case "xml" -> extension.setWriter(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            extension.getWriter().write(universities, write.getAbsolutePath()); /* записываем данные */
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
