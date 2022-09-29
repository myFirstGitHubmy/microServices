package com.api;

import com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.services.UserService;
import com.shared.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    SenderEmail emailSender = new SenderEmail("jekastre@gmail.com","windows27");

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> authUserByLoginAndPass(@RequestBody User user){
        UserDto getUser = userService.findUserAuth(user.getLogin(),user.getPassword());
        return getUser == null ?
                new ResponseEntity<>(null, HttpStatus.FORBIDDEN) : new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        UserDto savedUser = userService.createUser(user);
//        emailSender.send(user.getEmail());
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody User user){
        UserDto getUser = userService.getUserById(user.getId());
        if (getUser != null){
            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<UserDto> logoutUser(@RequestBody User user){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
