import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import com.pavel.service.Reader;
import com.pavel.service.structure.University;
import com.pavel.service.converters.Xml;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class XmlTest {
    List<University> universities;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, ParseException, org.json.simple.parser.ParseException, SAXException {
        universities = new ArrayList<>();
        Reader xml = new Xml();
        xml.read(universities, new File("src/main/java/com/pavel/data/data.xml").getAbsolutePath());
    }

    @Test
    public void countOfUniversities() {
        assertEquals(universities.size(), 1);
    }

    @Test
    public void countOfFaculties() {
        assertEquals(universities.get(0).getFaculties().size(), 5);
    }

    @Test
    public void countOfStudents() {
        assertEquals(universities.get(0).getStudents().size(), 5);
    }
}
