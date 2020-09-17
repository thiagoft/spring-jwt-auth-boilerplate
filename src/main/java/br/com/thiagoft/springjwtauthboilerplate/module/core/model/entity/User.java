package br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordReset;
    private Date creationDate;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = new Date();
        this.lastPasswordReset = this.creationDate;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public String getEncodedPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(this.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastPasswordReset=" + lastPasswordReset +
                ", creationDate=" + creationDate +
                '}';
    }
}
