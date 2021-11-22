package interfaces;

import classes.University;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface IReader {
    void read(List<University> universityList, String pathDocument) throws ParserConfigurationException, IOException, SAXException, ParseException, org.json.simple.parser.ParseException;
}
