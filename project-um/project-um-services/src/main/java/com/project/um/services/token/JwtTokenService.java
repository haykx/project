package com.project.um.services.token;

import com.project.um.reg.PublisherPrincipal;
import com.project.um.repositories.PublisherRepository;
import com.project.um.services.exceptions.NotFoundException;
import com.project.um.services.publisher.PublisherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {
  private final JwtUtil jwtUtil;
  private final PublisherRepository repository;
  private final PublisherMapper mapper;

  @Override
  public Map<String, String> generateTokens(String email) {
    return jwtUtil.generateToken(this.getPrincipal(email));
  }

  private PublisherPrincipal getPrincipal(String email) {
    return this.mapper.toPrincipal(this.repository.findByEmailAndDeletedIsNull(email).orElseThrow(()->new NotFoundException(email)));
  }
}
