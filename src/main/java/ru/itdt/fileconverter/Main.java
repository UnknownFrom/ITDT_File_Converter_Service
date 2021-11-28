package ru.itdt.fileconverter;

import ru.itdt.fileconverter.service.ParserFactory;
import ru.itdt.fileconverter.service.Parser;
import ru.itdt.fileconverter.service.structure.University;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<University> universities = new ArrayList<>();
        ParserFactory parserFactory = new ParserFactory();
        try {
            File read, write;
            if (args.length > 0) {
                read = new File(args[0]);
                write = new File(args[1]);
            } else {
                read = new File("src/main/resources/data.json");
                write = new File("src/main/resources/dataResult.json");
            }
            Parser parser = parserFactory.createParser(read.getAbsolutePath(), write.getAbsolutePath());
            parser.getReader().read(universities, read.getAbsolutePath()); /* считываем данные */
            parser.getWriter().write(universities, write.getAbsolutePath()); /* записываем данные */
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
