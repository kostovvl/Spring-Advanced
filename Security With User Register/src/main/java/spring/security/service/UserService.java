package spring.security.service;

import spring.security.domain.dto.UserEntityDto;

public interface UserService {

    boolean userExists(String username);
    void registerUser(UserEntityDto userEntityDto);
    UserEntityDto findByUsername(String username);

}
