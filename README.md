# assignment6-visitor-pattern

##
- Name: Assel Yermekkyzy
- Group: SE-2524
- Repository URL: https://github.com/yerassell/assignment6-visitor-pattern.git
- Last commit: 53bc6dd15aad8cc226cb1bf4209c4948cc9cec7b


## Project purpose
The purpose of this assignment was to build an in-memory hierarchical file system with two element types which are FileNode and DirectoryNode


## Package structure
- `src/main/java/org/example/` - contains all source code
- `src/test/java/org/example/` - contains JUnit 5 tests 
- `README.md` - information about assignment
- `pom.xml` - Maven configuration with JUnit 5 dependency


## Build and run instruction
- Run on IntelliJ IDEA
1. Click the green `Code` button and click `Download ZIP`
2. Extract the ZIP
3. Open in IntelliJ IDEA
4. Reload Maven (right-side Maven panel → refresh icon)
5. Run `Main.java` 
6. Run tests `VisitorTest.java`

- Run on terminal (requires Maven installed)
1. Click the green `Code` button and click `Download ZIP`.
2. Extract the ZIP to any folder.
3. Open a terminal:
   ```bash
   cd path/to/extracted/folder
   mvn test
   mvn compile exec:java -Dexec.mainClass=org.example.Main

## Expected Output
``` bash
Total size: 350
Search: [docs/b.txt]
Files: 2
Directories: 3
Max depth: 2
```
