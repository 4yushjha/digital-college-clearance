package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.ClearanceRequest;
import in.ac.mitmeerut.clearance.entity.Student;
import in.ac.mitmeerut.clearance.exception.ClearanceRequestNotFoundException;
import in.ac.mitmeerut.clearance.exception.StudentNotFoundException;
import in.ac.mitmeerut.clearance.repository.ClearanceRequestRepository;
import in.ac.mitmeerut.clearance.repository.StudentRepository;

@Service
public class ClearanceRequestService {

    private final ClearanceRequestRepository clearanceRequestRepository;
    private final StudentRepository studentRepository;

    public ClearanceRequestService(
            ClearanceRequestRepository clearanceRequestRepository,
            StudentRepository studentRepository) {

        this.clearanceRequestRepository = clearanceRequestRepository;
        this.studentRepository = studentRepository;
    }

    // CREATE
    public ClearanceRequest saveClearanceRequest(
            ClearanceRequest clearanceRequest) {

        Student student = studentRepository
                .findById(clearanceRequest.getStudent().getId())
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: "
                                        + clearanceRequest.getStudent().getId()
                        )
                );

        clearanceRequest.setStudent(student);

        return clearanceRequestRepository.save(clearanceRequest);
    }

    // GET ALL
    public List<ClearanceRequest> getAllClearanceRequests() {

        return clearanceRequestRepository.findAll();
    }

    // GET BY ID
    public ClearanceRequest getClearanceRequestById(Long id) {

        return clearanceRequestRepository.findById(id)
                .orElseThrow(() ->
                        new ClearanceRequestNotFoundException(
                                "Clearance request not found with id: " + id
                        )
                );
    }

    // UPDATE
    public ClearanceRequest updateClearanceRequest(
            Long id,
            ClearanceRequest clearanceRequest) {

        ClearanceRequest existingRequest =
                clearanceRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceRequestNotFoundException(
                                        "Clearance request not found with id: "
                                                + id
                                )
                        );

        existingRequest.setOverallStatus(
                clearanceRequest.getOverallStatus()
        );

        existingRequest.setCompletionDate(
                clearanceRequest.getCompletionDate()
        );

        return clearanceRequestRepository.save(existingRequest);
    }

    // DELETE
    public void deleteClearanceRequest(Long id) {

        ClearanceRequest existingRequest =
                clearanceRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceRequestNotFoundException(
                                        "Clearance request not found with id: "
                                                + id
                                )
                        );

        clearanceRequestRepository.delete(existingRequest);
    }
}