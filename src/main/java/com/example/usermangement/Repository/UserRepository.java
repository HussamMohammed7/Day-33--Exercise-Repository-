package com.example.usermangement.Repository;

import com.example.usermangement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findUserById(Integer id);

    User findUserByEmail(String email);

    User findUserByUsername(String username);



    @Query("select e from User e where e.role = ?1")
    List<User> getRole(String role);

    @Query("select e from User e where e.age >= ?1")
    List<User> getAgeOrAbove(int age);







}
