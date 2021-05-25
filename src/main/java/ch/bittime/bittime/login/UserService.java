package ch.bittime.bittime.login;


import ch.bittime.bittime.login.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Pascal
 *
 */

@Service
@Transactional

public class UserService {

    @Autowired
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final VacationRepo vacationRepo;
    private final SickdayRepo sickdayRepo;
    private final TimeRecordRepo timeRecordRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, VacationRepo vacationRepo, SickdayRepo sickdayRepo, TimeRecordRepo timeRecordRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.vacationRepo = vacationRepo;
        this.sickdayRepo = sickdayRepo;
        this.timeRecordRepo = timeRecordRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public Optional<User> findUserById(int id) {
        return userRepo.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        UserRole userRole = roleRepo.findByRole("USER");
        user.setRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        return userRepo.save(user);
    }
    /**
     * @author Dominic, Andre
     */
    public boolean matchesPassword(String pw, User user) {
        return bCryptPasswordEncoder.matches(pw, user.getPassword());
    }
    /**
     * @author Dominic, Andre
     */
    public String encodePassword(String pw) {
        return bCryptPasswordEncoder.encode(pw);
    }



    public void deleteUser( int id) {

        userRepo.deleteById(id);

    }


    public void deactivateUser(int id) {
        Optional<User> u = userRepo.findById(id);
        boolean status;
        status = u.get().getActive();

        System.out.println(status);
        u.get().setActive(false);
        status = u.get().getActive();

        System.out.println(status);
    }

    public void activateUser(int id) {
        Optional<User> u = userRepo.findById(id);
        boolean status;
        status = u.get().getActive();

        System.out.println(status);
        u.get().setActive(true);
        status = u.get().getActive();

        System.out.println(status);
    }


    /**
     * @author Robin
     *
     */


    public List<User> findByKeyword(String searchString) {


        return userRepo.findByKeyword(searchString);


    }
}









