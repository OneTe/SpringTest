package com.migc.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangcheng  on 2017/11/15.
 */

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String handleLoginRequest(@RequestParam String name,
                                     @RequestParam String password,ModelMap model){

        if(!loginService.validateUser(name,password)){
            model.put("errorMessage","wrong user");
            return "login";
        }
        model.put("password",password);
        model.put("name",name);
        return "welcome";
    }
}
