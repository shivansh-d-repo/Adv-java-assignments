package service;

import dao.CourseDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import model.Course;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class UniversityService {
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private RegistrationDAO registrationDAO = new RegistrationDAO();

    public void addStudent(String name, int year) {
        studentDAO.addStudent(new Student(name, year));
    }

    public void addCourse(String title, int credits) {
        courseDAO.addCourse(new Course(title, credits));
    }

    public void registerStudentForCourse(int studentId, int courseId) {
        registrationDAO.registerStudent(studentId, courseId);
    }

    public void viewAllStudentsWithCourses() {
        Map<Student, List<Course>> map = registrationDAO.getStudentCourses();
        map.forEach((student, courses) -> {
            System.out.println(student + " -> " + courses);
        });
    }

    public void searchCoursesByCredits(int minCredits) {
        List<Course> courses = courseDAO.getAllCourses().stream()
                .filter(c -> c.getCredits() >= minCredits)
                .collect(Collectors.toList());
        courses.forEach(System.out::println);
    }

    public void getStudentsInCourse(int courseId) {
        Map<Student, List<Course>> map = registrationDAO.getStudentCourses();
        map.entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(c -> c.getCourseId() == courseId))
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }

    public void sortStudentsByYearAndName() {
        List<Student> students = studentDAO.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getYear).thenComparing(Student::getName))
                .collect(Collectors.toList());
        students.forEach(System.out::println);
    }

    public void calculateTotalCreditsPerStudent() {
        Map<Student, List<Course>> map = registrationDAO.getStudentCourses();
        map.forEach((student, courses) -> {
            int totalCredits = courses.stream().mapToInt(Course::getCredits).sum();
            System.out.println(student.getName() + " -> Total Credits: " + totalCredits);
        });
    }
}
