package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pascal
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByUserName(String userName);
}