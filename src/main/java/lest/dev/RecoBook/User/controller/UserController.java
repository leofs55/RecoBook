package lest.dev.RecoBook.User.controller;

import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/details/{id}")
    public ResponseEntity<User> showById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.detailUser(id).orElse(null));
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deletado!");
    }

    @GetMapping("/generate_book/{id}")
    public ResponseEntity<String>  generateBook(@PathVariable Long id, @RequestBody User body){
        return ResponseEntity.ok("");
    }


}
