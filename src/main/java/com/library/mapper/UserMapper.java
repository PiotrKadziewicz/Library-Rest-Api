package com.library.mapper;

import com.library.domain.User;
import com.library.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(u->new UserDto(u.getId(),u.getName(),u.getLastName(),u.getCreationDate(),u.getAccount(),u.getBorrowBooks()))
                .collect(Collectors.toList());

    }

    public List<User> mapToUserList(final List<UserDto> userDtoList){
        return userDtoList.stream()
                .map(u->new User(u.getId(),u.getName(),u.getLastName(),u.getCreationDate(),u.getAccount(),u.getBorrowBooks()))
                .collect(Collectors.toList());

    }
}
