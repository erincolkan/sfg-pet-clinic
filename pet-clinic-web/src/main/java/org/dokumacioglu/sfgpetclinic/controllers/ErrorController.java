package org.dokumacioglu.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/oups")
@Controller
public class ErrorController {
    @RequestMapping({"", "/"})
    public String handleErrors(){
        return "notimplemented";
    }
}
