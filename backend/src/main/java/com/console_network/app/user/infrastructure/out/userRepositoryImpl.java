package com.console_network.app.user.infrastructure.out;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.mapper.userMapper;
import com.console_network.app.user.infrastructure.out.dto.UserDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;
import java.util.stream.Collectors;

import static com.console_network.app.user.infrastructure.mapper.userMapper.toListUserDto;
import static com.console_network.app.user.infrastructure.mapper.userMapper.toUserDto;
import static com.console_network.app.user.infrastructure.out.InMemoryDb.InMemoryDb.usersInMemory;

@Component
public class userRepositoryImpl implements userRepository{

public userRepositoryImpl(){
    loadingData();
}

 @Override
 public UserDto saveUser(User user) {
     usersInMemory.put(user.getName(),user);

User response = usersInMemory.get(user.getName());
 return toUserDto(response);
 }
    public void loadingData(){
     User user3 = new User("Adrian", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user1 = new User("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user2 = new User("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<User> users = Arrays.asList(user1, user2, user3);
        addMultipleUsers(users);
 }
    public void addMultipleUsers(List<User> users) {
        for (User user : users) {
            usersInMemory.put(user.getName(), user);
        }
    }
    @Override
    public List<UserDto> findAllUsers() {
        Collection<User> users = usersInMemory.values();
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public User findUsersByName(String name) {
        return usersInMemory.get(name);
    }
    @Override
    public Optional<UserDto> findUserByName(String name) {

        if(usersInMemory.containsKey(name)){
            return Optional.of(toUserDto(usersInMemory.get(name)));
        }else{
            throw new RuntimeException(" User by " + " not found");
        }
    }

    @Override
    public String addFollowUser(String userOrigen, String userDestino) {
    User Origen = usersInMemory.get(userOrigen);
    User Destino = usersInMemory.get(userDestino);
    if(Origen == null || Destino == null){
        throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "Cant Find That User, its need to be created");

    }
    if(Origen == Destino){
        throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "Cant follow yourself");
    }
        Destino.getFollower().add(Origen);
        Origen.getFollowing().add(Destino);
        return Origen.getName()+" empezo a seguir a "+Destino.getName();
    }

    @Override
    public Optional<List<UserDto>> findAllFollowers(UserDto user) {
        User user1 = usersInMemory.get(user.getName());
        List<User> userFollowers = user1.getFollower();
      List<UserDto> userDtoFollower = toListUserDto(userFollowers);
        List<UserDto> list = new ArrayList<>(userDtoFollower);
        return Optional.of(list);
    }


}



