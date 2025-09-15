package dao;

import connector.DBConnection;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (title, credits) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, title, credits FROM courses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getInt("credits")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
