package lest.dev.RecoBook.service;

import lest.dev.RecoBook.entity.User;
import lest.dev.RecoBook.mapper.BookMapper;
import lest.dev.RecoBook.mapper.UserMapper;
import lest.dev.RecoBook.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> detailUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional;
        }
        return Optional.empty();
    }

    //Criar um bglh pra validar uma senha segura!
    public User createUser(User user) {
        ;
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userRepository.save(user);
        }
        return null;

    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
