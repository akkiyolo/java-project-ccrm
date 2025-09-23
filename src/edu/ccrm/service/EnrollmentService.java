package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.exception.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EnrollmentService handles enroll/unenroll, marks, and GPA calculation.
 */
public class EnrollmentService {
    private final DataStore ds = DataStore.getInstance();
    private final AppConfig cfg = AppConfig.getInstance();
    private final AtomicInteger idSeq = new AtomicInteger(1000);

    public Enrollment enroll(Student s, Course c) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        boolean exists = ds.getEnrollments().stream()
                .anyMatch(e -> e.getStudent().getId().equals(s.getId()) && e.getCourse().getCode().equals(c.getCode()));
        if (exists) throw new DuplicateEnrollmentException("Already enrolled");

        int currentCredits = ds.getEnrollments().stream()
                .filter(e -> e.getStudent().getId().equals(s.getId()))
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        if (currentCredits + c.getCredits() > cfg.getMaxCreditsPerSemester()) {
            throw new MaxCreditLimitExceededException("Max credits exceeded");
        }

        Enrollment e = new Enrollment("ENR-" + idSeq.getAndIncrement(), s, c);
        ds.getEnrollments().add(e);
        s.enroll(e);
        return e;
    }

    public boolean unenroll(Student s, Course c) {
        Optional<Enrollment> opt = ds.getEnrollments().stream()
                .filter(en -> en.getStudent().getId().equals(s.getId()) && en.getCourse().getCode().equals(c.getCode()))
                .findFirst();
        if (opt.isPresent()) {
            Enrollment e = opt.get();
            ds.getEnrollments().remove(e);
            s.unenroll(c.getCode());
            return true;
        }
        return false;
    }

    public boolean recordMarks(String enrollmentId, double marks) {
        Optional<Enrollment> opt = ds.getEnrollments().stream()
                .filter(en -> en.getId().equals(enrollmentId)).findFirst();
        if (opt.isEmpty()) return false;
        Enrollment e = opt.get();
        e.setMarks(marks);
        return true;
    }

    public double computeGPA(Student s) {
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (Enrollment e : s.getEnrollments()) {
            if (e.getGrade().isPresent()) {
                Grade g = e.getGrade().get();
                totalPoints += g.getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        if (totalCredits == 0) return 0.0;
        return totalPoints / totalCredits;
    }
}
