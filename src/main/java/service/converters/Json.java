package service.converters;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.IReader;
import service.IWriter;
import service.structure.Faculty;
import service.structure.Student;
import service.structure.University;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Json implements IReader, IWriter {
    @Override
    public void read(List<University> universities, String path) throws IOException, ParseException {
        readFromJson(universities, path);
    }

    @Override
    public void write(List<University> universities, String path) {
        writeToJson(universities, path);
    }

    private static void readFromJson(List<University> universities, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path, StandardCharsets.UTF_8);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray facultiesJson = (JSONArray) jsonObject.get("faculties");
        Iterator iterator = facultiesJson.iterator();
        int indexFaculty = -1;
        while (iterator.hasNext()) {
            JSONObject facultyJson = (JSONObject) iterator.next();
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
    }

    private void writeToJson(List<University> universities, String path) {
        JSONObject result = new JSONObject();/* основной объект для записи результата */
        JSONArray facultiesJson = new JSONArray();
        for (University university : universities) {
            /* список факультетов университета */
            List<Faculty> faculties = university.getFaculties();
            for (Faculty faculty : faculties) {
                List<Student> students = faculty.getStudents();
                JSONArray studentsJson = new JSONArray();
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
        try {
            /* запись в файл */
            FileWriter file = new FileWriter(path);
            file.write(result.toJSONString());
            file.flush();
            file.close();
            //System.out.print(result.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
