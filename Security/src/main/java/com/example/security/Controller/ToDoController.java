package com.example.security.Controller;

import com.example.security.Model.MyUser;
import com.example.security.Model.ToDo;
import com.example.security.Service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class ToDoController{

    private  final ToDoService toDoService;

    @GetMapping("/get")
    public ResponseEntity getToDos(@AuthenticationPrincipal MyUser myUser){
        List<ToDo> toDos=toDoService.getToDos(myUser.getId());
        return ResponseEntity.status(200).body(toDos);
    }

    @PostMapping("/add")
    public ResponseEntity addToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo){
        toDoService.addToDo(myUser.getId(),toDo);
        return ResponseEntity.status(200).body("todo added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo, @PathVariable Integer id){
        toDoService.updateToDo(myUser.getId(),toDo,id);
        return ResponseEntity.status(200).body("todo updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        toDoService.deleteToDo(myUser.getId(),id);
        return ResponseEntity.status(200).body("todo deleted");
    }


}
