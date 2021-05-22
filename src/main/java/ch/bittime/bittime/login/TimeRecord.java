package ch.bittime.bittime.login;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dominic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timerecord") //MySQL Data Table
public class TimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timeRecord_id")
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "starttime")
    private Date starttime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "endtime")
    private Date endtime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "startbreak")
    private Date startbreak;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "endbreak")
    private Date endbreak;

    @Column(name = "time")
    private String time;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;

    public int totalWorkTimeWithoutBreaksInMinutes() {
        long result = ((endtime.getTime() - starttime.getTime()) - (endbreak.getTime() - startbreak.getTime())) / 1000 / (60);

        return (int) result;
    }
}

