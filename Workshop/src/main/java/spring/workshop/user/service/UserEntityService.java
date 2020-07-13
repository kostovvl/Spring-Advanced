package spring.workshop.user.service;

import org.modelmapper.ModelMapper;
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

    public UserEntityService(UserEntityRepository userEntityRepository, ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
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

        this.userEntityRepository.saveAndFlush(userEntity);
    }
}
