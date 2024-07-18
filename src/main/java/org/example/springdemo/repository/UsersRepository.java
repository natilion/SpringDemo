package org.example.springdemo.repository;

import org.example.springdemo.model.Users;
import org.springframework.data.jdbc.repository.query.*;
//import org.springframework.data.jdbc.repository.query.Modifying;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findByLogin(String login);

    @Modifying
    @Query("UPDATE users SET name = :name WHERE id = :id")
    boolean updateById(@Param("id") Long id, @Param("name") String name);
}
