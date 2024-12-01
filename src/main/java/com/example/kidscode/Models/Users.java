package com.example.kidscode.Models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String password;
    private String role;
    private String phone;
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
    }

    public Users(String username, String password, String surname,  String name, String patronymic, String role) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public String getFio(){
        return this.getSurname() + ' ' + this.getName() + ' ' + this.getPatronymic();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		return password;
	}

    @Override
    public String getUsername(){return username;}

}
