package br.sc.senai.controller;

import br.sc.senai.model.User;
import br.sc.senai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value="/sa")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("users/{id}")
    public @ResponseBody
    ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {

        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("users/{id}")
    public @ResponseBody
    ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

        Optional<User> userData = Optional.ofNullable(userRepository.findById(id));

        if (userData.isPresent()) {
            User updatedUser = userData.get();
            updatedUser.setName(user.getName());
            updatedUser.setTelephone(user.getTelephone());
            return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}