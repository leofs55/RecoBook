package lest.dev.RecoBook.mapper;

import lest.dev.RecoBook.controller.request.UserRequest;
import lest.dev.RecoBook.controller.response.UserResponse;
import lest.dev.RecoBook.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User map(UserRequest userRequest) {
        return User.builder()
                .first_name(userRequest.firstName())
                .last_name(userRequest.lastName())
                .email(userRequest.email())
                .birth_date(userRequest.birthDate())
                .password(userRequest.password()) //Aq irá entra a criptografia
                .build();
    }

    public static UserResponse map(User user) {
        return UserResponse.builder()
                .firstName(user.getFirst_name())
                .lastName(user.getLast_name())
                .email(user.getEmail())
                .birthDate(user.getBirth_date())
                .build();
    }

}
