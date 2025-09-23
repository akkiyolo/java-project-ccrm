package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CourseService with search using Streams and lambdas.
 */
public class CourseService {
    private final DataStore ds = DataStore.getInstance();

    public Course addCourse(Course course) {
        ds.getCourses().put(course.getCode(), course);
        return course;
    }

    public Optional<Course> findByCode(String code) {
        return Optional.ofNullable(ds.getCourses().get(code));
    }

    public List<Course> listAll() {
        return new ArrayList<>(ds.getCourses().values());
    }

    public void assignInstructor(String code, Instructor instructor) {
        Course c = ds.getCourses().get(code);
        if (c != null) c.setInstructor(instructor);
    }

    public List<Course> filterByInstructor(String instructorName) {
        return ds.getCourses().values().stream()
                .filter(c -> c.getInstructor() != null)
                .filter(c -> c.getInstructor().getFullName().toLowerCase().contains(instructorName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String dept) {
        return ds.getCourses().values().stream()
                .filter(c -> c.getDepartment() != null && c.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester sem) {
        return ds.getCourses().values().stream()
                .filter(c -> c.getSemester() == sem)
                .collect(Collectors.toList());
    }
}
