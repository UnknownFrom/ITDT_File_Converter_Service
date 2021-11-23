import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import service.*;
import service.converters.Json;
import service.structure.University;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    List<University> universities;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, ParseException, org.json.simple.parser.ParseException, SAXException {
        universities = new ArrayList<>();
        IReader json = new Json();
        json.read(universities, new File("src/main/java/data/data.json").getAbsolutePath());
    }

    @Test
    public void countOfUniversities(){
        Assert.assertEquals(universities.size(), 1);
    }

    @Test
    public void countOfFaculties(){
        Assert.assertEquals(universities.get(0).getFaculties().size(), 5);
    }

    @Test
    public void countOfStudents(){
        Assert.assertEquals(universities.get(0).getStudents().size(), 5);
    }
}
