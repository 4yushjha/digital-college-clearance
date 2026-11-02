package in.ac.mitmeerut.clearance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ac.mitmeerut.clearance.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUser_Id(Long userId);

}