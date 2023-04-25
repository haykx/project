package com.project.services.init;

import com.project.entities.model.Permission;
import com.project.entities.model.Role;
import com.project.entities.model.UmPublisher;
import com.project.repositories.PermissionRepository;
import com.project.repositories.UmPublisherRepository;
import com.project.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitOnLoad implements ApplicationListener<ApplicationStartedEvent> {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UmPublisherRepository umPublisherRepository;
    private final PasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        List<Permission> permissions = new ArrayList<>(){{
                add(new Permission("PUBLISHER_CAN_DO_EVERYTHING"));
        }};

        List<Role> roles = new ArrayList<>(){{
            add(new Role("PUBLISHER", permissionRepository.saveAll(permissions)));
        }};

        UmPublisher publisher = new UmPublisher(
                "hayk@gmail.com",
                encoder.encode("1234"),
                roleRepository.saveAll(roles));

//        publisherRepository.save(publisher);

    }
}
