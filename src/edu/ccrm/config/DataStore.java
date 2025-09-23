package edu.ccrm.config;

import edu.ccrm.domain.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory datastore demonstrating Singleton storage for the app.
 */
public class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final Map<String, Student> students = new ConcurrentHashMap<>();
    private final Map<String, Course> courses = new ConcurrentHashMap<>();
    private final Map<String, Instructor> instructors = new ConcurrentHashMap<>();
    private final List<Enrollment> enrollments = Collections.synchronizedList(new ArrayList<>());

    private DataStore() {}

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public Map<String, Student> getStudents() { return students; }
    public Map<String, Course> getCourses() { return courses; }
    public Map<String, Instructor> getInstructors() { return instructors; }
    public List<Enrollment> getEnrollments() { return enrollments; }
}
