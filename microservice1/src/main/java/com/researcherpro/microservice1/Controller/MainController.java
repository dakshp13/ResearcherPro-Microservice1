package com.researcherpro.microservice1.Controller;

import com.researcherpro.microservice1.Model.LoginCreds;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MainController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
