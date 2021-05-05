package com.example.demo.security;




import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsersRepository userRepository;

    @Autowired
    public void setUserRepository(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ObjectNotFoundException(Users.class, "User not found [email: " + email + "]")
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Users.class, "User not found [id: " + id + "]")
        );

        return UserPrincipal.create(user);
    }
}
