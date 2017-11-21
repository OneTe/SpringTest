package com.migc.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangcheng  on 2017/11/16.
 */
@Controller
@SessionAttributes("name")
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "/list-todos",method = RequestMethod.GET)
    public String listTodos( ModelMap model){
        model.addAttribute("todos",service.retrieveTodos("in28Minutes"));
        return "list-todos";
    }
    @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
    public String showTodoPage(ModelMap model){
        model.addAttribute("todo",new Todo());
        return "todo";
    }

    @RequestMapping(value = "/add-todo",method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        service.addTodo("in28Minutes",todo.getDesc(),new Date(),false);
        model.clear();
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
        model.addAttribute("todo", service.retrieveTodo(id));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo,
                             BindingResult result) {
        if (result.hasErrors())
            return "todo";

        todo.setUser("in28Minutes");
        //todo.setTargetData(new Date());
        service.updateTodo(todo);
        logger.info("---------" + todo.getDesc().toString());
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:list-todos";
    }


    @RequestMapping(value = "/delete-todo",method = RequestMethod.GET)
    public String deleteTodo(ModelMap model,@RequestParam int id){
        service.deleteTodo(id);
       // model.clear();
        return "redirect:list-todos";
    }
}
