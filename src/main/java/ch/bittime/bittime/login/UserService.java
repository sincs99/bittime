package ch.bittime.bittime.login;


import ch.bittime.bittime.login.repository.RoleRepo;
import ch.bittime.bittime.login.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author Pascal
 * test master
 */
@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
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

    public void deleteUser(int id) {
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

    //todo: edit profile functions
//    public void changeName(int id) {
//        Optional<User> u = userRepo.findById(id);
//        String name = u.get().getName();
//
//        String newName = variable;
//        u.get().setName(newName);
//    }

}









