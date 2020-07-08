package exam.security.service;

import exam.security.domain.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String name);
    void recordNewUser(UserDto userDto);

}
