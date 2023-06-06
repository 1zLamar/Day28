package com.example.security.Repository;

import com.example.security.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRespository extends JpaRepository<MyUser,String> {

    MyUser findMyUserByUsername(String username);

    MyUser findMyUserById(Integer id);

}
