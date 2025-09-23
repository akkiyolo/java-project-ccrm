# Campus Course & Records Manager (CCRM)

A console-based Java SE application to manage **students, courses, enrollment, grades, transcripts, and file utilities** for a campus.  
This project demonstrates Java OOP principles, modern APIs (NIO.2, Streams, Date/Time), design patterns (Singleton, Builder), recursion, enums, exceptions, and CLI workflows.

---

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


