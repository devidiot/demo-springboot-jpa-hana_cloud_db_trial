package org.starj.boot.jpa.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starj.boot.jpa.demo.model.User;
import org.starj.boot.jpa.demo.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // GET ALL
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userService.findAll();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }

    // PUT
    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> putUser(@RequestBody User user)
            throws IllegalArgumentException, IllegalAccessException {
        userService.updateById(user.getUid(), user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // GET ONE
    @GetMapping(value = "/{uid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> getUser(@PathVariable("uid") String uid) {
        User user = userService.findById(uid);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // PUT
    @PutMapping(value = "/{uid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> putUser(@PathVariable("uid") String uid, @RequestBody User user) {
        try {
            user.setUid(uid);
            userService.updateById(uid, user);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PATCH (json-path)
    @PatchMapping(value = "/{uid}", consumes = "application/json-patch+json", produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> patchUser(@PathVariable("uid") String uid, @RequestBody JsonPatch patch) {
        try {
            User user = userService.findById(uid);
            User userPatched = applyPatchToUser(patch, user);
            userService.updateById(uid, userPatched);
            return ResponseEntity.ok(userPatched);
        } catch (JsonPatchException | JsonProcessingException | IllegalArgumentException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PATCH (apply if exists)
    @PatchMapping(value = "/{uid}", consumes = "application/json", produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> patchUser(@PathVariable("uid") String uid, @RequestBody User user) {
        try {
            userService.applyById(uid, user);
            return ResponseEntity.ok(user);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            log.error(e.getLocalizedMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE
    @DeleteMapping(value = "/{uid}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteUser(@PathVariable("uid") String uid) {
        userService.deleteById(uid);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private User applyPatchToUser(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

}
