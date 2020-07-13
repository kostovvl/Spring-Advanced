package spring.workshop.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.workshop.user.domain.UserDto;
import spring.workshop.user.domain.UserEntity;
import spring.workshop.user.domain.UserRole;
import spring.workshop.user.repository.UserEntityRepository;

import java.util.List;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository userEntityRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void seedNewUser(UserDto userDto) {
        UserEntity userEntity = this.mapper.map(userDto, UserEntity.class);
        UserRole user = new UserRole("ROLE_USER");
        user.setUserEntity(userEntity);
        if (this.userEntityRepository.count() == 0) {
            UserRole admin = new UserRole("ROLE_ADMIN");
            admin.setUserEntity(userEntity);
            userEntity.setRoles(List.of(admin, user));
        }

        userEntity.setRoles(List.of(user));

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        this.userEntityRepository.saveAndFlush(userEntity);
    }
}
