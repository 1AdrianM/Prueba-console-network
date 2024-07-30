package com.console_network.app.post.infrastructure.InMemoryDb;

import com.console_network.app.post.domain.model.Post;

import java.util.HashMap;
import java.util.List;

public class postInMemory {
    public static final HashMap<String, List<Post>> postInMemory = new HashMap<String, List<Post>> ();
 //este Mapa almacena una lista de post dentro de un clave tipo String, que basicamente seria nuestro User.name
}
