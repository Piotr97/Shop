package pl.netpaper.shop.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.mapper.UserMapper;
import pl.netpaper.shop.model.dto.UserDto;
import pl.netpaper.shop.model.dao.User;

@Component // rejstruje bina w ktorej nie powinno byc logiki biz
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {


    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User userDtoToUser(UserDto userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}


