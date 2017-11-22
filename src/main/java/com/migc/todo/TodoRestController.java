package com.migc.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by wangcheng  on 2017/11/22.
 */
@RestController
public class TodoRestController {
    @Autowired
    TodoService service;
    @RequestMapping(value = "/todos",method = RequestMethod.GET)
    public List<Todo> listTodos(){
        List<Todo> list = service.retrieveTodos("in28Minutes");
        return list;
    }

    @RequestMapping(value = "/todos/{id}",method = RequestMethod.GET)
    public Todo retrieveTodo(@PathVariable("id") int id){
        Todo todo = service.retrieveTodo(id);
        return todo;
    }
}
