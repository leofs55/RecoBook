package lest.dev.RecoBook.repository;

import lest.dev.RecoBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
