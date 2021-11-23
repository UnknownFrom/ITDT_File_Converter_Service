import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.structure.Faculty;
import service.structure.Student;
import service.structure.University;

import java.util.ArrayList;

public class UniversityTest {
    private ArrayList<University> universities;

    @Before
    public void setUp(){
        universities = new ArrayList<>();
        universities.add(new University("ВятГУ"));
        universities.add(new University("МГУ"));
    }

    @Test
    public void getName(){
        Assert.assertEquals(universities.get(0).getName(), "ВятГУ");
        Assert.assertEquals(universities.get(1).getName(), "МГУ");
    }

    @Test
    public void addFaculty(){
        University university = universities.get(0);
        university.addFaculty(new Faculty("Информационный"));
        Assert.assertEquals(university.getFaculties().size(), 1);
        Assert.assertEquals(university.getFaculty(0).getName(), "Информационный");
    }

    @Test
    public void addStudent(){
        University university = universities.get(0);
        university.addStudent(new Student("Павел"));
        Assert.assertEquals(university.getStudent(0).getName(), "Павел");
    }
}
