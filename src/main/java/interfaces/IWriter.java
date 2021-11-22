package interfaces;

import classes.University;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface IWriter {
    void write(List<University> universityList, String pathDocument) throws ParserConfigurationException, TransformerException;
}