package in.ac.mitmeerut.clearance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.ac.mitmeerut.clearance.entity.ClearanceRequest;
import in.ac.mitmeerut.clearance.service.ClearanceRequestService;

@RestController
@RequestMapping("/clearance-requests")
public class ClearanceRequestController {

    private final ClearanceRequestService clearanceRequestService;

    public ClearanceRequestController(
            ClearanceRequestService clearanceRequestService) {

        this.clearanceRequestService = clearanceRequestService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ClearanceRequest> createClearanceRequest(
            @RequestBody ClearanceRequest clearanceRequest) {

        ClearanceRequest savedRequest =
                clearanceRequestService
                        .saveClearanceRequest(clearanceRequest);

        return new ResponseEntity<>(
                savedRequest,
                HttpStatus.CREATED
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ClearanceRequest>>
            getAllClearanceRequests() {

        return ResponseEntity.ok(
                clearanceRequestService.getAllClearanceRequests()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ClearanceRequest>
            getClearanceRequestById(@PathVariable Long id) {

        return ResponseEntity.ok(
                clearanceRequestService
                        .getClearanceRequestById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ClearanceRequest>
            updateClearanceRequest(
                    @PathVariable Long id,
                    @RequestBody ClearanceRequest clearanceRequest) {

        return ResponseEntity.ok(
                clearanceRequestService
                        .updateClearanceRequest(
                                id,
                                clearanceRequest
                        )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClearanceRequest(
            @PathVariable Long id) {

        clearanceRequestService.deleteClearanceRequest(id);

        return ResponseEntity.ok(
                "Clearance request deleted successfully"
        );
    }
}