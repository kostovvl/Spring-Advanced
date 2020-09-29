package api.gateway.innerSecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private final InnerSecurityController innerSecurityController;

    public Init(InnerSecurityController innerSecurityController) {
        this.innerSecurityController = innerSecurityController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.innerSecurityController.sendKey();
    }
}
