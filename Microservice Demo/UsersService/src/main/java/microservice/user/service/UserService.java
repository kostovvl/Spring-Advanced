package microservice.user.service;

import microservice.user.domian.dto.UserDto;

import java.util.List;

public interface UserService {

    void seedNewUser(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(Long Id);

}
