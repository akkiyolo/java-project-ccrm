# Campus Course & Records Manager (CCRM)

A console-based Java SE application to manage **students, courses, enrollment, grades, transcripts, and file utilities** for a campus.  
This project demonstrates Java OOP principles, modern APIs (NIO.2, Streams, Date/Time), design patterns (Singleton, Builder), recursion, enums, exceptions, and CLI workflows.

---

<img width="1024" height="1024" alt="Gemini_Generated_Image_2vqa362vqa362vqa" src="https://github.com/user-attachments/assets/5bdddd76-60c4-4c6e-bc6b-7ac392f58ac9" />


## 🚀 How to Run

### Requirements
- Java SE 17+ (works with Java 11+)
- Any IDE (Eclipse, IntelliJ) or CLI

### Compile & Run (CLI)
```bash
javac -d out $(find src -name "*.java")
java -cp out edu.ccrm.cli.MainMenu

```
---
### Eclipse

- File → New → Java Project → Name it ccrm.

- Copy src/edu/ccrm/... into src.

- Right-click MainMenu.java → Run As → Java Application.

---

## 📜 Evolution of Java

1995: Java 1.0 released ("Write Once, Run Anywhere").

2004: Java 5 introduced generics, enums, annotations.

2011: Java 7 with try-with-resources, NIO.2.

2014: Java 8 with lambdas, streams, Date/Time API.

2017: Java 9 modules, JShell.

2019–present: Java 11/17 LTS; new features every 6 months.

---

## 🖥️ Java ME vs SE vs EE

| Aspect      | Java ME (Micro)         | Java SE (Standard)  | Java EE (Enterprise)          |
| ----------- | ----------------------- | ------------------- | ----------------------------- |
| Target      | Mobile/embedded devices | Desktop & CLI apps  | Large-scale enterprise apps   |
| APIs        | Limited (CLDC/MIDP)     | Full core libraries | Adds servlets, EJB, JPA, etc. |
| Example Use | Feature phones          | Console apps, tools | Banking, e-commerce           |

---

## ⚙️ Java Architecture

JDK (Java Development Kit) → compiler (javac), debugger, runtime tools.

JRE (Java Runtime Environment) → runs programs (JVM + libraries).

JVM (Java Virtual Machine) → executes .class bytecode.

Flow: Source (.java) → Bytecode (.class, JDK) → JVM (JRE executes)

---

## 📥 Install Java on Windows

Download JDK from Oracle or Adoptium

Install and configure JAVA_HOME + add to PATH.

Verify:
```bash
java -version
```
---

## 📦 Eclipse Setup

Open Eclipse → File → New → Java Project.

Create packages edu.ccrm.cli, edu.ccrm.domain, etc.

Add source files.

Run MainMenu → console output.

---

## 📑 Features

Students: add, list, update, deactivate.

Courses: add, list, search, filter, assign instructor.

Enrollment & Grades: enroll/unenroll, record marks, compute GPA.

Transcripts: print student transcripts with GPA.

Import/Export: CSV-based, via NIO.2 + Streams.

Backup: timestamped folder copy + recursive size computation.

CLI Menu: switch/loops, labeled breaks, jump control.

---

## 🗂️ Project Structure

```bash
src/edu/ccrm/
 ├─ cli/            # MainMenu CLI
 ├─ config/         # AppConfig (Singleton), DataStore
 ├─ domain/         # Person, Student, Instructor, Course (Builder), Enrollment, Grade, Semester
 ├─ service/        # StudentService, CourseService, EnrollmentService
 ├─ io/             # ImportExportService, BackupService
 ├─ util/           # Validators, RecursionUtils
 └─ exception/      # Custom exceptions
```
---

## 📊 Mapping: Requirement → Code

| Requirement                          | Implementation                                                    |
| ------------------------------------ | ----------------------------------------------------------------- |
| Encapsulation                        | `Student`, `Course` with private fields/getters                   |
| Inheritance                          | `Person → Student/Instructor`                                     |
| Abstraction                          | `Person` abstract class                                           |
| Polymorphism                         | `profileSummary()`, `toString()` overrides                        |
| Enum (Semester, Grade)               | `domain/Semester.java`, `domain/Grade.java`                       |
| Singleton                            | `AppConfig`, `DataStore`                                          |
| Builder                              | `Course.Builder`                                                  |
| Exceptions                           | `DuplicateEnrollmentException`, `MaxCreditLimitExceededException` |
| Streams                              | `CourseService.filter...`, `ImportExportService`                  |
| Date/Time API                        | `Person.createdAt`, `Enrollment.enrolledAt`                       |
| NIO.2                                | `ImportExportService`, `BackupService`                            |
| Recursion                            | `BackupService.computeSizeRecursive`                              |
| CLI + switch + loops + labeled break | `MainMenu`                                                        |
| Arrays/Strings                       | CSV parsing, `split/join`                                         |
| Lambdas                              | `CourseService.filter...`                                         |
| Anonymous inner class                | `BackupService` (FileVisitor)                                     |

---

## 🧪 Assertions

Enable assertions when running:
``` bash
java -ea -cp out edu.ccrm.cli.MainMenu
```
---

## 📜 License & Credits

License: EPL 2.0 — see LICENSE

Author: Akshat Shukla

Credits: Eclipse , Oracle








