package lest.dev.RecoBook.User.service;

import lest.dev.RecoBook.User.dto.UserDTO;
import lest.dev.RecoBook.User.mapper.UserMapper;
import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO detailUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.map(user);
        }
        return null;

    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        return userMapper.map(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userDTO.setId(id);
            User user = userMapper.map(userDTO);
            return userMapper.map(userRepository.save(user));
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
