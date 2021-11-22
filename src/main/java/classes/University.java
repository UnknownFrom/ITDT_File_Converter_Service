package classes;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private List<Faculty> faculties;
    private List<Student> students;

    public University(String name) {
        this.name = name;
        faculties = new ArrayList<>();
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty(int index) {
        return faculties.get(index);
    }

    public Student getStudent(int index) {
        return students.get(index);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void addFaculty(Faculty faculty) {
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
        }
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }
}
