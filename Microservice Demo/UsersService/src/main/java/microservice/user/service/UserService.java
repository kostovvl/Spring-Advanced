package microservice.user.service;

import microservice.user.domian.dto.UserDto;

public interface UserService {

    void seedNewUser(UserDto userDto);

}
