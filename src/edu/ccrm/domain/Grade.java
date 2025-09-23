package edu.ccrm.domain;

/**
 * Grade enum with grade points.
 */
public enum Grade {
    S(10), A(9), B(8), C(7), D(6), E(5), F(0);

    private final double points;
    Grade(double points) { this.points = points; }
    public double getPoints() { return points; }

    public static Grade fromMarks(double marks) {
        if (marks >= 90) return S;
        if (marks >= 80) return A;
        if (marks >= 70) return B;
        if (marks >= 60) return C;
        if (marks >= 50) return D;
        if (marks >= 40) return E;
        return F;
    }
}
