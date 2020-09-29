package api.userservice.service;

import api.userservice.domain.UserEntity;
import api.userservice.domain.UserEntityRole;
import api.userservice.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username).orElse(null);
    }

    public UserEntity register(UserEntity userEntity) {
        UserEntityRole roleUser = new UserEntityRole();
        roleUser.setRole("USER");
        roleUser.setUser(userEntity);

        userEntity.setRoles(Set.of(roleUser));
        userEntity.setRegisteredOn(LocalDateTime.now());
        return this.userEntityRepository.saveAndFlush(userEntity);
    }
}
