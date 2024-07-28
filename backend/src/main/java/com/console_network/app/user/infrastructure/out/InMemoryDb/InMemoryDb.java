package com.console_network.app.user.infrastructure.out.InMemoryDb;

import com.console_network.app.user.domain.model.User;

import java.util.HashMap;

public class InMemoryDb {
    public static final HashMap<String, User> usersInMemory = new HashMap<String, User>();

}
