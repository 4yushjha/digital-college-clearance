package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.Student;
import in.ac.mitmeerut.clearance.entity.User;
import in.ac.mitmeerut.clearance.repository.StudentRepository;
import in.ac.mitmeerut.clearance.repository.UserRepository;
import in.ac.mitmeerut.clearance.exception.StudentNotFoundException;
import in.ac.mitmeerut.clearance.exception.UserNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(
            StudentRepository studentRepository,
            UserRepository userRepository) {

        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    // Create a new student
    public Student saveStudent(Student student) {

        if (student.getUser() != null
                && student.getUser().getId() != null) {

            Long userId = student.getUser().getId();

            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with ID: " + userId
                            )
                    );

            student.setUser(user);
        }

        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    // Get a student by student ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id
                        )
                );
    }

    // Get a student using the associated user ID
    public Student getStudentByUserId(Long userId) {

        return studentRepository.findByUser_Id(userId)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found for user ID: " + userId
                        )
                );
    }

    // Update a student
    public Student updateStudent(Long id, Student studentDetails) {

        Student existingStudent = getStudentById(id);

        studentDetails.setId(existingStudent.getId());

        if (studentDetails.getUser() != null
                && studentDetails.getUser().getId() != null) {

            Long userId = studentDetails.getUser().getId();

            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with ID: " + userId
                            )
                    );

            studentDetails.setUser(user);
        } else {
            studentDetails.setUser(existingStudent.getUser());
        }

        return studentRepository.save(studentDetails);
    }

    // Delete a student
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}