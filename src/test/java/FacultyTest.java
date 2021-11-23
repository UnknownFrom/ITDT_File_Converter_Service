import org.junit.*;
import service.structure.Faculty;
import service.structure.Student;

import java.util.ArrayList;

public class FacultyTest {
    private ArrayList<Faculty> faculties;

    @Before
    public void setUp(){
        Faculty faculty1 = new Faculty("Информатики");
        Faculty faculty2 = new Faculty("Математики");
        faculties = new ArrayList<>();
        faculties.add(faculty1);
        faculties.add(faculty2);
    }

    @Test
    public void getStudents(){
        Faculty faculty = faculties.get(0);
        Assert.assertEquals(faculty.getStudents().size(), 0);
    }

    @Test
    public void addStudents(){
        Faculty faculty = faculties.get(0);
        faculty.addStudent(new Student("Павел"));
        faculty.addStudent(new Student("Алексей"));
        faculty.addStudent(new Student("Максим"));
        Assert.assertEquals(faculty.getStudents().size(), 3);
    }

    @Test
    public void getFacultyName(){
        Assert.assertEquals(faculties.get(0).getName(), "Информатики");
        Assert.assertEquals(faculties.get(1).getName(), "Математики");
    }
}
