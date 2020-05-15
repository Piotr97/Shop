package pl.netpaper.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.netpaper.shop.model.dao.User;
import pl.netpaper.shop.repository.RoleRepository;
import pl.netpaper.shop.repository.UserRepository;
import pl.netpaper.shop.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor // tworzony konstruktor dla wszystkich pol finalnych w obiekcje
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role))); // tworzy set z jednym elementem
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not exist"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
