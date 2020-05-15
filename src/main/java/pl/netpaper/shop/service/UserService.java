package pl.netpaper.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.netpaper.shop.model.dao.User;

public interface UserService {

    User save(User user);

    User findById(Long id);

    void deleteById(Long id);

    Page<User> getPage(Pageable pageable);
}
