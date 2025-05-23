package lest.dev.RecoBook.service;

import lest.dev.RecoBook.entity.User;
import lest.dev.RecoBook.exception.WeakPasswordException;
import lest.dev.RecoBook.repository.UserRepository;
import lest.dev.RecoBook.validatoras.ValidatorStrongPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorStrongPassword validatorStrongPassword;

    public Optional<User> detailUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional;
        }
        return Optional.empty();
    }

    //Criar um bglh pra validar uma senha segura!
    public User createUser(User user) {
        try {
            if (validatorStrongPassword.validate(user.getPassword())) {
                String password = user.getPassword();
                user.setPassword(passwordEncoder.encode(password));
                return userRepository.save(user);
            }
            return User.builder().build();
        } catch (WeakPasswordException ex) {
            throw new WeakPasswordException(ex.getMessage());
        }
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
