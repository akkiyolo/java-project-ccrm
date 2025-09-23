package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.config.DataStore;
import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * CLI entry point. Demonstrates loops, switch, labeled break, and use of services.
 */
public class MainMenu {
    private static final Scanner SC = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final ImportExportService ioService = new ImportExportService();
    private final BackupService backupService = new BackupService();
    private final AppConfig cfg = AppConfig.getInstance();
    private final DataStore ds = DataStore.getInstance();

    public static void main(String[] args) {
        MainMenu app = new MainMenu();
        app.seedSampleData();
        app.run();
    }

    private void run() {
        MAIN_LOOP:
        while (true) {
            printMenu();
            String choice = SC.nextLine().trim();
            switch (choice) {
                case "1" -> manageStudents();
                case "2" -> manageCourses();
                case "3" -> manageEnrollment();
                case "4" -> importExport();
                case "5" -> backupAndReports();
                case "6" -> { System.out.println("Exiting."); break MAIN_LOOP; }
                default -> System.out.println("Unknown option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== CCRM Main Menu ===");
        System.out.println("1) Manage Students");
        System.out.println("2) Manage Courses");
        System.out.println("3) Enrollment & Grades");
        System.out.println("4) Import/Export Data");
        System.out.println("5) Backup & Reports");
        System.out.println("6) Exit");
        System.out.print("Choose> ");
    }

    private void manageStudents() {
        System.out.println("--- Students ---");
        System.out.println("a) Add sample student");
        System.out.println("b) List all");
        System.out.println("c) Deactivate by id");
        System.out.print("opt> ");
        switch (SC.nextLine().trim()) {
            case "a" -> {
                String id = "S" + System.currentTimeMillis()%10000;
                Student s = studentService.addStudent(id, "REG-" + id, "Student " + id,
                        id + "@example.com", LocalDate.of(2000,1,1));
                System.out.println("Added: " + s);
            }
            case "b" -> studentService.listAll().forEach(System.out::println);
            case "c" -> {
                System.out.print("id> ");
                boolean ok = studentService.deactivate(SC.nextLine().trim());
                System.out.println(ok ? "Deactivated" : "Not found");
            }
            default -> System.out.println("unknown");
        }
    }

    private void manageCourses() {
        System.out.println("--- Courses ---");
        System.out.println("a) Add sample course");
        System.out.println("b) List all");
        System.out.print("opt> ");
        switch (SC.nextLine().trim()) {
            case "a" -> {
                String code = "C" + System.currentTimeMillis()%10000;
                Course c = new Course.Builder(code)
                        .title("Intro to " + code).credits(3)
                        .semester(Semester.FALL).department("CSE").build();
                courseService.addCourse(c);
                System.out.println("Added: " + c);
            }
            case "b" -> courseService.listAll().forEach(System.out::println);
            default -> System.out.println("unknown");
        }
    }

    private void manageEnrollment() {
        System.out.println("--- Enrollment ---");
        System.out.println("a) Enroll student in course");
        System.out.println("b) Record marks");
        System.out.println("c) Print transcript (GPA)");
        System.out.print("opt> ");
        switch (SC.nextLine().trim()) {
            case "a" -> {
                System.out.print("studentId> "); String sid = SC.nextLine().trim();
                System.out.print("courseCode> "); String ccode = SC.nextLine().trim();
                Optional<Student> os = studentService.findById(sid);
                Optional<Course> oc = courseService.findByCode(ccode);
                if (os.isEmpty() || oc.isEmpty()) { System.out.println("Not found"); return; }
                try {
                    Enrollment e = enrollmentService.enroll(os.get(), oc.get());
                    System.out.println("Enrolled: " + e);
                } catch (Exception ex) { System.out.println("Failed: " + ex.getMessage()); }
            }
            case "b" -> {
                System.out.print("enrollmentId> "); String eid = SC.nextLine().trim();
                System.out.print("marks> "); double marks = Double.parseDouble(SC.nextLine().trim());
                boolean res = enrollmentService.recordMarks(eid, marks);
                System.out.println(res ? "Recorded" : "Enrollment not found");
            }
            case "c" -> {
                System.out.print("studentId> "); String sid = SC.nextLine().trim();
                Optional<Student> st = studentService.findById(sid);
                if (st.isEmpty()) { System.out.println("not found"); return; }
                Student s = st.get();
                System.out.println("Transcript for " + s.getFullName());
                s.getEnrollments().forEach(System.out::println);
                double gpa = enrollmentService.computeGPA(s);
                System.out.printf("GPA: %.2f%n", gpa);
            }
            default -> System.out.println("unknown");
        }
    }

    private void importExport() {
        System.out.println("--- Import/Export ---");
        System.out.println("a) Export all to data folder");
        System.out.print("opt> ");
        if ("a".equals(SC.nextLine().trim())) {
            try { System.out.println("Exported to: " + ioService.exportAll().toAbsolutePath()); }
            catch (Exception ex) { System.out.println("Export failed: " + ex.getMessage()); }
        }
    }

    private void backupAndReports() {
        System.out.println("--- Backup & Reports ---");
        System.out.println("a) Run backup");
        System.out.println("b) Show backup size (recursive)");
        System.out.print("opt> ");
        try {
            switch (SC.nextLine().trim()) {
                case "a" -> {
                    Path dest = backupService.backupFolder(cfg.getDataFolder());
                    System.out.println("Backup created: " + dest.toAbsolutePath());
                }
                case "b" -> {
                    Path backups = cfg.getDataFolder().resolve("backups");
                    if (!backups.toFile().exists()) { System.out.println("No backups"); return; }
                    long size = backupService.computeSizeRecursive(backups);
                    System.out.println("Total backup size bytes: " + size);
                }
                default -> System.out.println("unknown");
            }
        } catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
    }

    private void seedSampleData() {
        Instructor i = new Instructor("I100", "Prof. Alice", "alice@uni.edu", "CSE");
        ds.getInstructors().put(i.getId(), i);

        Course c1 = new Course.Builder("C101").title("Data Structures").credits(4)
                .semester(Semester.FALL).department("CSE").instructor(i).build();
        Course c2 = new Course.Builder("C102").title("Algorithms").credits(4)
                .semester(Semester.FALL).department("CSE").instructor(i).build();
        ds.getCourses().put(c1.getCode(), c1);
        ds.getCourses().put(c2.getCode(), c2);

        Student s1 = new Student("S101", "REG101", "Akshat Shukla",
                "akshat@example.com", LocalDate.of(2002,6,1));
        ds.getStudents().put(s1.getId(), s1);
    }
}
