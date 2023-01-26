package com.project.um.services.publisher;

import com.project.um.entities.UmPublisher;
import com.project.um.reg.LoginRequest;
import com.project.um.reg.PublisherPrincipal;
import com.project.um.repositories.PublisherRepository;
import com.project.um.services.login.LoginService;
import com.project.um.services.token.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class PublisherLoginService implements LoginService<LoginRequest> {

    private final PublisherRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final PublisherMapper mapper;

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        UmPublisher umPublisher = this.repository.findByEmailAndDeletedIsNull(request.getEmail()).orElseThrow();
        if(!encoder.matches(request.getPassword(), umPublisher.getPassword())){
            throw new RuntimeException();
        }
        PublisherPrincipal dto = this.mapper.toPrincipal(umPublisher);
        Map<String, String> tokens = jwtUtil.generateToken(dto);
        return new ResponseEntity<>(tokens, OK);
    }

}
