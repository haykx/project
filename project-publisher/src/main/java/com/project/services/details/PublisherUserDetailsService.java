package com.project.services.details;

import com.project.repositories.UmPublisherRepository;
import com.project.services.exceptions.NotFoundException;
import com.project.services.publisher.UmPublisherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherUserDetailsService implements UserDetailsService {

    private final UmPublisherRepository repository;
    private final UmPublisherMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.mapper.toPrincipal(this.repository.findByEmail(username).orElseThrow(() -> new NotFoundException(username)));
    }
}
