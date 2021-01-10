package com.ps.chatrooms.service.impl;

import com.ps.chatrooms.model.entity.User;
import com.ps.chatrooms.model.errors.ErrorCode;
import com.ps.chatrooms.model.exception.SystemServiceException;
import com.ps.chatrooms.repository.UserRepository;
import com.ps.chatrooms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(String email, String username, String password) throws SystemServiceException {
        if(userRepository.findUserByUsernameAndActiveIsTrue(username).isPresent()){
            throw SystemServiceException.builder()
                    .errorCode(ErrorCode.USERNAME_ALREADY_EXISTS)
                    .message("User with such username already exists")
                    .build();

        }
        return this.userRepository.save(User.builder()
                .password(bCryptPasswordEncoder.encode(password))
                .username(username)
                .email(email)
                .build());
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAllByActiveIsTrue();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findByIdAndActiveIsTrue(id).orElseThrow(() ->
                SystemServiceException.builder()
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .message("User with  id " + id + " not found")
                        .build()
        );
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsernameAndActiveIsTrue(username).orElseThrow(() ->
                SystemServiceException.builder()
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .message("User with  username " + username + " not found")
                        .build()
        );
    }

    @Override
    public User update(User user) throws SystemServiceException {
        if (Objects.nonNull(user.getId())) {
            return userRepository.save(user);
        }

        throw SystemServiceException.builder()
                .message("entity not found")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build();
    }

    @Override
    public void delete(User user) throws SystemServiceException {
        user.setActive(false);
        update(user);
    }

    @Override
    public void delete(Long id) throws SystemServiceException {
        User user = findById(id);
        delete(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findUserByUsernameAndActiveIsTrue(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), new ArrayList<>());
        } else {
            return null;
        }
    }
}
