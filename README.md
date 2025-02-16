# parent-child-pipe

This Java program demonstrates inter-thread communication using pipes. The parent thread sends a message to the child thread via a pipe, and the child thread modifies the message by changing the case of each character and appending "CHILD" before sending it back.

## How to Run the Program

### **Prerequisites**
- Ensure you have **Java JDK** installed on your system.

### **Steps to Run**

1. **Clone or Download the Repository**
   ```sh
   git clone https://github.com/Sirihegde02/parent-child-pipe.git
   cd parent-child-pipe
   ```

2. **Save the Java file** as `parent_child_pipe.java`.

3. **Open a Terminal in VS Code** (or any terminal) and navigate to the file location.

4. **Compile the Java file** using:
   ```sh
   javac parent_child_pipe.java
   ```
   This will generate a `PipeCommunication.class` file if the compilation is successful.

5. **Run the Program**
   ```sh
   java PipeCommunication
   ```

### **Expected Output**
```
Parent received: comp 512 PIPE PROGRAMMING PARENT CHILD
```

### **How the Program Works**
- The **Parent Thread** sends: `COMP 512 pipe programming parent` to the child.
- The **Child Thread**:
  - Changes the case of each character (uppercase → lowercase, lowercase → uppercase).
  - Appends `CHILD` at the end.
  - Sends the modified message back to the parent.
- The **Parent Thread** prints the received modified message.

### **Troubleshooting**
- **If `javac` or `java` commands are not recognized**, ensure Java is properly installed and configured in your system's PATH.
- **Compilation errors?** Ensure the filename matches `parent_child_pipe.java` exactly.

---
**Author:** Siri Hegde
