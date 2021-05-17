package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.TimeRecord;
import ch.bittime.bittime.login.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
     * @author Dominic
     */
    @Repository
    public interface TimeRecordRepo extends JpaRepository<TimeRecord, Integer> {
    List<TimeRecord> findAllByUser(User user);
}
