package com.historicalreferencebook.historicalreferencebook.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

}
