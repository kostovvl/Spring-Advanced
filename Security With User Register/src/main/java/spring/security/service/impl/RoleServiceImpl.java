package spring.security.service.impl;

import org.springframework.stereotype.Service;
import spring.security.domain.entity.Role;
import spring.security.repository.RoleRepository;
import spring.security.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRoles() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        this.roleRepository.saveAll(List.of(user, admin));
    }

    @Override
    public boolean isEmpty() {

        return this.roleRepository.count() == 0;
    }
}
