package in.ac.mitmeerut.clearance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ac.mitmeerut.clearance.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}