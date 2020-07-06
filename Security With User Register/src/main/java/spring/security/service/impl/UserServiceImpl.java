package spring.security.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.domain.dto.UserEntityDto;
import spring.security.domain.entity.Role;
import spring.security.domain.entity.UserEntity;
import spring.security.repository.RoleRepository;
import spring.security.repository.UserEntityRepository;
import spring.security.service.UserService;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserServiceImpl(UserEntityRepository userEntityRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public boolean userExists(String username) {
        return this.userEntityRepository.findByUsername(username).orElse(null) != null;
    }

    @Transactional
    @Override
    public void registerUser(UserEntityDto userEntityDto) {
        UserEntity userEntity = this.mapper.map(userEntityDto, UserEntity.class);
        if (this.userEntityRepository.count() == 0) {
            Role admin = this.roleRepository.findByName("ROLE_ADMIN");
            admin.addUser(userEntity);
            Role user = this.roleRepository.findByName("ROLE_USER");
            user.addUser(userEntity);
            System.out.println();
            userEntity.setRoles(Set.of(admin, user));
        } else {
            Role user = this.roleRepository.findByName("ROLE_USER");
            user.addUser(userEntity);
            userEntity.setRoles(Set.of(user));
        }

        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        this.userEntityRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntityDto findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username)
                .map(u -> this.mapper.map(u, UserEntityDto.class))
                .orElse(null);
    }
}
