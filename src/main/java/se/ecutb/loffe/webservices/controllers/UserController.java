package se.ecutb.loffe.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ecutb.loffe.webservices.entities.User;
import se.ecutb.loffe.webservices.services.UserService;

import java.util.List;

// @Controller // MVC med statiska html-sidor

// REST API Endpoints:
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String name, @RequestParam(required = false) boolean sort) {
        //var users = userService.findAll();
        //List<User> users = userService.findAll();
        //var users : List<User> = userService.findAll();
        //return new ResponseEntity<>(users, HttpStatus.OK);
        return ResponseEntity.ok(userService.findAll(name, sort));
        //return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    /* Detta är ett sätt att lösa det på:
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
     */

    // Detta är ett annat:
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUSer(@PathVariable String id, @RequestBody User user) {
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
