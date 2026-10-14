package in.ac.mitmeerut.clearance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.ac.mitmeerut.clearance.entity.ClearanceAuthority;
import in.ac.mitmeerut.clearance.exception.ClearanceAuthorityNotFoundException;
import in.ac.mitmeerut.clearance.repository.ClearanceAuthorityRepository;

@Service
public class ClearanceAuthorityService {

    private final ClearanceAuthorityRepository
            clearanceAuthorityRepository;

    public ClearanceAuthorityService(
            ClearanceAuthorityRepository clearanceAuthorityRepository) {

        this.clearanceAuthorityRepository =
                clearanceAuthorityRepository;
    }

    // Create Clearance Authority
    public ClearanceAuthority saveClearanceAuthority(
            ClearanceAuthority authority) {

        return clearanceAuthorityRepository.save(authority);
    }

    // Get all Clearance Authorities
    public List<ClearanceAuthority> getAllClearanceAuthorities() {

        return clearanceAuthorityRepository.findAll();
    }

    // Get Clearance Authority by ID
    public ClearanceAuthority getClearanceAuthorityById(Long id) {

        return clearanceAuthorityRepository.findById(id)
                .orElseThrow(() ->
                        new ClearanceAuthorityNotFoundException(
                                "Clearance Authority not found with id: "
                                        + id
                        )
                );
    }

    // Update Clearance Authority
    public ClearanceAuthority updateClearanceAuthority(
            Long id,
            ClearanceAuthority authority) {

        ClearanceAuthority existingAuthority =
                clearanceAuthorityRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceAuthorityNotFoundException(
                                        "Clearance Authority not found with id: "
                                                + id
                                )
                        );

        existingAuthority.setName(authority.getName());
        existingAuthority.setDescription(
                authority.getDescription()
        );

        return clearanceAuthorityRepository.save(
                existingAuthority
        );
    }

    // Delete Clearance Authority
    public void deleteClearanceAuthority(Long id) {

        ClearanceAuthority existingAuthority =
                clearanceAuthorityRepository.findById(id)
                        .orElseThrow(() ->
                                new ClearanceAuthorityNotFoundException(
                                        "Clearance Authority not found with id: "
                                                + id
                                )
                        );

        clearanceAuthorityRepository.delete(existingAuthority);
    }
}