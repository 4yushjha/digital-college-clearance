package in.ac.mitmeerut.clearance.repository;

import in.ac.mitmeerut.clearance.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}