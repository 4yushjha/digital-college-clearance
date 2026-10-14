package in.ac.mitmeerut.clearance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.ac.mitmeerut.clearance.entity.ClearanceAuthority;
import in.ac.mitmeerut.clearance.service.ClearanceAuthorityService;

@RestController
@RequestMapping("/clearance-authorities")
public class ClearanceAuthorityController {

    private final ClearanceAuthorityService clearanceAuthorityService;

    public ClearanceAuthorityController(
            ClearanceAuthorityService clearanceAuthorityService) {

        this.clearanceAuthorityService = clearanceAuthorityService;
    }

    // Create Clearance Authority
    @PostMapping
    public ResponseEntity<ClearanceAuthority> createClearanceAuthority(
            @RequestBody ClearanceAuthority authority) {

        ClearanceAuthority savedAuthority =
                clearanceAuthorityService.saveClearanceAuthority(authority);

        return new ResponseEntity<>(
                savedAuthority,
                HttpStatus.CREATED
        );
    }

    // Get all Clearance Authorities
    @GetMapping
    public ResponseEntity<List<ClearanceAuthority>>
            getAllClearanceAuthorities() {

        return ResponseEntity.ok(
                clearanceAuthorityService.getAllClearanceAuthorities()
        );
    }

    // Get Clearance Authority by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClearanceAuthority>
            getClearanceAuthorityById(
                    @PathVariable Long id) {

        return ResponseEntity.ok(
                clearanceAuthorityService
                        .getClearanceAuthorityById(id)
        );
    }

    // Update Clearance Authority
    @PutMapping("/{id}")
    public ResponseEntity<ClearanceAuthority>
            updateClearanceAuthority(
                    @PathVariable Long id,
                    @RequestBody ClearanceAuthority authority) {

        return ResponseEntity.ok(
                clearanceAuthorityService
                        .updateClearanceAuthority(id, authority)
        );
    }

    // Delete Clearance Authority
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClearanceAuthority(
            @PathVariable Long id) {

        clearanceAuthorityService
                .deleteClearanceAuthority(id);

        return ResponseEntity.ok(
                "Clearance Authority deleted successfully"
        );
    }
}