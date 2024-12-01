package com.example.kidscode.Repository;


import com.example.kidscode.Models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
