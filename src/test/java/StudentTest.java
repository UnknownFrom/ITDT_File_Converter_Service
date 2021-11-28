import org.junit.*;
import ru.itdt.fileconverter.service.structure.Student;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    @Test
    public void getName() {
        Student student = new Student("Павел");
        assertEquals(student.getName(), "Павел");
    }
}
