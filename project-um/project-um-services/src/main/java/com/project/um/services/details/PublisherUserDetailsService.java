package com.project.um.services.details;

import com.project.um.repositories.PublisherRepository;
import com.project.um.services.exceptions.NotFoundException;
import com.project.um.services.publisher.PublisherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherUserDetailsService implements UserDetailsService {

    private final PublisherRepository repository;
    private final PublisherMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.mapper.toPrincipal(this.repository.findByEmail(username).orElseThrow(()->new NotFoundException(username)));
    }
}
