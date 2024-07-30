package com.console_network.app.user.infrastructure.repositoryImpl;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.mapper.userMapper;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;
import java.util.stream.Collectors;

import static com.console_network.app.user.infrastructure.mapper.userMapper.toUserDto;
import static com.console_network.app.user.infrastructure.InMemoryDb.InMemoryDb.usersInMemory;

@Component
public class userRepositoryImpl implements userRepository{

public userRepositoryImpl(){

}

 @Override
 public UserDto saveUser(User user) {
     usersInMemory.put(user.getName(),user);

User response = usersInMemory.get(user.getName());
 return toUserDto(response);
 }

    public  void addDefaultUsers(List<User> users) {
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



}



