package lest.dev.RecoBook.User.repository;

import lest.dev.RecoBook.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
