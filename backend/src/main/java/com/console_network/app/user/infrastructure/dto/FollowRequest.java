package com.console_network.app.user.infrastructure.dto;

import java.util.Objects;

public class FollowRequest {
    private String userOrigen;
    private String userDestino;

    // Constructor con ambos parámetros
    public FollowRequest(String userOrigen, String userDestino) {
        this.userOrigen = userOrigen;
        this.userDestino = userDestino;
    }

    // Constructor sin parámetros (para deserialización)
    public FollowRequest() {}

    // Getters y Setters
    public String getUserOrigen() {
        return userOrigen;
    }

    public void setUserOrigen(String userOrigen) {
        this.userOrigen = userOrigen;
    }

    public String getUserDestino() {
        return userDestino;
    }

    public void setUserDestino(String userDestino) {
        this.userDestino = userDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowRequest that = (FollowRequest) o;
        return Objects.equals(userOrigen, that.userOrigen) && Objects.equals(userDestino, that.userDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOrigen, userDestino);
    }

}
