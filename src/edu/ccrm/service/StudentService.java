package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple StudentService demonstrating CRUD.
 */
public class StudentService {
    private final DataStore ds = DataStore.getInstance();

    public Student addStudent(String id, String regNo, String fullName, String email, LocalDate dob) {
        Student s = new Student(id, regNo, fullName, email, dob);
        ds.getStudents().put(id, s);
        return s;
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(ds.getStudents().get(id));
    }

    public List<Student> listAll() {
        return new ArrayList<>(ds.getStudents().values());
    }

    public List<Student> searchByName(String namePart) {
        return ds.getStudents().values().stream()
                .filter(st -> st.getFullName().toLowerCase().contains(namePart.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean deactivate(String id) {
        Student s = ds.getStudents().get(id);
        if (s == null) return false;
        s.deactivate();
        return true;
    }
}
