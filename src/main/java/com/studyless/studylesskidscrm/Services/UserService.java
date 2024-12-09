package com.studyless.studylesskidscrm.Services;


import com.studyless.studylesskidscrm.Models.Role;
import com.studyless.studylesskidscrm.Models.User;
import com.studyless.studylesskidscrm.Repos.IRolesRepos;
import com.studyless.studylesskidscrm.Repos.IUserRepos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    private final IUserRepos userRepos;

    private final IRolesRepos rolesRepos;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(IUserRepos userRepos, IRolesRepos rolesRepos, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepos = userRepos;
        this.rolesRepos = rolesRepos;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Користувача не знайдено");
        }
        return user;
    }

    public User findByEmail(String email) {
        List<User> users = findAll();
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> findAll(){
        return userRepos.findAll();
    }

    public User findUserById(long id) {
        return userRepos.findById(id).orElse(null);
    }

    public boolean saveUser(User user) {
        User userFromDB = findByEmail(user.getEmail());
        if(userFromDB != null) {
            return false;
        }

        user.setRoles(List.of(new Role(1L,"ROLE_TEACHER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepos.save(user);
        return true;
    }

    public boolean deleteUser(User user) {
        User userFromDB = findByEmail(user.getUsername());
        if(userFromDB != null) {
            userRepos.delete(userFromDB);
            return true;
        }
        return false;
    }
}
