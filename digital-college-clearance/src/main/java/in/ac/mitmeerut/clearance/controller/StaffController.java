package in.ac.mitmeerut.clearance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.ac.mitmeerut.clearance.entity.Staff;
import in.ac.mitmeerut.clearance.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Create Staff
    @PostMapping
    public ResponseEntity<Staff> createStaff(
            @RequestBody Staff staff) {

        Staff savedStaff = staffService.saveStaff(staff);

        return new ResponseEntity<>(
                savedStaff,
                HttpStatus.CREATED
        );
    }

    // Get all Staff
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {

        return ResponseEntity.ok(
                staffService.getAllStaff()
        );
    }

    // Get Staff by ID
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                staffService.getStaffById(id)
        );
    }

    // Update Staff
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable Long id,
            @RequestBody Staff staff) {

        return ResponseEntity.ok(
                staffService.updateStaff(id, staff)
        );
    }

    // Delete Staff
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(
            @PathVariable Long id) {

        staffService.deleteStaff(id);

        return ResponseEntity.ok(
                "Staff deleted successfully"
        );
    }
}