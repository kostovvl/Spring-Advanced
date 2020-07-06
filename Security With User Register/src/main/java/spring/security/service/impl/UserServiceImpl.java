package spring.security.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.security.repository.UserEntityRepository;
import spring.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserEntityRepository userEntityRepository, ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean userExists(String username) {
        return this.userEntityRepository.findByUsername(username).orElse(null) != null;
    }
}
