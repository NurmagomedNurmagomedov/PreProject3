package space.nurik.preproject3.task_2.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import space.nurik.preproject3.task_2.models.User;
import space.nurik.preproject3.task_2.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> allUsers() {

        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> oneUser(@PathVariable("id") int id) {
        User user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> newUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<HttpStatus> changeUser(@RequestBody @Valid User user, @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User existingUser = userService.findOne(id);

        existingUser.setName(user.getName());
        existingUser.setSurName(user.getSurName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(existingUser.getRoles());


        userService.save(existingUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@PathVariable("id") int id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
