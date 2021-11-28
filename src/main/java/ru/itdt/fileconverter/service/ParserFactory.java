package ru.itdt.fileconverter.service;

import org.apache.commons.io.FilenameUtils;
import ru.itdt.fileconverter.service.converters.Json;
import ru.itdt.fileconverter.service.converters.Xml;

public final class ParserFactory {
    public Parser createParser(String inputFile, String outputFile) {
        Parser parser = new Parser();
        try {
            switch (FilenameUtils.getExtension(inputFile)) {
                case "json" -> parser.setReader(new Json());
                case "xml" -> parser.setReader(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
            switch (FilenameUtils.getExtension(outputFile)) {
                case "json" -> parser.setWriter(new Json());
                case "xml" -> parser.setWriter(new Xml());
                default -> throw new Exception("Ошибка в формате файла");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return parser;
    }
}
