package in.ac.mitmeerut.clearance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import in.ac.mitmeerut.clearance.enums.ClearanceStatus;

@Entity
@Table(name = "clearance_verifications")
public class ClearanceVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clearance_request_id", nullable = false)
    private ClearanceRequest clearanceRequest;

    @ManyToOne
    @JoinColumn(name = "authority_id", nullable = false)
    private ClearanceAuthority authority;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private Staff verifiedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClearanceStatus status;

    private String remarks;

    private LocalDateTime verifiedDate;

    public ClearanceVerification() {
        this.status = ClearanceStatus.PENDING;
    }
    
 // Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClearanceRequest getClearanceRequest() {
		return clearanceRequest;
	}

	public void setClearanceRequest(ClearanceRequest clearanceRequest) {
		this.clearanceRequest = clearanceRequest;
	}

	public ClearanceAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(ClearanceAuthority authority) {
		this.authority = authority;
	}

	public Staff getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(Staff verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public ClearanceStatus getStatus() {
		return status;
	}

	public void setStatus(ClearanceStatus status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(LocalDateTime verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	

    
    
}