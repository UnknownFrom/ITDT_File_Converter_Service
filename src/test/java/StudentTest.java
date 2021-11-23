import org.junit.*;
import service.structure.Student;

import java.util.ArrayList;

public class StudentTest {
    private ArrayList<Student> students;

    @Before
    public void setUp(){
        students = new ArrayList<>();
        students.add(new Student("Павел"));
        students.add(new Student("Максим"));
    }

    @Test
    public void getName(){
        Assert.assertEquals(students.get(0).getName(), "Павел");
        Assert.assertEquals(students.get(1).getName(), "Максим");
        Assert.assertNotEquals(students.get(0).getName(), "павел");
        Assert.assertNotEquals(students.get(1).getName(), "Владислав");
    }
}
