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
public class WelcomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        model.put("name","in28Minutes");
        return "welcome";
    }
}
