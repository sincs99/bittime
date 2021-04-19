package ch.bittime.bittime.login;


import ch.bittime.bittime.login.repository.UserRepo;
import ch.bittime.bittime.login.repository.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;





    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        UserRole userRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        return userRepo.save(user);
    }







}
