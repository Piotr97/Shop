package pl.netpaper.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.netpaper.shop.model.dao.User;
import pl.netpaper.shop.repository.UserRepository;
import pl.netpaper.shop.service.SecurityService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public boolean hasAccessToUser(Long id) {
        Optional<User> optionalUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return optionalUser.isPresent() && optionalUser.get().getId().equals(id);
    }
}
