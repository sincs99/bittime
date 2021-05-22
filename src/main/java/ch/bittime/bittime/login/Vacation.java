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
@Table(name = "vacation") //MySQL Data Table
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //https://thorben-janssen.com/jpa-generate-primary-keys/
    @Column(name = "vacation_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "accept_state")
    private int acceptState;
}