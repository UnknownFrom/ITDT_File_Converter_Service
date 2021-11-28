import org.junit.Test;
import ru.itdt.fileconverter.service.structure.Faculty;
import ru.itdt.fileconverter.service.structure.Student;
import ru.itdt.fileconverter.service.structure.University;

import static org.junit.Assert.assertEquals;

public class UniversityTest {

    @Test
    public void getName() {
        University university = new University("ВятГУ");
        assertEquals(university.getName(), "ВятГУ");
    }

    @Test
    public void addFaculty() {
        University university = new University("ВятГУ");
        university.addFaculty(new Faculty("Информационный"));
        assertEquals(university.getFaculties().size(), 1);
        assertEquals(university.getFaculty(0)
                .getName(), "Информационный");
    }

    @Test
    public void addStudent() {
        University university = new University("ВятГУ");
        university.addStudent(new Student("Павел"));
        assertEquals(university.getStudent(0)
                .getName(), "Павел");
    }
}
