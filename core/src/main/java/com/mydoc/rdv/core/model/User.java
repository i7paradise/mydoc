package com.mydoc.rdv.core.model;

import java.time.LocalDateTime;

public class User {

    private final Long idUser;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime lastConnection;

    private User(Long idUser, String email, String firstName, String lastName, LocalDateTime lastConnection) {
        this.idUser = idUser;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastConnection = lastConnection;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private Long idUser;
        private String email;
        private String firstName;
        private String lastName;
        private LocalDateTime lastConnection;

        public Builder withIdUser(Long idUser) {
            this.idUser = idUser;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withLastConnection(LocalDateTime lastConnection) {
            this.lastConnection = lastConnection;
            return this;
        }

        public User build() {
            return new User(idUser, email, firstName, lastName, lastConnection);
        }
    }

	public boolean emailNotValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean nameNotValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
