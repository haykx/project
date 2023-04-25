package com.project.um.services.publisher;

import com.project.um.entities.model.UmPublisher;
import com.project.um.entities.reg.LoginRequest;
import com.project.um.entities.reg.PublisherPrincipal;
import com.project.um.repositories.PublisherRepository;
import com.project.um.services.exceptions.BadRequestException;
import com.project.um.services.exceptions.NotFoundException;
import com.project.um.services.login.LoginService;
import com.project.um.services.token.JwtTokenService;
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
    private final JwtTokenService jwtTokenService;
    private final PublisherMapper mapper;

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        UmPublisher umPublisher = this.repository.findByEmail(request.getEmail()).orElseThrow(()->new NotFoundException(request.getEmail()));
        if(!encoder.matches(request.getPassword(), umPublisher.getPassword())){
            throw new BadRequestException("Wrong password");
        }
        PublisherPrincipal dto = this.mapper.toPrincipal(umPublisher);
        Map<String, String> tokens = jwtTokenService.generateTokens(dto);
        return new ResponseEntity<>(tokens, OK);
    }

}
