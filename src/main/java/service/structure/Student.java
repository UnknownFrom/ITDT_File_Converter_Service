package service.structure;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final List<String> faculties; /* на какие факультеты поступил */

    public Student(String name) {
        this.name = name;
        faculties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addFaculties(String fac) {
        if (!faculties.contains(fac)) {
            faculties.add(fac);
        }
    }
}
