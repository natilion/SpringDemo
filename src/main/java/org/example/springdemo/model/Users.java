package org.example.springdemo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//@Data
//@Builder
//@Table(name = "user")
public class Users {
    @Id
    @Generated
    private long id;
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    private String name;
    @NonNull
    private String password;

    public Users(@NonNull String email, @NonNull String login, @NonNull String name, @NonNull String password) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public @NonNull String getLogin() {
        return login;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String toJSONString() {
        return "{" +
//                "\"id\"" + ":" + "\"" + id + "\"," +
                "\"email\"" + ":" + "\"" + email + "\"," +
                "\"login\"" + ":" + "\"" + login + "\"," +
                "\"name\"" + ":" + "\"" + name + "\"," +
                "\"password\"" + ":" + "\"" + password + "\"" +
                '}';
    }
}
