package pl.netpaper.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.netpaper.shop.model.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
