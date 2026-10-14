package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.Staff;
import in.ac.mitmeerut.clearance.entity.User;
import in.ac.mitmeerut.clearance.exception.StaffNotFoundException;
import in.ac.mitmeerut.clearance.exception.UserNotFoundException;
import in.ac.mitmeerut.clearance.repository.StaffRepository;
import in.ac.mitmeerut.clearance.repository.UserRepository;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;

    public StaffService(
            StaffRepository staffRepository,
            UserRepository userRepository) {

        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
    }

    // Create Staff
    public Staff saveStaff(Staff staff) {

        User user = userRepository.findById(staff.getUser().getId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: "
                                        + staff.getUser().getId()
                        )
                );

        staff.setUser(user);

        return staffRepository.save(staff);
    }

    // Get all Staff
    public List<Staff> getAllStaff() {

        return staffRepository.findAll();
    }

    // Get Staff by ID
    public Staff getStaffById(Long id) {

        return staffRepository.findById(id)
                .orElseThrow(() ->
                        new StaffNotFoundException(
                                "Staff not found with id: " + id
                        )
                );
    }

    // Update Staff
    public Staff updateStaff(Long id, Staff staff) {

        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() ->
                        new StaffNotFoundException(
                                "Staff not found with id: " + id
                        )
                );

        existingStaff.setEmployeeId(staff.getEmployeeId());
        existingStaff.setDesignation(staff.getDesignation());
        existingStaff.setStaffType(staff.getStaffType());
        existingStaff.setDepartment(staff.getDepartment());

        return staffRepository.save(existingStaff);
    }

    // Delete Staff
    public void deleteStaff(Long id) {

        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() ->
                        new StaffNotFoundException(
                                "Staff not found with id: " + id
                        )
                );

        staffRepository.delete(existingStaff);
    }
}