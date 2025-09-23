package edu.ccrm.io;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.*;
import edu.ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Import/export using NIO.2 and Streams.
 * students.csv: id,regNo,fullName,email,dob(yyyy-mm-dd)
 * courses.csv: code,title,credits,department,semester
 */
public class ImportExportService {
    private final DataStore ds = DataStore.getInstance();
    private final AppConfig cfg = AppConfig.getInstance();

    public void importStudents(Path csv) throws IOException {
        try (Stream<String> lines = Files.lines(csv, StandardCharsets.UTF_8)) {
            lines.filter(l -> !l.isBlank())
                 .forEach(line -> {
                     String[] parts = line.split(",");
                     if (parts.length >= 5) {
                         String id = parts[0].trim();
                         String reg = parts[1].trim();
                         String name = parts[2].trim();
                         String email = parts[3].trim();
                         LocalDate dob = LocalDate.parse(parts[4].trim());
                         Student s = new Student(id, reg, name, email, dob);
                         ds.getStudents().put(id, s);
                     }
                 });
        }
    }

    public void importCourses(Path csv) throws IOException {
        try (Stream<String> lines = Files.lines(csv, StandardCharsets.UTF_8)) {
            lines.filter(l -> !l.isBlank())
                 .forEach(line -> {
                     String[] p = line.split(",");
                     if (p.length >= 5) {
                         String code = p[0].trim();
                         String title = p[1].trim();
                         int credits = Integer.parseInt(p[2].trim());
                         String dept = p[3].trim();
                         Semester sem = Semester.valueOf(p[4].trim().toUpperCase());
                         Course c = new Course.Builder(code)
                                 .title(title)
                                 .credits(credits)
                                 .department(dept)
                                 .semester(sem)
                                 .build();
                         ds.getCourses().put(code, c);
                     }
                 });
        }
    }

    public Path exportAll() throws IOException {
        Path folder = cfg.getDataFolder();
        if (!Files.exists(folder)) Files.createDirectories(folder);

        Path studentsFile = folder.resolve("students_export.csv");
        Path coursesFile = folder.resolve("courses_export.csv");
        Path enrollFile = folder.resolve("enrollments_export.csv");

        // students
        List<String> sLines = ds.getStudents().values().stream()
                .map(s -> String.join(",",
                        s.getId(), s.getRegNo(), s.getFullName(),
                        s.getEmail(), s.getDob().toString()))
                .collect(Collectors.toList());
        Files.write(studentsFile, sLines, StandardCharsets.UTF_8);

        // courses
        List<String> cLines = ds.getCourses().values().stream()
                .map(c -> String.join(",",
                        c.getCode(), c.getTitle(),
                        String.valueOf(c.getCredits()),
                        c.getDepartment() == null ? "" : c.getDepartment(),
                        c.getSemester().name()))
                .collect(Collectors.toList());
        Files.write(coursesFile, cLines, StandardCharsets.UTF_8);

        // enrollments
        List<String> eLines = ds.getEnrollments().stream()
                .map(e -> String.join(",",
                        e.getId(), e.getStudent().getId(),
                        e.getCourse().getCode(),
                        e.getMarks().map(Object::toString).orElse(""),
                        e.getGrade().map(Enum::name).orElse("")))
                .collect(Collectors.toList());
        Files.write(enrollFile, eLines, StandardCharsets.UTF_8);

        return folder;
    }
}
