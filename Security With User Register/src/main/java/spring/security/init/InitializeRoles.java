package spring.security.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.security.service.RoleService;

@Component
public class InitializeRoles implements CommandLineRunner {

    private final RoleService roleService;

    public InitializeRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!this.roleService.isEmpty()) {
            return;
        }

        this.roleService.createRoles();

    }
}
