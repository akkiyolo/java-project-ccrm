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

