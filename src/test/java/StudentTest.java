import org.junit.*;
import com.pavel.service.structure.Student;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    @Test
    public void getName() {
        Student student = new Student("Павел");
        assertEquals(student.getName(), "Павел");
    }
}
