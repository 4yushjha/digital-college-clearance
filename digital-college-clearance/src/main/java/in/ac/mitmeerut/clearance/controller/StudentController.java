package in.ac.mitmeerut.clearance.controller;

import in.ac.mitmeerut.clearance.entity.Student;
import in.ac.mitmeerut.clearance.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );
    }

    // Get all Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    // Get Student by ID
    @GetMapping("/{userId}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                studentService.getStudentById(userId)
        );
    }

    // Update Student
    @PutMapping("/{userId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long userId,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(userId, student)
        );
    }

    // Delete Student
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long userId) {

        studentService.deleteStudent(userId);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }
}