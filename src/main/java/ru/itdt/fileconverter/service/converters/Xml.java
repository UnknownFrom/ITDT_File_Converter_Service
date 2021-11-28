package ru.itdt.fileconverter.service.converters;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.itdt.fileconverter.service.Reader;
import ru.itdt.fileconverter.service.Writer;
import ru.itdt.fileconverter.service.structure.Faculty;
import ru.itdt.fileconverter.service.structure.Student;
import ru.itdt.fileconverter.service.structure.University;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class Xml implements Reader, Writer {
    @Override
    public void read(List<University> universities, String path) throws ParserConfigurationException, IOException, SAXException {
        readFromXml(universities, path);
    }

    @Override
    public void write(List<University> universities, String path) throws ParserConfigurationException {
        writeToXml(universities, path);
    }

    void writeToXml(List<University> universities, String path) throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.newDocument();

        /* создание корневого элемента */
        Element rootElement = document.createElement("universities");
        document.appendChild(rootElement);

        for (University university : universities) {
            /* создание отдельных университетов */
            Element universityElement = document.createElement("university");
            rootElement.appendChild(universityElement);

            /* создание атрибутов */
            Attr nameUniversity = document.createAttribute("name");
            nameUniversity.setValue(university.getName());
            universityElement.setAttributeNode(nameUniversity);

            List<Faculty> faculties = university.getFaculties();
            for (Faculty faculty : faculties) {
                /* создание отдельных факультетов */
                Element facultyElement = document.createElement("faculty");
                universityElement.appendChild(facultyElement);

                /* создание атрибутов */
                Attr nameFaculty = document.createAttribute("name");
                nameFaculty.setValue(faculty.getName());
                facultyElement.setAttributeNode(nameFaculty);

                List<Student> students = faculty.getStudents();
                for (Student student : students) {
                    /* создание отдельных студентов */
                    Element studentElement = document.createElement("student");
                    facultyElement.appendChild(studentElement);

                    /* создание атрибутов */
                    Attr nameStudent = document.createAttribute("name");
                    nameStudent.setValue(student.getName());
                    studentElement.setAttributeNode(nameStudent);
                }
            }
        }
        /* запись в XML документ */
        createFile(document, path);
    }

    void createFile(Document document, String path) {
        /* запись в XML документ */
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    void readFromXml(List<University> universities, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        /* получение списка всех элементов university */
        NodeList universitiesElements = document.getDocumentElement().getElementsByTagName("university");
        /* перебор всех элементов university */
        for (int i = 0; i < universitiesElements.getLength(); i++) {
            Node university = universitiesElements.item(i);
            NamedNodeMap universityAttributes = university.getAttributes();
            String universityName = universityAttributes.getNamedItem("name").getNodeValue();
            universities.add(new University(universityName));

            int countFaculties = -1; /* кол-во факультетов в университете */
            NodeList facultiesElements = university.getChildNodes();
            for (int k = 0; k < facultiesElements.getLength(); k++) {
                Node faculty = facultiesElements.item(k);
                if (faculty.getNodeType() != Node.TEXT_NODE) {
                    NamedNodeMap facultyAttributes = faculty.getAttributes();
                    String facultyName = facultyAttributes.getNamedItem("name").getNodeValue();
                    universities.get(i).addFaculty(new Faculty(facultyName));
                    countFaculties++;

                    NodeList studentsElements = faculty.getChildNodes();
                    for (int m = 0; m < studentsElements.getLength(); m++) {
                        Node student = studentsElements.item(m);
                        if (student.getNodeType() != Node.TEXT_NODE) {
                            NamedNodeMap studentAttributes = student.getAttributes();
                            String nameStud = studentAttributes.getNamedItem("name").getNodeValue();
                            universities.get(i)
                                    .getFaculty(countFaculties).addStudent(new Student(nameStud));
                            universities.get(i).addStudent(new Student(nameStud));
                        }
                    }
                }
            }
        }
    }
}
