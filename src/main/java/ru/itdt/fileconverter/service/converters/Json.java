package ru.itdt.fileconverter.service.converters;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import ru.itdt.fileconverter.service.Reader;
import ru.itdt.fileconverter.service.Writer;
import ru.itdt.fileconverter.service.structure.Faculty;
import ru.itdt.fileconverter.service.structure.Student;
import ru.itdt.fileconverter.service.structure.University;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.charset.StandardCharsets;

public final class Json implements Reader, Writer {
    @Override
    public void read(List<University> universities, String path) throws ParseException {
        readFromJson(universities, path);
    }

    @Override
    public void write(List<University> universities, String path) {
        writeToJson(universities, path);
    }

    void readFromJson(List<University> universities, String path) throws ParseException {
        try (FileReader reader = new FileReader(path, StandardCharsets.UTF_8)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray facultiesJson = (JSONArray) jsonObject.get("faculties");
            int indexFaculty = -1;
            for (Object faculty: facultiesJson) {
                JSONObject facultyJson = (JSONObject) faculty;
                String nameUniversity = (String) facultyJson.get("university");
                String nameFaculty = (String) facultyJson.get("name");

                /* получение индекса университета в списке */
                int indexUniversity = -1;
                for (int index = 0; index < universities.size(); index++) {
                    if (universities.get(index).getName().equals(nameUniversity)) {
                        indexUniversity = index;
                    }
                }
                /* если университета нет, добавляем */
                if (indexUniversity == -1) {
                    universities.add(new University(nameUniversity));
                    indexUniversity = universities.size() - 1;
                }
                /* добавляем факультет к университету */
                universities.get(indexUniversity).addFaculty(new Faculty(nameFaculty));
                indexFaculty++;

                JSONArray studentsJson = (JSONArray) facultyJson.get("students");
                for (Object student : studentsJson) {
                    JSONObject studentJson = (JSONObject) student;
                    String nameStudent = (String) studentJson.get("name");
                    /* общий список студентов в университете */
                    universities.get(indexUniversity).addStudent(new Student(nameStudent));
                    /* добавляем студента на факультет */
                    universities.get(indexUniversity).getFaculty(indexFaculty).addStudent(new Student(nameStudent));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void writeToJson(List<University> universities, String path) {
        JSONObject result = new JSONObject();/* основной объект для записи результата */
        JSONArray facultiesJson = new JSONArray();
        for (University university : universities) {
            /* список факультетов университета */
            List<Faculty> faculties = university.getFaculties();
            for (Faculty faculty : faculties) {
                List<Student> students = faculty.getStudents();
                JSONArray studentsJson = new JSONArray();
                HashMap<String, String> studentsMap = new HashMap<>();
                for (Student student : students) {
                    JSONObject studentJson = new JSONObject();
                    studentJson.put("name", student.getName());
                    studentsJson.add(studentJson);
                }
                /* создание одного объекта "факультет" */
                JSONObject facultyJson = new JSONObject();
                facultyJson.put("students", studentsJson);
                facultyJson.put("name", faculty.getName());
                facultyJson.put("university", university.getName());
                /* занесение факультета в массив */
                facultiesJson.add(facultyJson);
            }
            result.put("faculties", facultiesJson);
        }
        /* запись в файл */
        try (FileWriter file = new FileWriter(path)) {
            file.write(result.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
