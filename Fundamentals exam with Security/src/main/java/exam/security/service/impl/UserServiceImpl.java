package exam.security.service.impl;

import exam.security.domain.dto.UserDto;
import exam.security.domain.entity.UserEntity;
import exam.security.domain.entity.UserRole;
import exam.security.repository.UserEntityRepository;
import exam.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserEntityRepository userEntityRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findByUsername(String name) {
        return this.userEntityRepository.findByUsername(name)
                .map(u -> this.mapper.map(u, UserDto.class))
                .orElse(null);
    }

    @Override
    public void recordNewUser(UserDto userDto) {
        UserEntity newUser = this.mapper.map(userDto, UserEntity.class);

        if (this.userEntityRepository.count() == 0) {
            UserRole admin = new UserRole("ROLE_ADMIN");
            admin.setUser(newUser);
            UserRole user = new UserRole("ROLE_USER");
            user.setUser(newUser);
            newUser.setUserRoles(List.of(admin, user));
        } else {
            UserRole user = new UserRole("ROLE_USER");
            user.setUser(newUser);
            newUser.setUserRoles(List.of(user));
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.userEntityRepository.saveAndFlush(newUser);
    }
}
