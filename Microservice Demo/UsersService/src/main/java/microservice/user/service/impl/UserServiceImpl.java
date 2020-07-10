package microservice.user.service.impl;

import microservice.user.domian.dto.UserDto;
import microservice.user.domian.entity.UserEntity;
import microservice.user.domian.entity.UserRole;
import microservice.user.repository.UserRepository;
import microservice.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedNewUser(UserDto userDto) {

        UserEntity userEntity = this.mapper.map(userDto, UserEntity.class);

        UserRole user = new UserRole("ROLE_USER", userEntity);

        if (this.userRepository.count() == 0) {
            UserRole admin = new UserRole("ROLE_ADMIN", userEntity);
            userEntity.setRoles(List.of(admin, user));
        }

        userEntity.setRoles(List.of(user));
        userEntity.setActive(true);

        this.userRepository.saveAndFlush(userEntity);

    }
}
