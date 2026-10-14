package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.Department;
import in.ac.mitmeerut.clearance.exception.DepartmentNotFoundException;
import in.ac.mitmeerut.clearance.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Create Department
    public Department saveDepartment(Department department) {

        return departmentRepository.save(department);
    }

    // Get all Departments
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    // Get Department by ID
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );
    }

    // Update Department
    public Department updateDepartment(
            Long id,
            Department department) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id
                                )
                        );

        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(
                department.getDescription()
        );

        return departmentRepository.save(existingDepartment);
    }

    // Delete Department
    public void deleteDepartment(Long id) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id
                                )
                        );

        departmentRepository.delete(existingDepartment);
    }
}