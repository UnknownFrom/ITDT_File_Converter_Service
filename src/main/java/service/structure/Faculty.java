package service.structure;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private final String name;
    private final List<Student> students;

    public Faculty(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student stud) {
        if (!students.contains(stud)) {
            students.add(stud);
        }
    }
}
