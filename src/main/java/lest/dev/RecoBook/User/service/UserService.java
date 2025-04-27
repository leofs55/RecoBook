package lest.dev.RecoBook.User.service;

import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> detailUser(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
