package ntt.bai2.services.impl;


import ntt.bai2.repositories.UserRepository;
import ntt.bai2.entities.User;
import ntt.bai2.services.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("could not find user");
        }
        return new MyUserDetails(user);

    }
}
