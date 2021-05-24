package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dominic
 */
@Repository
public interface VacationRepo extends JpaRepository<Vacation, Integer> {

    @Query("SELECT v FROM Vacation v WHERE v.user.name LIKE %?1%"
    //        + " OR v.user.email LIKE %?1%"
            + " OR v.user.lastName LIKE %?1%")
    List<Vacation> findByKeyword(@Param("searchString") String searchString);

}