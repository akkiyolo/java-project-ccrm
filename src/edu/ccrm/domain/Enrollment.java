package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Enrollment connects Student and Course, holds marks & grade.
 */
public class Enrollment {
    private final String id;
    private final Student student;
    private final Course course;
    private final LocalDateTime enrolledAt;
    private Double marks; // nullable
    private Grade grade;  // computed when marks recorded

    public Enrollment(String id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }

    public Optional<Double> getMarks() { return Optional.ofNullable(marks); }
    public Optional<Grade> getGrade() { return Optional.ofNullable(grade); }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) throw new IllegalArgumentException("marks out of range");
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    @Override
    public String toString() {
        return String.format("%s | %s -> %s | marks=%s grade=%s",
                id, student.getFullName(), course.getCode(),
                marks == null ? "N/A" : String.format("%.2f", marks),
                grade == null ? "N/A" : grade.name());
    }
}
