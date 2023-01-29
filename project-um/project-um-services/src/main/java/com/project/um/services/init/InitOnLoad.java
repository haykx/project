package com.project.um.services.init;

import com.project.um.entities.Permission;
import com.project.um.entities.Role;
import com.project.um.entities.UmPublisher;
import com.project.um.repositories.PermissionRepository;
import com.project.um.repositories.PublisherRepository;
import com.project.um.repositories.RoleRepository;
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
    private final PublisherRepository publisherRepository;
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

        publisherRepository.save(publisher);

    }
}
