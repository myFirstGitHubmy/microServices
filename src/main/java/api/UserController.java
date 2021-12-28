package api;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import shared.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.allUsers();
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        UserDto savedUser = userService.saveUser(user);
        return new ResponseEntity<UserDto>(savedUser, HttpStatus.OK);
    }
}
