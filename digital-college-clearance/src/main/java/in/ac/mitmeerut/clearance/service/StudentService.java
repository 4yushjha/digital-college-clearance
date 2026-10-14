package in.ac.mitmeerut.clearance.service;

import in.ac.mitmeerut.clearance.entity.Student;
import in.ac.mitmeerut.clearance.entity.User;
import in.ac.mitmeerut.clearance.exception.StudentNotFoundException;
import in.ac.mitmeerut.clearance.exception.UserNotFoundException;
import in.ac.mitmeerut.clearance.repository.StudentRepository;
import in.ac.mitmeerut.clearance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Student saveStudent(Student student) {

        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: "
                                        + student.getUser().getId()
                        )
                );

        student.setUser(user);

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );
    }

    public Student updateStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        existingStudent.setRollNumber(student.getRollNumber());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setBranch(student.getBranch());
        existingStudent.setSemester(student.getSemester());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(existingStudent);
    }
}