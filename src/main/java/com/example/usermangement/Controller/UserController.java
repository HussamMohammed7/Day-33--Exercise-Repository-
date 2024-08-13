package com.example.usermangement.Controller;


import com.example.usermangement.Model.User;
import com.example.usermangement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor


public class UserController {


    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {


        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsers()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        userService.addUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(
                "User added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(
                "User updated successfully"
        );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.removeUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(

                "User deleted successfully");
    }

    @GetMapping("get/role")
    public ResponseEntity getUserByRole(@RequestParam String role) {

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsersByRole(role)
        );
    }
    @GetMapping("get/email")
    public ResponseEntity getUserByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserByEmail(email)
        );
    }

    @GetMapping("get/age")
    public ResponseEntity getUsersByAge(@RequestParam Integer age) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsersByAge(age)
        );
    }

    @PostMapping("check/")
    public ResponseEntity checkUsernamePassword(@RequestParam String username, @RequestParam String password) {

        boolean result = userService.checkUserNameAndPassword(username,password);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    "Username and password are correct"
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                "Username and password are incorrect"
        );

    }


}
