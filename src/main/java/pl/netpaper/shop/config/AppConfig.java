package pl.netpaper.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.netpaper.shop.model.dao.Role;
import pl.netpaper.shop.repository.RoleRepository;
import pl.netpaper.shop.service.impl.WatcherService;

import java.util.Optional;

@Configuration // klasa konfiguracyjna
@RequiredArgsConstructor

public class AppConfig {



    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository, WatcherService watcherService) {
        return args -> {

            new Thread(watcherService, "th1").start();

            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            if (roleUser.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_USER").build());
            }

            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if (roleAdmin.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


