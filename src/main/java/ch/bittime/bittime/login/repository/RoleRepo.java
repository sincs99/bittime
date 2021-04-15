package ch.bittime.bittime.login.repository;


import ch.bittime.bittime.login.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepo extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(String role);
}
