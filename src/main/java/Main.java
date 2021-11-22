import classes.Json;
import classes.ManageExtension;
import classes.University;
import classes.Xml;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, SAXException, TransformerException, org.json.simple.parser.ParseException {
        /* "C:\Program Files\Java\jdk-16.0.2\bin\java.exe" -jar FileConverterService.iml.jar data.xml dataResult.json */
        List<University> universityList = new ArrayList<>();
        ManageExtension extension = new ManageExtension();
        File read = new File(args[0]);
        File write = new File(args[1]);
        switch (getFileExtension(read)) {
            case "json" -> extension.setReader(new Json());
            case "xml" -> extension.setReader(new Xml());
        }
        extension.getReader().read(universityList, read.getAbsolutePath()); /* заносим данные */

        switch (getFileExtension(write)) {
            case "json" -> extension.setWriter(new Json());
            case "xml" -> extension.setWriter(new Xml());
        }
        extension.getWriter().write(universityList, write.getAbsolutePath()); /* заносим данные */
    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
