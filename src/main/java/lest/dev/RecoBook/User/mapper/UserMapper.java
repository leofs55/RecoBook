package lest.dev.RecoBook.User.mapper;

import lest.dev.RecoBook.User.dto.UserDTO;
import lest.dev.RecoBook.User.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setBirth_date(userDTO.getBirth_date());
        user.setBooks(userDTO.getBooks());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setBirth_date(user.getBirth_date());
        userDTO.setBooks(user.getBooks());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}
