package com.mydoc.rdv.persistence.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "user")
public class Ta_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NonNull
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime lastConnection;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }
}
