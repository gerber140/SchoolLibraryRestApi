package pl.kurs.schoollibraryrestapi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schoollibraryrestapi.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
