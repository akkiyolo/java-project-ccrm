package edu.ccrm.domain;

/**
 * Instructor extends Person.
 */
public class Instructor extends Person {
    private String department;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String profileSummary() {
        return String.format("Instructor[%s] %s dept=%s", id, fullName, department);
    }
}
