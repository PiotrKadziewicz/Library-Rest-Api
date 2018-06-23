package com.library.mapper;

import com.library.domain.User;
import com.library.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getCreationDate(),
                userDto.getAccount(),
                userDto.getBorrowBooks());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getCreationDate(),
                user.getAccount(),
                user.getBorrowBooks());
    }
}
