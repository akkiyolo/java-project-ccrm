package edu.ccrm.domain;

/**
 * Immutable-ish Course with Builder pattern.
 */
public class Course {
    private final String code;
    private String title;
    private final int credits;
    private Instructor instructor;
    private final Semester semester;
    private String department;
    private boolean active = true;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.instructor = b.instructor;
        this.semester = b.semester;
        this.department = b.department;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }

    public void setTitle(String title) { this.title = title; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public void setDepartment(String department) { this.department = department; }
    public void deactivate() { this.active = false; }

    @Override
    public String toString() {
        String instr = instructor == null ? "TBD" : instructor.getFullName();
        return String.format("%s - %s (%d cr) [%s] %s", code, title, credits, semester, instr);
    }

    public static class Builder {
        private final String code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;

        public Builder(String code) {
            this.code = code;
        }

        public Builder title(String t) { this.title = t; return this; }
        public Builder credits(int c) { this.credits = c; return this; }
        public Builder instructor(Instructor i) { this.instructor = i; return this; }
        public Builder semester(Semester s) { this.semester = s; return this; }
        public Builder department(String d) { this.department = d; return this; }

        public Course build() {
            if (code == null || code.isBlank()) throw new IllegalStateException("code required");
            if (credits <= 0) throw new IllegalStateException("credits required");
            return new Course(this);
        }
    }
}
