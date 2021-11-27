package ru.pavel.service;

import org.xml.sax.SAXException;
import ru.pavel.service.structure.University;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Reader {
    void read(List<University> universities, String path) throws ParserConfigurationException, IOException, SAXException, ParseException, org.json.simple.parser.ParseException;
}
