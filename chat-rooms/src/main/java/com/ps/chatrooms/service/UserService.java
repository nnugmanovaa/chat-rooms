package com.ps.chatrooms.service;

import com.ps.chatrooms.model.entity.User;
import com.ps.chatrooms.model.exception.SystemServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User registerUser(String email, String username, String password) throws SystemServiceException;

    List<User> findAll() throws SystemServiceException;

    User findById(Long id) throws SystemServiceException;

    User findByUsername(String username);

    User update(User user) throws SystemServiceException;

    void delete(User user) throws SystemServiceException;

    void delete(Long id) throws SystemServiceException;

}
