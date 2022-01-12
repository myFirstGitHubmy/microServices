package com.services;

import com.domain.Role;
import com.domain.User;
import com.repositories.RoleRepo;
import com.repositories.UserRepo;
import com.shared.utils.DataUtil;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shared.dto.UserDto;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleService roleService;

    private final DataUtil dataUtil = new DataUtil();

    public List<UserDto> allUsers() {
        return userRepo.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        Optional<User> user = userRepo.getUserById(id);
        if (user.isPresent()) return userRepo.getUserById(id).get().toDto();
        return null;
    }

    public UserDto findUserAuth(String login, String password){
        String pass = Base64.getEncoder().encodeToString(password.getBytes());
        User user = userRepo.findUserByLoginAndPassword(login, pass);
        if (user != null){
            return Optional.of(user.toDto()).get();
        }
        return null;
    }

    public UserDto createUser(User user){
        User saveUser = new User();
            saveUser.setName(user.getName());
            saveUser.setLogin(user.getLogin());
//            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_8));
            saveUser.setPassword(password);
            saveUser.setUuid(UUID.randomUUID().toString());
            saveUser.setEmail(user.getEmail());
            saveUser.setCreateDate(new DateTime().toDate());
            saveUser.setRoles(user.getRoles());
       return userRepo.save(saveUser).toDto();
    }

    public UserDto update(User user){
        return userRepo.save(user).toDto();
    }
}
