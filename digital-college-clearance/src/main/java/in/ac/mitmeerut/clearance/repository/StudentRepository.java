package in.ac.mitmeerut.clearance.repository;

import in.ac.mitmeerut.clearance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}