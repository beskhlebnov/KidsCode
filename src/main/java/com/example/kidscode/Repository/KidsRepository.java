package com.example.kidscode.Repository;

import com.example.kidscode.Models.Kids;
import com.example.kidscode.Models.Users;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface KidsRepository extends CrudRepository<Kids, Long> {
    List<Kids> findByParent(Users parent);
}
