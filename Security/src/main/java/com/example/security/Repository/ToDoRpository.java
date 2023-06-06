package com.example.security.Repository;

import com.example.security.Model.MyUser;
import com.example.security.Model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRpository extends JpaRepository<ToDo,Integer> {

    List<ToDo> findToDoByMyUser(MyUser myUser);

    ToDo findToDoById(Integer id);
}
