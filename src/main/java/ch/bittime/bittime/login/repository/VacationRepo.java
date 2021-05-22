package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dominic
 */
@Repository
public interface VacationRepo extends JpaRepository<Vacation, Integer> {
}