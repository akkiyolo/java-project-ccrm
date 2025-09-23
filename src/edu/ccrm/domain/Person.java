package edu.ccrm.domain;

import java.time.LocalDateTime;

/**
 * Abstract base class for Student and Instructor - demonstrates Abstraction & inheritance.
 */
public abstract class Person {
    protected final String id;
    protected String fullName;
    protected String email;
    protected boolean active;
    protected final LocalDateTime createdAt;

    public Person(String id, String fullName, String email) {
        assert id != null && !id.isBlank();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    public abstract String profileSummary();

    @Override
    public String toString() {
        return String.format("%s (%s) <%s> active=%b", fullName, id, email, active);
    }
}
