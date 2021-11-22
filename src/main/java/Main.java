import classes.Json;
import classes.ManageExtension;
import classes.University;
import classes.Xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* "C:\Program Files\Java\jdk-16.0.2\bin\java.exe" -jar FileConverterService.iml.jar data.xml data.json */
        List<University> universityList = new ArrayList<>();
        ManageExtension extension = new ManageExtension();
        try {
            File read = new File("src/main/java/data/data.xml");
            File write = new File("src/main/java/data/dataResult.json");
            switch (getFileExtension(read)) {
                case "json" -> extension.setReader(new Json());
                case "xml" -> extension.setReader(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            extension.getReader().read(universityList, read.getAbsolutePath()); /* считываем данные */

            switch (getFileExtension(write)) {
                case "json" -> extension.setWriter(new Json());
                case "xml" -> extension.setWriter(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            extension.getWriter().write(universityList, write.getAbsolutePath()); /* записываем данные */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /* определение расширения файла */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        /* если в имени файла есть точка и она не является первым символом в названии файла */
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            /* то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt */
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            /* в противном случае возвращаем заглушку, то есть расширение не найдено */
        else return "";
    }
}
