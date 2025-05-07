package lest.dev.RecoBook.User.controller;

import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Gemini.service.GeminiService;
import lest.dev.RecoBook.User.dto.UserDTO;
import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final GeminiService geminiService;

    @GetMapping("/details/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        UserDTO userDTO = userService.detailUser(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO userAtt = userService.updateUser(id, userDTO);
        if (userAtt != null) {
            return ResponseEntity.ok(userAtt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean resultDeletion = userService.deleteUser(id);
        if (resultDeletion) {
            return ResponseEntity.ok("Deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

//    @GetMapping("/generate_book/{id}")
//    public ResponseEntity<?>  generateBook(@PathVariable Long id){
//        List<Book> books = userService.detailUser(id).getBooks();
//        return ResponseEntity.ok(geminiService.generateRecoBook(books));
//    }


}
