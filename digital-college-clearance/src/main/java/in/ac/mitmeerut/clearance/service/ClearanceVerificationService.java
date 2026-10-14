package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.ClearanceAuthority;
import in.ac.mitmeerut.clearance.entity.ClearanceRequest;
import in.ac.mitmeerut.clearance.entity.ClearanceVerification;
import in.ac.mitmeerut.clearance.entity.Staff;
import in.ac.mitmeerut.clearance.exception.ClearanceAuthorityNotFoundException;
import in.ac.mitmeerut.clearance.exception.ClearanceRequestNotFoundException;
import in.ac.mitmeerut.clearance.exception.ClearanceVerificationNotFoundException;
import in.ac.mitmeerut.clearance.exception.StaffNotFoundException;
import in.ac.mitmeerut.clearance.repository.ClearanceAuthorityRepository;
import in.ac.mitmeerut.clearance.repository.ClearanceRequestRepository;
import in.ac.mitmeerut.clearance.repository.ClearanceVerificationRepository;
import in.ac.mitmeerut.clearance.repository.StaffRepository;

@Service
public class ClearanceVerificationService {

    private final ClearanceVerificationRepository clearanceVerificationRepository;
    private final ClearanceRequestRepository clearanceRequestRepository;
    private final ClearanceAuthorityRepository clearanceAuthorityRepository;
    private final StaffRepository staffRepository;

    public ClearanceVerificationService(
            ClearanceVerificationRepository clearanceVerificationRepository,
            ClearanceRequestRepository clearanceRequestRepository,
            ClearanceAuthorityRepository clearanceAuthorityRepository,
            StaffRepository staffRepository) {

        this.clearanceVerificationRepository = clearanceVerificationRepository;
        this.clearanceRequestRepository = clearanceRequestRepository;
        this.clearanceAuthorityRepository = clearanceAuthorityRepository;
        this.staffRepository = staffRepository;
    }

    // CREATE
    public ClearanceVerification saveClearanceVerification(
            ClearanceVerification verification) {

        ClearanceRequest clearanceRequest =
                clearanceRequestRepository
                        .findById(
                                verification
                                        .getClearanceRequest()
                                        .getId()
                        )
                        .orElseThrow(() ->
                                new ClearanceRequestNotFoundException(
                                        "Clearance request not found with id: "
                                                + verification
                                                        .getClearanceRequest()
                                                        .getId()
                                )
                        );

        ClearanceAuthority authority =
                clearanceAuthorityRepository
                        .findById(
                                verification
                                        .getAuthority()
                                        .getId()
                        )
                        .orElseThrow(() ->
                                new ClearanceAuthorityNotFoundException(
                                        "Clearance authority not found with id: "
                                                + verification
                                                        .getAuthority()
                                                        .getId()
                                )
                        );

        verification.setClearanceRequest(clearanceRequest);
        verification.setAuthority(authority);

        // Staff is optional because a verification can initially be PENDING
        if (verification.getVerifiedBy() != null) {

            Staff staff =
                    staffRepository
                            .findById(
                                    verification
                                            .getVerifiedBy()
                                            .getId()
                            )
                            .orElseThrow(() ->
                                    new StaffNotFoundException(
                                            "Staff not found with id: "
                                                    + verification
                                                            .getVerifiedBy()
                                                            .getId()
                                    )
                            );

            verification.setVerifiedBy(staff);
        }

        return clearanceVerificationRepository.save(verification);
    }

    // GET ALL
    public List<ClearanceVerification> getAllClearanceVerifications() {

        return clearanceVerificationRepository.findAll();
    }

    // GET BY ID
    public ClearanceVerification getClearanceVerificationById(Long id) {

        return clearanceVerificationRepository.findById(id)
                .orElseThrow(() ->
                        new ClearanceVerificationNotFoundException(
                                "Clearance verification not found with id: "
                                        + id
                        )
                );
    }

    // UPDATE
    public ClearanceVerification updateClearanceVerification(
            Long id,
            ClearanceVerification verification) {

        ClearanceVerification existingVerification =
                clearanceVerificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceVerificationNotFoundException(
                                        "Clearance verification not found with id: "
                                                + id
                                )
                        );

        existingVerification.setStatus(
                verification.getStatus()
        );

        existingVerification.setRemarks(
                verification.getRemarks()
        );

        existingVerification.setVerifiedDate(
                verification.getVerifiedDate()
        );

        // Allow staff to be assigned/changed during update
        if (verification.getVerifiedBy() != null) {

            Staff staff =
                    staffRepository
                            .findById(
                                    verification
                                            .getVerifiedBy()
                                            .getId()
                            )
                            .orElseThrow(() ->
                                    new StaffNotFoundException(
                                            "Staff not found with id: "
                                                    + verification
                                                            .getVerifiedBy()
                                                            .getId()
                                    )
                            );

            existingVerification.setVerifiedBy(staff);
        }

        return clearanceVerificationRepository.save(
                existingVerification
        );
    }

    // DELETE
    public void deleteClearanceVerification(Long id) {

        ClearanceVerification existingVerification =
                clearanceVerificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceVerificationNotFoundException(
                                        "Clearance verification not found with id: "
                                                + id
                                )
                        );

        clearanceVerificationRepository.delete(
                existingVerification
        );
    }
}