package ch.bittime.bittime.login.repository;

import ch.bittime.bittime.login.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    /**
     * @author Dominic
     */
    @Repository
    public interface TimeRecordRepo extends JpaRepository<TimeRecord, Integer> {


    }
