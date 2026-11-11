package com.rest.api.controller;

import com.rest.api.model.User;
import com.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private static final Logger logger =
          LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<User> getAllUsers() {
      if (logger.isInfoEnabled()) logger.info("Fetching all users");
      return userRepository.findAll();

  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    logger.info("Fetching user with ID: {}", id);
    Optional<User> user = userRepository.findById(id);
    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    logger.info("Creating a new user");
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      User existingUser = user.get();
      existingUser.setName(userDetails.getName());
      existingUser.setEmail(userDetails.getEmail());
      User updatedUser = userRepository.save(existingUser);
      return ResponseEntity.ok(updatedUser);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    logger.info("Deleting user with ID: {}", id);
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.delete(user.get());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
