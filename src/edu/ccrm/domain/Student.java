package edu.ccrm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Student with enrolledCourses; demonstrates encapsulation and toString override.
 */
public class Student extends Person {
    private String regNo;
    private LocalDate dob;
    // Map courseCode -> Enrollment
    private final Map<String, Enrollment> enrolledCourses = new HashMap<>();
    private final LocalDateTime registeredAt;

    public Student(String id, String regNo, String fullName, String email, LocalDate dob) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.dob = dob;
        this.registeredAt = LocalDateTime.now();
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public void enroll(Enrollment e) {
        enrolledCourses.put(e.getCourse().getCode(), e);
    }
    public void unenroll(String courseCode) {
        enrolledCourses.remove(courseCode);
    }

    public Collection<Enrollment> getEnrollments() {
        return enrolledCourses.values();
    }

    @Override
    public String profileSummary() {
        String enrolled = enrolledCourses.keySet().stream().sorted().collect(Collectors.joining(", "));
        return String.format("Student[%s] %s | regNo=%s | enrolled=[%s]", id, fullName, regNo, enrolled);
    }

    @Override
    public String toString() {
        return profileSummary();
    }
}
