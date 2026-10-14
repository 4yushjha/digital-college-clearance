package in.ac.mitmeerut.clearance.repository;

import in.ac.mitmeerut.clearance.entity.ClearanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClearanceRequestRepository extends JpaRepository<ClearanceRequest, Long> {

}