package ch.bittime.bittime.login;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timerecord") //MySQL Data Table
/**
 * @author Dominic
 */

public class TimeRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "timeRecord_id")
    private int id;


    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "date")
    private Date date;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "starttime")
    private Date starttime;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "endtime")
    private Date endtime;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "startbreak")
    private Date startbreak;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name = "endbreak")
    private Date endbreak;

    @Column(name = "time")
    private String time;


}

