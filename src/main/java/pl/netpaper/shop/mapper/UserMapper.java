package pl.netpaper.shop.mapper;

import pl.netpaper.shop.model.dto.UserDto;
import pl.netpaper.shop.model.dao.User;

public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDTO);

}
