package model;

public class Course {
    private int courseId;
    private String title;
    private int credits;

    public Course(int courseId, String title, int credits) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
    }

    public Course(String title, int credits) {
        this.title = title;
        this.credits = credits;
    }

    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setTitle(String title) { this.title = title; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + courseId +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}
