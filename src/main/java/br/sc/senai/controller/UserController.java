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
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
        try {
            Iterable<User> users = userRepository.findAll();
            if (((Collection<?>) users).size() == 0) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @DeleteMapping("users/{id}")
    public @ResponseBody
    ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {

        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("users/{id}")
    public @ResponseBody ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {

        Optional<User> userData = userRepository.findById(id);

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