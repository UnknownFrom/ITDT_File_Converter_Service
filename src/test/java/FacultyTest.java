import org.junit.*;
import service.structure.Faculty;
import service.structure.Student;

import static org.junit.Assert.assertEquals;

public class FacultyTest {

    @Test
    public void getStudents() {
        Faculty faculty = new Faculty("Информатики");
        assertEquals(faculty.getStudents().size(), 0);
    }

    @Test
    public void addStudents() {
        Faculty faculty = new Faculty("Информатики");
        faculty.addStudent(new Student("Павел"));
        faculty.addStudent(new Student("Алексей"));
        faculty.addStudent(new Student("Максим"));
        assertEquals(faculty.getStudents().size(), 3);
    }

    @Test
    public void getFacultyName() {
        Faculty faculty = new Faculty("Информатики");
        assertEquals(faculty.getName(), "Информатики");
    }
}
