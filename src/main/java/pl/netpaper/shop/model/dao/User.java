package pl.netpaper.shop.model.dao;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@EntityListeners(AuditingEntityListener.class) //to włacza adnotacje createdDate i lstmddate
@Entity
@Data //lombok / getery setery, tostring, equ and hashc
@NoArgsConstructor // lombok bez arg konstr
@AllArgsConstructor // -,,- wielo arg
@Builder

@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto ma jedna sekwencje na baze danych czyli tworzac dwie tabele i w kazdej tabeli jeden rekord to pierwszy bedzie mial id 1 ale w drugiej tabeli pierwszy bedzie mial juz id 2
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    @CreatedDate // nie działaja bez adnotacji EntityListeners | ustawiają daty stworzenia objektu
    private LocalDateTime createdDate;
    @LastModifiedDate // ustawiają daty objektu ostatniej mod obj
    private LocalDateTime lastModifyDate;

    @ManyToMany
    private Set<Role> roles;

}
