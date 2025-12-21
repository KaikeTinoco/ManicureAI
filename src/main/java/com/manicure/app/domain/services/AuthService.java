package com.manicure.app.domain.services;

import com.manicure.app.domain.dtos.UserRegisterDTO;
import com.manicure.app.domain.entities.User;
import com.manicure.app.domain.exceptions.BadRequestException;
import com.manicure.app.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository repository;


    @Autowired
    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public void registerUser(UserRegisterDTO dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            throw new BadRequestException("Esse login já está em uso");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = User.builder()
                .login(dto.login())
                .password(encryptedPassword)
                .role(dto.role())
                .build();
        repository.save(user);
    }

}
