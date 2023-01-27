package com.springboot.myfirsttime.member.service.impl;

import com.springboot.myfirsttime.member.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Username을 통해 UserDetails를 반환하는 Service 클래스
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("[loaduserByUsername] loadUserByUsername 수행. username : {}", username);
        return userRepository.getByUid(username);
    }
}
