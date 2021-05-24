package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pascal + Robin
 */

@Repository

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%"
            + " OR u.email LIKE %?1%"
            + " OR u.lastName LIKE %?1%")



    List<User> findByKeyword(@Param("searchString") String searchString);

    User findByEmail(String email);

    User findByUserName(String userName);



}



