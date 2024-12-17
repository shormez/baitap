package ntt.bai2.services;

import ntt.bai2.entities.Role;
import ntt.bai2.entities.User;
import ntt.bai2.repositories.RoleRepository;
import ntt.bai2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public void registerUser(User user) {
        Role userRole = roleRepository.findByName("USER");
        user.addRole(userRole);
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Role> findRolesByUser(User user) {
        return new ArrayList<>(user.getRoles());

    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
