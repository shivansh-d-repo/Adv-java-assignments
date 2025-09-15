package dao;

import connector.DBConnection;
import model.Student;
import model.Course;

import java.sql.*;
import java.util.*;

public class RegistrationDAO {

    public void registerStudent(int studentId, int courseId) {
        String sql = "INSERT INTO registrations (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Student, List<Course>> getStudentCourses() {
        Map<Student, List<Course>> map = new HashMap<>();
        String sql = "SELECT s.student_id, s.name, s.year, c.course_id, c.title, c.credits " +
                "FROM students s " +
                "LEFT JOIN registrations r ON s.student_id = r.student_id " +
                "LEFT JOIN courses c ON r.course_id = c.course_id";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("year")
                );
                Course course = null;
                if (rs.getInt("course_id") != 0) {
                    course = new Course(
                            rs.getInt("course_id"),
                            rs.getString("title"),
                            rs.getInt("credits")
                    );
                }
                map.computeIfAbsent(student, k -> new ArrayList<>());
                if (course != null) {
                    map.get(student).add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
