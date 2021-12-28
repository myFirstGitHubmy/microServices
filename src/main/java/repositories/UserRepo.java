package repositories;

import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shared.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long>,JpaRepository<User, Long> {

    public User getUserById(Long id);

    public List<UserDto> getAllUsers();

    @Query("select u from User u where u.login = :login")
    public Optional<UserDto> findUserByLogin(@Param("login") String login);
}
