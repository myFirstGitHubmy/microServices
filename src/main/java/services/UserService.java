package services;

import domain.User;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepo;
import shared.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<UserDto> allUsers() {
        return userRepo.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){ return userRepo.getUserById(id).toDto();}

    public UserDto saveUser(final User user){
        User saveUser = new User();
            saveUser.setName(user.getName());
            saveUser.setLogin(user.getLogin());
            saveUser.setPassword(user.getPassword());
            saveUser.setUuid(user.getUuid());
            saveUser.setEmail(user.getEmail());
            saveUser.setCreateDate(new DateTime().toDate());
            saveUser.setRoles(user.getRoles());
       return userRepo.save(saveUser).toDto();
    }
}
