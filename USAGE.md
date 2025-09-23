# Campus Course & Records Manager (CCRM) – Usage Guide

This guide walks through how to use the **CLI menus** and test major features like students, courses, enrollment, GPA calculation, import/export, and backups.

---

## ▶️ Starting the Application
Compile and run:
```bash
javac -d out $(find src -name "*.java")
java -cp out edu.ccrm.cli.MainMenu
```
---

## 📋 Main Menu
```bash
=== CCRM Main Menu ===
1) Manage Students
2) Manage Courses
3) Enrollment & Grades
4) Import/Export Data
5) Backup & Reports
6) Exit
Choose>
```
---

## 👨‍🎓 Student Management
- Add a sample student
```bash
opt> a
Added: Student[S123] Student S123 | regNo=REG-S123 | enrolled=[]
```
- List students
```bash
opt> b
Student[S123] Student S123 | regNo=REG-S123 | enrolled=[]
```
- Deactivate a student
```bash
opt> c
id> S123
Deactivated
```
---
## 📚 Course Management
- Add a sample course
```bash
opt> a
Added: C103 | Intro to C103 | credits=3 dept=CSE sem=FALL
```
- List all courses
```bash
opt> b
C101 | Data Structures | credits=4 dept=CSE sem=FALL
C102 | Algorithms | credits=4 dept=CSE sem=FALL
C103 | Intro to C103 | credits=3 dept=CSE sem=FALL
```
---

## 📝 Enrollment & Grades
- Enroll a student in a course
```bash
opt> a
studentId> S101
courseCode> C101
Enrolled: ENR-1000 | Akshat Shukla -> C101 | marks=N/A grade=N/A
```
- Record marks
```bash
opt> b
enrollmentId> ENR-1000
marks> 87
Recorded
```
- Print transcript (with GPA)
```bash
opt> c
studentId> S101
Transcript for Akshat Shukla
ENR-1000 | Akshat Shukla -> C101 | marks=87.00 grade=A
GPA: 9.00
```
---

## 📂 Import/Export
- Export data
```bash
opt> a
Exported to: /home/user/ccrm-data
```
Files created:
students_export.csv
courses_export.csv
enrollments_export.csv

- Import sample data
Place students.csv / courses.csv in test-data/.
Modify ImportExportService.importStudents / importCourses.

---

## 💾 Backup & Reports
- Create backup
```bash
opt> a
Backup created: /home/user/ccrm-data/backups/backup-20250923-120500
```
- Show backup size (recursive)
```bash
opt> b
Total backup size bytes: 134
```
---

## 🧪 Assertions
Run with assertions enabled to verify invariants:
```bash
java -ea -cp out edu.ccrm.cli.MainMenu
```
---

## ✅ Test Data (example CSVs)
students.csv
```bash
S201,REG201,Test Student,test1@example.com,2001-05-20
```
courses.csv
```bash
C201,Intro to Testing,3,CSE,FALL
```
---

