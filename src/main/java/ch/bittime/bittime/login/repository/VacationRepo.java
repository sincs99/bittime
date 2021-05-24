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



}