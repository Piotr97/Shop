package pl.netpaper.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.netpaper.shop.mapper.UserMapper;
import pl.netpaper.shop.model.dto.UserDto;
import pl.netpaper.shop.model.dao.User;
import pl.netpaper.shop.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDTO) {
        User save = userService.save(userMapper.userDtoToUser(userDTO));
        return userMapper.userToUserDto(save);
    }

    @PreAuthorize("hasRole('ADMIN') || @securityServiceImpl.hasAccessToUser(#id)") // nazwa beana z malej litery, # - zwracanie się do parametru funkcji
    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        User userById = userService.findById(id);
        return userMapper.userToUserDto(userById);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<UserDto> getPage(@RequestParam int page, @RequestParam int size) {
        Page<User> users = userService.getPage(PageRequest.of(page, size));
        return users.map(userMapper::userToUserDto);
    }

}
