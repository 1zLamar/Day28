package com.example.security.Service;

import com.example.security.ApiException.ApiException;
import com.example.security.ApiResponse.ApiResponse;
import com.example.security.Model.ToDo;
import com.example.security.Repository.AuthRespository;
import com.example.security.Repository.ToDoRpository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ToDoService{

    private final ToDoRpository toDoRpository;
    private final AuthRespository authRespository;
    public List<ToDo> getToDos(Integer id) {
        return toDoRpository.findToDoByMyUser(authRespository.findMyUserById(id));
    }

    public void addToDo(Integer userId, ToDo toDo) {
        toDo.setMyUser(authRespository.findMyUserById(userId));
        toDoRpository.save(toDo);
    }

    public void updateToDo(Integer userId, ToDo toDo, Integer toDoId) {
        ToDo toDo1=toDoRpository.findToDoById(toDoId);
        if(toDo1==null){
            throw new ApiException("not found");
        }
        if(userId!=toDo1.getMyUser().getId()){
            throw new ApiException("unauthorized");
        }
        toDo1.setMsg(toDo.getMsg());
        toDoRpository.save(toDo1);
    }

    public void deleteToDo(Integer userId,Integer toDoId){
        ToDo toDo1=toDoRpository.findToDoById(toDoId);
        if(toDo1==null){
            throw new ApiException("not found");
        }
        if(userId!=toDo1.getMyUser().getId()){
            throw new ApiException("unauthorized");
        }
        toDoRpository.delete(toDo1);
    }
}
