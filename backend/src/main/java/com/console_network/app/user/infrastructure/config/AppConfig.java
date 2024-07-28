package com.console_network.app.user.infrastructure.config;

import com.console_network.app.user.application.Service.UserService;
import com.console_network.app.user.application.UseCase.userAddFollowUseCaseImpl;
import com.console_network.app.user.application.UseCase.userCreateUseCaseImpl;
import com.console_network.app.user.application.UseCase.userGetByIdUseCaseImpl;
import com.console_network.app.user.application.UseCase.userGetUseCaseImpl;
import com.console_network.app.user.infrastructure.out.userRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.console_network.app.user.domain.repository.out.userRepository;
@Configuration
public class AppConfig {
    @Bean
    public userRepository userRepository() {
        return new userRepositoryImpl(); //
    }
    @Bean
    public UserService UserService(userRepository userRepository){
        return new UserService(
                new userGetUseCaseImpl(userRepository),
                new userCreateUseCaseImpl(userRepository),
                new userGetByIdUseCaseImpl(userRepository),
                new userAddFollowUseCaseImpl(userRepository));

    }


}
