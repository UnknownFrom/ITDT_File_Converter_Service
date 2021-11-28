package ru.itdt.fileconverter.service;

import ru.itdt.fileconverter.service.structure.University;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface Writer {
    void write(List<University> universities, String path) throws ParserConfigurationException, TransformerException;
}