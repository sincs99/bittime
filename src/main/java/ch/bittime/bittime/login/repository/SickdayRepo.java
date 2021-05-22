package ch.bittime.bittime.login.repository;


import ch.bittime.bittime.login.Sickday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dominic
 */
@Repository
public interface SickdayRepo extends JpaRepository<Sickday, Integer> {
}