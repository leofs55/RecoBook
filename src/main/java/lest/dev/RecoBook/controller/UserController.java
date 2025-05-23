package lest.dev.RecoBook.controller;

import lest.dev.RecoBook.config.TokenService;
import lest.dev.RecoBook.dto.request.UserLoginRequest;
import lest.dev.RecoBook.dto.request.UserRequest;
import lest.dev.RecoBook.dto.response.UserLoginResponse;
import lest.dev.RecoBook.dto.response.UserResponse;
import lest.dev.RecoBook.entity.User;
import lest.dev.RecoBook.exception.UsernameOrPasswordInvalidException;
import lest.dev.RecoBook.mapper.UserMapper;
import lest.dev.RecoBook.service.GeminiService;
import lest.dev.RecoBook.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final GeminiService geminiService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @GetMapping("/details/{id}")
    public ResponseEntity<UserResponse> showById(@PathVariable Long id) {
        Optional<User> userOp = userService.detailUser(id);

        return userOp
                .map(user -> ResponseEntity.ok(UserMapper.map(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = UserMapper.map(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                UserMapper.map(userService.createUser(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userRequest) {
       try {
           UsernamePasswordAuthenticationToken UsernameAndPassword = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
           Authentication authentication = authenticationManager.authenticate(UsernameAndPassword);

           User user = (U'ser) authentication.getPrincipal();

           return ResponseEntity.ok(UserLoginResponse.builder()
                   .token(tokenService.generateToken(user))
                   .build());
       } catch (BadCredentialsException ex) {
           throw new UsernameOrPasswordInvalidException("Usuário ou senha invalido!");
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User userAtt = userService.updateUser(id, UserMapper.map(userRequest));
        return ResponseEntity.ok(UserMapper.map(userAtt));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean resultDeletion = userService.deleteUser(id);
        if (resultDeletion) {
            return ResponseEntity.ok("Deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

}
